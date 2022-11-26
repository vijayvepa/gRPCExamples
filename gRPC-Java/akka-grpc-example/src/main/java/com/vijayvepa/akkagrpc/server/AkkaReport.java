package com.vijayvepa.akkagrpc.server;
import com.akkagrpc.akka.Genre;

import lombok.Data;

@Data
public class AkkaReport {
    private String akkaId;
    private String title;
    private int releaseYear;
    private Float rating;
    private Genre genre;
    private String creationDateTime;
    private String createdBy;
    private String lastModifiedDateTime;
    private String lastModifiedBy;
    private String smStatus;
}
