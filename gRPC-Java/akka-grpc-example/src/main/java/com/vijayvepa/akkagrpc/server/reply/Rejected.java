package com.vijayvepa.akkagrpc.server.reply;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;

@Value
@JsonDeserialize
public class Rejected implements Confirmation {
    public String reason;

    @JsonCreator
    public Rejected(String reason) {
        this.reason = reason;
    }
}
