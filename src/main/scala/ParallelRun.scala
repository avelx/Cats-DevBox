import cats.effect.IO
import scala.concurrent.ExecutionContext.Implicits.global

object ParallelRun {
  def main(args: Array[String]) : Unit = {

    import cats.implicits._
    implicit val contextShift = IO.contextShift(global)

    val ioA = IO(println("Running ioA"))
    val ioB = IO(println("Running ioB"))
    val ioC = IO(println("Running ioC"))

    // make sure that you have an implicit ContextShift[IO] in scope. We created one earlier in this document.
    val program = (ioA, ioB, ioC).parMapN { (a, b, c) => () }

    program.unsafeRunSync()

  }
}
