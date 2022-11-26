package com.vijayvepa.akkagrpc.server.event;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.google.common.base.Preconditions;
import lombok.Value;

@Value
@JsonDeserialize
public class AkkaDisabled implements Event {

    public String akkaId;
    public String disabledBy;
    public String disabledDateTime;

    @JsonCreator
    public AkkaDisabled(String akkaId, String disabledBy, String disabledDateTime) {
        this.akkaId = Preconditions.checkNotNull(akkaId, "akkaId");
        this.disabledBy = Preconditions.checkNotNull(disabledBy, "disabledBy");
        this.disabledDateTime = disabledDateTime;
    }
}
