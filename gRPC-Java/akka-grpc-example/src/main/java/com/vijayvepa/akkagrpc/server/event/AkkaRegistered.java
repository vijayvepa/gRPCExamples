package com.vijayvepa.akkagrpc.server.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import lombok.Value;
import com.akkagrpc.akka.Genre;

@Value
@JsonDeserialize
public class AkkaRegistered implements Event {
    public String akkaId;
    public String title;
    public int releaseYear;
    public Float rating;
    public Genre genre;
    public String createdBy;
    public String createdDateTime;

    @JsonCreator
    public AkkaRegistered(String akkaId, String title, int releaseYear, Float rating, Genre genre, String createdBy, String createdDateTime) {
        this.akkaId = akkaId;
        this.title = title;
        this.releaseYear = releaseYear;
        this.rating = rating;
        this.genre = genre;
        this.createdBy = createdBy;
        this.createdDateTime = createdDateTime;
    }
}
