//object CatFunctor {
//
//  def main(args: Array[String]): Unit = {
//
//    import scala.language.higherKinds
//    import cats.Functor
//
//    import cats.instances.list._   // for Functor
//    import cats.instances.option._ // for Functor
//
//    val list1 = List(1, 2, 3)
//    // list1: List[Int] = List(1, 2, 3)
//    val list2 = Functor[List].map(list1)(_ * 2)
//    // list2: List[Int] = List(2, 4, 6)
//    val option1 = Option(123)
//    // option1: Option[Int] = Some(123)
//    val option2 = Functor[Option].map(option1)(_.toString)
//
//
//    implicit val optionFunctor: Functor[Option] =
//      new Functor[Option] {
//        def map[A, B](value: Option[A])(func: A => B): Option[B] = value.map(func)
//      }
//
//    sealed trait Tree[+A]
//
//    final case class Branch[A](left: Tree[A], right: Tree[A])
//      extends Tree[A]
//
//    final case class Leaf[A](value: A) extends Tree[A]
//
//  }
//
//}