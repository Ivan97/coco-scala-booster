package tech.iooo.coco

import Msg.Msg

import akka.actor.UntypedAbstractActor
import com.typesafe.scalalogging.Logger

/**
 * @author 龙也
 * @date 2022/3/17 4:25 PM
 */
class Greeter extends UntypedAbstractActor {

  private val logger = Logger(this.getClass)

  override def onReceive(message: Any): Unit = {

    if (message.asInstanceOf[Msg] == Msg.GREET) {
      logger.info("Hello world")
      Thread.sleep(1000)
      getSender().tell(Msg.DONE, getSelf())
    } else {
      unhandled(message)
    }
  }
}
