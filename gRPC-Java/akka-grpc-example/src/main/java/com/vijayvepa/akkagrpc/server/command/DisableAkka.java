package com.vijayvepa.akkagrpc.server.command;

import akka.actor.typed.ActorRef;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import lombok.Value;
import com.vijayvepa.akkagrpc.server.reply.Confirmation;

@Value
@JsonDeserialize
public class DisableAkka implements Command<Confirmation> {
    private String akkaId;
    private String disabledBy;
    public ActorRef<Confirmation> replyTo;

    @JsonCreator
    public DisableAkka(String akkaId, String disabledBy, ActorRef<Confirmation> replyTo) {
        this.akkaId = Preconditions.checkNotNull(akkaId, "Blank akkaId");
        this.disabledBy = Preconditions.checkNotNull(disabledBy, "Blank disabledBy");
        this.replyTo = replyTo;
    }
}