package org.example.actorTwo;

import akka.actor.typed.ActorSystem;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import org.example.actorTwo.Actor2;

public class Main2 {

    public static void main(String[] args) throws InterruptedException {

        Config config = ConfigFactory.load("application2.conf");

        // Creating and binding the actor
        ActorSystem.create(Actor2.create(), "system2", config);

    }

}
