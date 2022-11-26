package com.vijayvepa.akkagrpc.dagger;

import com.akkagrpc.akka.AkkaServicePowerApi;
import dagger.Binds;
import dagger.Module;
import com.vijayvepa.akkagrpc.projection.*;
import com.vijayvepa.akkagrpc.query.AkkaDAO;
import com.vijayvepa.akkagrpc.query.AkkaDAOImpl;
import com.vijayvepa.akkagrpc.query.QueryServer;
import com.vijayvepa.akkagrpc.query.QueryServerImpl;

import com.vijayvepa.akkagrpc.server.CommandServer;
import com.vijayvepa.akkagrpc.server.CommandServerImpl;
import com.vijayvepa.akkagrpc.server.AkkaServiceImpl;
import com.vijayvepa.akkagrpc.util.*;

import javax.inject.Singleton;

@Module
public interface AkkaManagementModule {

    @Binds
    @Singleton
    AkkaServicePowerApi bindAkkaServicePowerApi(AkkaServiceImpl impl);

    @Binds
    @Singleton
    AkkaDAO bindAkkaDAO(AkkaDAOImpl dao);

    @Binds
    @Singleton
    MicrometerClient bindMicrometerClient(MicrometerClientImpl client);

    @Singleton
    @Binds
    ActiveDirectoryClient bindActiveDirectoryClient(ActiveDirectoryClientImpl client);

    @Singleton
    @Binds
    ElasticSearchRestClient bindElasticSearchRestClientImpl(ElasticSearchRestClientImpl impl);

    @Singleton
    @Binds
    DBProjection bindDBProjection(DBProjectionImpl impl);

    @Singleton
    @Binds
    EventsProjection bindPublishEventsProjection(EventsProjectionImpl impl);

    @Singleton
    @Binds
    CommandServer bindCommandServer(CommandServerImpl impl);

    @Singleton
    @Binds
    QueryServer bindQueryServer(QueryServerImpl impl);
}

