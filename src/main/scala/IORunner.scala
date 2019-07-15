import cats.effect.IO

object IORunner {

  val ioa = IO { println("hey!") }

  def main(args: Array[String]) : Unit = {

    val program: IO[Unit] =
      for {
        _ <- ioa
        _ <- ioa
      } yield ()

    program.unsafeRunSync()
  }

}