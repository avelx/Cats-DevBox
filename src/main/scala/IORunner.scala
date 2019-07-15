import cats.effect.IO
import java.util.concurrent.{Executors, ScheduledExecutorService}

import scala.concurrent.duration._
import java.util.concurrent.Executors.defaultThreadFactory
import cats.effect._
import scala.concurrent.CancellationException

object IORunner {


  def delayedTick(d: FiniteDuration)
                 (implicit sc: ScheduledExecutorService): IO[Unit] = {

    IO.cancelable { cb =>
      val r = new Runnable { def run() = cb(Right(())) }
      val f = sc.schedule(r, d.length, d.unit)

      // Returning the cancellation token needed to cancel
      // the scheduling and release resources early
      IO(f.cancel(false))
    }
  }

  val ioa = IO { println("hey2!") }

  def putStrlLn(value: String) = IO(println(value))

  val readLn = IO(scala.io.StdIn.readLine)

  def main(args: Array[String]) : Unit = {

    val program: IO[Unit] =
      for {
        _ <- ioa
        _ <- ioa
//        data <- readLn
//        _ <- putStrlLn(data)
      } yield ()

    implicit val sc = Executors.newScheduledThreadPool(1)
    val tick = delayedTick(5 seconds)


    def exec: Either[Throwable, Unit] = {
      val x = 5
      val y = 0
      try {
        Right( println( x / y) )
      } catch {
        case ex : Exception => Left(ex)
      }
    }

    //val error = new CancellationException("This is some string .. data")
    //tick.unsafeRunAsync(error)

    program.unsafeRunSync()
  }

}