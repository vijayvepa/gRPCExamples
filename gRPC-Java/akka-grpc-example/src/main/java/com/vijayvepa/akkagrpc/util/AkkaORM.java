package com.vijayvepa.akkagrpc.util;


import com.vijayvepa.akkagrpc.server.Akka;
import com.akkagrpc.akka.Genre;

public final class AkkaORM {
    private AkkaORM(){}

    public static Akka mapRowToAkka(io.r2dbc.spi.Row row){
        return Akka.builder()
                .akkaId(row.get("akkaid", String.class))
                .title(row.get("title", String.class))
                .releaseYear(row.get("releaseyear", Integer.class))
                .rating(row.get("rating", Float.class))
                .genre(row.get("genre", Genre.class))
                .createdBy(row.get("createdby", String.class))
                .creationDateTime(row.get("creationdatetime", String.class))
                .lastModifiedBy(row.get("lastmodifiedby", String.class))
                .lastModifiedDateTime(row.get("disableddatetime", String.class))
                .smStatus(row.get("smstatus", String.class))
                .build();
    }
}
