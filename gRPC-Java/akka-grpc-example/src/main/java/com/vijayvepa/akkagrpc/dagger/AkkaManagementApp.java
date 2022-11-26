package com.vijayvepa.akkagrpc.dagger;

import akka.actor.typed.ActorSystem;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import akka.management.javadsl.AkkaManagement;
import dagger.Component;
import com.vijayvepa.akkagrpc.projection.DBProjection;
import com.vijayvepa.akkagrpc.projection.EventsProjection;
import com.vijayvepa.akkagrpc.query.QueryServer;
import com.vijayvepa.akkagrpc.server.CommandServer;
import com.vijayvepa.akkagrpc.server.AkkaAggregate;

import javax.inject.Singleton;

public class AkkaManagementApp {

    @Singleton
    @Component(modules = {ActorSystemModule.class, AkkaManagementModule.class})
    public interface AkkaApp {
        ActorSystem<?> actorSystem();

        AkkaManagement akkaManagement();

        ClusterBootstrap clusterBootstrap();

        DBProjection dbProjection();

        EventsProjection eventsProjection();

        CommandServer commandServer();

        QueryServer queryServer();
    }

    public static void main(String[] args) {
        AkkaApp app = DaggerAkkaManagementApp_AkkaApp.builder().build();
        app.akkaManagement().start();
        app.clusterBootstrap().start();
        AkkaAggregate.init(app.actorSystem());
        app.dbProjection().startProjection();
        app.eventsProjection().startProjection();
        app.commandServer().startCommandServer();
        app.queryServer().startQueryServer();
    }
}