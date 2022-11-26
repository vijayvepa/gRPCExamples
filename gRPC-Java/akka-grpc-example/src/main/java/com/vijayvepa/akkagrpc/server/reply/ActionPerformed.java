package com.vijayvepa.akkagrpc.server.reply;

public class ActionPerformed implements Confirmation {
    public final String description;
    public ActionPerformed(String description) {
        this.description = description;
    }
}
