//import scala.concurrent.Future
//import scala.concurrent.ExecutionContext.Implicits.global
//import cats._;
//import cats.implicits._
//
//
//object FutureFunctorRunner {
//
//  val futureListF = Functor[Future].compose(Functor[List])
//
//  val data: Future[List[Int]] = Future(List(4, 5, 6))
//
//  case class FutOpt[A](value: Future[Option[A]])
//
//  val monad = new Monad[FutOpt] {
//
//    override def pure[A](a: => A): FutOpt[A] = FutOpt(a.pure[Option].pure[Future])
//
//    override def map[A, B](fa: FutOpt[A])(f: A => B): FutOpt[B] =
//      FutOpt(fa.value.map(optA => optA.map(f)) )
//
//    override def flatMap[A, B](fa: FutOpt[A])(f: A => FutOpt[B]): FutOpt[B] =
//      FutOpt(fa.value.flatMap(opt => opt match {
//        case Some(a) => f(a).value
//        case None => (None: Option[B]).pure[Future]
//      }))
//  }
//
//  def main(args : Array[String]): Unit = {
//    val res = futureListF.map(data)(_ + 1)
//    println(res)
//  }
//
//}
