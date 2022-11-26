package com.vijayvepa.akkagrpc.server.command;

import akka.actor.typed.ActorRef;
import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import com.vijayvepa.akkagrpc.server.reply.Summary;

@Getter
public class GetAkka implements Command<Summary> {

    public String akkaId;
    public ActorRef<? super Summary> replyTo;

    @JsonCreator
    public GetAkka(String akkaId, ActorRef<Summary> replyTo) {
        this.akkaId = akkaId;
        this.replyTo = replyTo;
    }
}
