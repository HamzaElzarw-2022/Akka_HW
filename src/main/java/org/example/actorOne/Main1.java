package org.example.actorOne;

import akka.actor.typed.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.example.actorOne.Actor1;

public class Main1 {
    public static void main(String[] args) throws InterruptedException {

        Config config = ConfigFactory.load("application1.conf");
        // Creating and binding the actor
        ActorSystem<String> system1 = ActorSystem.create(Actor1.create(), "system1", config);

        //sends command to actor1 to trigger actor1 receiver
        system1.tell("sendToActor2");

    }
}