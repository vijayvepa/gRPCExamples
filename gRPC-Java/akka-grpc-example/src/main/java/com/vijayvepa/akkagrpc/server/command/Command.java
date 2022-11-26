package com.vijayvepa.akkagrpc.server.command;

import com.vijayvepa.akkagrpc.util.CborSerializable;

public interface Command<R> extends CborSerializable {
}