package tech.iooo.coco


import Msg.Msg

import akka.actor.{Props, UntypedAbstractActor}

/**
 * @author 龙也
 * @date 2022/3/17 4:20 PM
 */
class HelloWorld extends UntypedAbstractActor {

  override def preStart(): Unit = {
    val greeter = getContext().actorOf(Props.create(classOf[Greeter]), "greeter")
    greeter.tell(msg = Msg.GREET, getSelf())
  }

  override def onReceive(message: Any): Unit = {
    if (message.asInstanceOf[Msg] == Msg.DONE) {
      getContext().stop(getSelf())
    } else {
      unhandled(message)
    }
  }
}
