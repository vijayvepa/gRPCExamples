package com.vijayvepa.akkagrpc.query;

import javax.inject.Inject;

public class QueryServerImpl implements QueryServer {

    AkkaDAO dao;

    @Inject
    QueryServerImpl(AkkaDAO dao){
        this.dao = dao;
    }

    @Override
    public void startQueryServer() {
    }
}
