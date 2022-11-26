package com.vijayvepa.akkagrpc.util;

import io.micrometer.prometheus.PrometheusMeterRegistry;

import javax.inject.Singleton;

@Singleton
public interface MicrometerClient {
    PrometheusMeterRegistry monitoringSystem();
}
