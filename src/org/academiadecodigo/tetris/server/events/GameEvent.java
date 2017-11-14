package org.academiadecodigo.tetris.server.events;

import org.academiadecodigo.tetris.Constants;

public abstract class GameEvent {

    private GameEventType gameEventType;

    public GameEvent(GameEventType gameEventType) {

        this.gameEventType = gameEventType;
    }

    public GameEventType getGameEventType() {
        return gameEventType;
    }

    @Override
    public String toString() {
        return Constants.EVENT_DELIMITER + gameEventType.ordinal();
    }
}
