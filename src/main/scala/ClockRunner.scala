import com.sun.net.httpserver.Authenticator.Success

import scala.concurrent.Future

object ClockRunner {

  def main(args: Array[String]) : Unit = {

    import cats.effect._
    import cats.implicits._
    import scala.concurrent.duration.MILLISECONDS

    def measure[F[_], A](fa: F[A])
                        (implicit F: Sync[F], clock: Clock[F]): F[(A, Long)] = {
      for {
        start <- clock.monotonic(MILLISECONDS)
        result <- fa
        finish <- clock.monotonic(MILLISECONDS)
      }
        yield (result, finish - start)
    }

    def run(data: String) = Sync[IO].delay {
      println(s"DATA $data")
    }

    import scala.concurrent.ExecutionContext.Implicits.global

    def call(): Future[String] = Future {
      Thread.sleep(500)
      "hello"
    }

    val res = run("This is some data output ")

    def logger( x: (Unit, Long) ) =  IO { println( s"${x._2}" ) }

    implicit val clock = Clock.create[IO]

    val runner = measure {
      run("This is a test string")
    }

    {
      for {
        state <- IO( runner.unsafeRunSync() )
        log <- logger(state)
      } yield log
    }.unsafeRunSync()

  }


}
