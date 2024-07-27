package org.example.actorOne;

import akka.actor.ActorSelection;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.AbstractBehavior;
import akka.actor.typed.javadsl.ActorContext;
import akka.actor.typed.javadsl.Behaviors;
import akka.actor.typed.javadsl.Receive;

import java.util.Scanner;

public class Actor1 extends AbstractBehavior<String> {

    private final ActorSelection actor2;
    public Actor1(ActorContext<String> context) {
        super(context);

        //create reference to actor2
        actor2 = context.getSystem().classicSystem().actorSelection("akka://system2@127.0.0.1:25521/user");

    }
    public static Behavior<String> create() {
        return Behaviors.setup(Actor1::new);
    }

    @Override
    public Receive<String> createReceive() {
        return newReceiveBuilder()
                .onMessageEquals("sendToActor2", this::sendToActor2)
                .onMessage(String.class, this::onMessage).build();
    }

    //handles replies from other actors
    private Behavior<String> onMessage(String message) {

        System.out.println("\nreceived message: " + message);
        return this;
    }

    //takes input from console and send to actor2
    public Behavior<String> sendToActor2() {

        Scanner sc = new Scanner(System.in);
        System.out.println("\nenter message to send to Actor2:");
        String test = sc.nextLine();
        sc.close();

        actor2.tell(test, this.getContext().classicActorContext().self());

        return this;
    }
}

