import cats.effect.IO

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global


object FutureFeatures {
  def main(args: Array[String]) : Unit = {

    val f = Future.successful("I come from the Future!")

    val ff = IO.fromFuture( IO.pure(f) )

    val result = ff.unsafeRunSync()

    println(result)
  }
}
