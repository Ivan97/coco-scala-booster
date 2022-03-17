package tech.iooo.coco

import akka.actor.{ActorSystem, Props}
import com.typesafe.scalalogging.Logger

/**
 * @author 龙也
 * @date 2022/3/17 4:47 PM
 */
object GreeterMain {

  private val logger = Logger(this.getClass)

  def main(args: Array[String]): Unit = {
    val actorSystem = ActorSystem.create("Hello")
    val ref = actorSystem.actorOf(Props.create(classOf[HelloWorld]), "HelloWorld")
    logger.info("{}", ref.path)
  }
}
