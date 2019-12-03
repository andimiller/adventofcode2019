import cats._
import cats.implicits._
import cats.data._
import higherkindness.droste._
import higherkindness.droste.scheme._
import cats.mtl._
import cats.mtl.implicits._

import scala.language.higherKinds

object Two {

  object Opcode {
    val add = 1
    val mul = 2
    val stop = 99
  }

  def evaluate(xs: Vector[Int], in1: Int, in2: Int, target: Int)(f: (Int, Int) => Int): Vector[Int] = {
    val i1 = xs(in1)
    val i2 = xs(in2)
    val v1 = xs(i1)
    val v2 = xs(i2)
    val t = xs(target)
    xs.updated(t, f(v1, v2))
  }

  def run[F[_]: Monad](index: Int)(implicit state: MonadState[F, Vector[Int]], raise: FunctorRaise[F, Vector[Int]]): F[Vector[Int]] = for {
    initial <- state.get
    _       <- initial(index) match {
      case Opcode.add =>
        state.set(evaluate(initial, index + 1, index + 2, index + 3){_ + _})
      case Opcode.mul =>
        state.set(evaluate(initial, index + 1, index + 2, index + 3){_ * _})
      case Opcode.stop =>
        raise.raise[Unit](initial)
    }
    result <- state.get
  } yield result


  def main(args: Array[String]): Unit = {
    type App[T] = StateT[EitherT[Eval, Vector[Int], *], Vector[Int], T]
    val r = run[App](0).iterateWhile(_ => true).runS(Vector(1, 0, 0, 0, 99))
    println(r.value.value)
  }

}
