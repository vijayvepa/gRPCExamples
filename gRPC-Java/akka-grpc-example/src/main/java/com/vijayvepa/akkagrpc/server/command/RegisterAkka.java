package com.vijayvepa.akkagrpc.server.command;

import akka.actor.typed.ActorRef;
import com.akkagrpc.akka.RegisterAkkaRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Value;
import com.vijayvepa.akkagrpc.server.reply.Confirmation;

@Value
@JsonDeserialize
public class RegisterAkka implements Command<Confirmation> {
    private RegisterAkkaRequest akkaDetails;
    public String createdBy;
    public ActorRef<Confirmation> replyTo;

    @JsonCreator
    public RegisterAkka(RegisterAkkaRequest akkaDetails, String createdBy, ActorRef<Confirmation> replyTo) {
        this.akkaDetails = akkaDetails;
        this.createdBy = createdBy;
        this.replyTo = replyTo;
    }
}
