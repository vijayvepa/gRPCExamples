package com.vijayvepa.akkagrpc.util;

import com.vijayvepa.akkagrpc.server.ESRecord;
import com.vijayvepa.akkagrpc.server.AkkaReport;
import org.elasticsearch.client.RestHighLevelClient;

public interface ElasticSearchRestClient {

    ESRecord convertToESRecord(AkkaReport record);

    RestHighLevelClient getElasticSearchRestClient(boolean esAuthenticationEnabled);
}
