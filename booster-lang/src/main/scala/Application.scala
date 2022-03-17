package tech.iooo.coco

import com.typesafe.scalalogging.Logger

import java.util.concurrent.TimeUnit
import scala.sys.error

/**
 * @author 龙也
 * @date 2022/3/17 10:21 AM
 */
object Application {

  private val logger = Logger(Application.getClass)

  def main(args: Array[String]): Unit = {
    logger.info("hello world")

    val complex = new Complex(1.1, -2.2)
    logger.info("{}", complex)
    logger.info("{}", complex.re)
    logger.info("{}", complex.im)

    type Environment = String => Int

    def eval(t: Tree, env: Environment): Int = t match {
      case Sum(l, r) => eval(l, env) + eval(r, env)
      case Multi(l, r) => eval(l, env) * eval(r, env)
      case Var(n) => env(n)
      case Const(v) => v
    }

    def derive(t: Tree, v: String): Tree = t match {
      case Sum(l, r) => Sum(derive(l, v), derive(r, v))
      case Var(n) if v == n => Const(1)
      case _ => Const(0)
    }

    val exp: Tree = Multi(Sum(Sum(Var("x"), Var("x")), Sum(Const(7), Var("y"))), Const(2))
    val env: Environment = {
      case "x" => 5
      case "y" => 7
    }

    logger.info("Expression: " + exp)
    logger.info("Evaluation with x=5, y=7: " + eval(exp, env))
    logger.info("Derivative relative to x: " + derive(exp, "x"))
    logger.info("Derivative relative to y: " + derive(exp, "y"))

    val cell = new Reference[Int]
    cell.set(13)
    logger.info("Reference contains the half of " + (cell.get * 2))

    logger.info("hello world")

    logger.info("{}", (1 to 10).filter(i => i % 2 == 0).toList)

    val arr = Array(1, 2, 3, 4, 5)
    val map = Map(
      1 -> 2,
      2 -> 3
    )
    val safeMap = map.withDefaultValue(-1)
    logger.info("{}", safeMap(4))

    val t = (1, 2, 3)
    logger.info("{}", t._1)
  }


  def oncePerSecond(callback: () => Unit): Unit = {
    while (true) {
      callback()
      TimeUnit.SECONDS.sleep(1)
    }
  }
}


class Complex(real: Double, imaginary: Double) {

  def re: Double = real

  def im: Double = imaginary

  override def toString: String = s"$re ${if (im < 0) "-" else "+"} ${Math.abs(im)}i"
}

abstract class Tree

case class Sum(l: Tree, r: Tree) extends Tree

case class Multi(l: Tree, r: Tree) extends Tree

case class Var(n: String) extends Tree

case class Const(v: Int) extends Tree

trait Ord {
  def <(that: Any): Boolean

  def <=(that: Any): Boolean = (this < that) || (this == that)

  def >(that: Any): Boolean = !(this <= that)

  def >=(that: Any): Boolean = !(this < that)
}

class Date(y: Int, m: Int, d: Int) extends Ord {
  def year: Int = y

  def month: Int = m

  def day: Int = d

  override def toString: String = s"$year-$month-$day"

  override def <(that: Any): Boolean = {
    val o = checkOrCase(that)
    (year < o.year) || (year == o.year && (month < o.month || (month == o.month && day < o.day)))
  }

  override def equals(that: Any): Boolean = {
    val o = checkOrCase(that)
    o.day == day && o.month == month && o.year == year
  }

  private def checkOrCase(ins: Any): Date = {
    if (!ins.isInstanceOf[Date]) {
      error("cannot compare " + ins + " and a Date")
    }
    ins.asInstanceOf[Date]
  }
}

class Reference[T] {
  private var contents: T = _

  def set(value: T): Unit = {
    contents = value
  }

  def get: T = contents
}