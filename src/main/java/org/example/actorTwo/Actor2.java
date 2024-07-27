package org.example.actorTwo;

import akka.actor.ActorSelection;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

public class Actor2 extends AbstractBehavior<String> {

    public static Behavior<String> create() {
        return Behaviors.setup(Actor2::new);
    }

    public Actor2(ActorContext<String> context) {

        super(context);

    }

    @Override
    public Receive<String> createReceive() {
        return newReceiveBuilder().onMessage(String.class, this::reply).build();
    }

    public Behavior<String> reply(String command) {

        System.out.println("\nreceived message: " + command);

        //create reference to actor1
        ActorSelection actor1 = this.getContext().getSystem().classicSystem().actorSelection("akka://system1@127.0.0.1:25520/user");

        //replies to actor1
        actor1.tell("Hello Actor One.", this.getContext().classicActorContext().self());

        return this;
    }

}
