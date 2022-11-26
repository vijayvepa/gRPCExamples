package com.vijayvepa.akkagrpc.server;
import com.akkagrpc.akka.Genre;

import lombok.Data;

@Data
public class ESRecord {
    private String akkaId;
    private String title;
    private int releaseYear;
    private Float rating;
    private Genre genre;
    private String createdDate;
    private String createdBy;
    private String lastModifiedDate;
    private String lastModifiedBy;
}
