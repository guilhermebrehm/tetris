package org.academiadecodigo.tetris.event;

import org.academiadecodigo.tetris.Constants;

public abstract class GameEvent {

    private GameEventType gameEventType;

    public GameEvent(GameEventType gameEventType) {

        this.gameEventType = gameEventType;
    }

    public GameEventType getGameEventType() {
        return gameEventType;
    }

    public static boolean isEvent(String event) {

        return event.substring(0, Constants.EVENT_DELIMITER.length()).equals(Constants.EVENT_DELIMITER);
    }

    @Override
    public String toString() {
        return String.valueOf(gameEventType.ordinal());
    }
}
