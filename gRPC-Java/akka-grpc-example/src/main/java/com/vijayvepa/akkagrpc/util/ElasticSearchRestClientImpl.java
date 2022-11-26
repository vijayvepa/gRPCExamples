package com.vijayvepa.akkagrpc.util;

import com.vijayvepa.akkagrpc.server.AkkaReport;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import com.vijayvepa.akkagrpc.server.ESRecord;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class ElasticSearchRestClientImpl implements ElasticSearchRestClient {
    @Inject
    public ElasticSearchRestClientImpl() {
    }

    @Override
    public RestHighLevelClient getElasticSearchRestClient(boolean esAuthenticationEnabled) {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("ixadmin", "User@123"));
        RestClientBuilder restClientBuilder = RestClient
                .builder(new HttpHost("localhost", 9200, "http"));
        if (esAuthenticationEnabled) {
            restClientBuilder.setHttpClientConfigCallback(h -> h.setDefaultCredentialsProvider(credentialsProvider));
        }
        return new RestHighLevelClient(restClientBuilder);
    }

    @Override
    public ESRecord convertToESRecord(AkkaReport record) {
        ESRecord esRecord = new ESRecord();
        esRecord.setAkkaId(record.getAkkaId());
        esRecord.setTitle(record.getTitle());
        esRecord.setReleaseYear(record.getReleaseYear());
        esRecord.setRating(record.getRating());
        esRecord.setGenre(record.getGenre());
        esRecord.setCreatedBy(record.getCreatedBy());
        esRecord.setCreatedDate(record.getCreationDateTime());
        esRecord.setLastModifiedBy(record.getLastModifiedBy());
        esRecord.setLastModifiedDate(record.getLastModifiedDateTime());
        return esRecord;
    }

}
