package org.academiadecodigo.tetris.event;

import org.academiadecodigo.tetris.Constants;
import org.academiadecodigo.tetris.Utils;

public class GameEventFactory {

    public static GameEvent getEventByString(String string) {
        String[] eventParts = string.split(Constants.EVENT_DELIMITER);

        if (!Utils.areNumbers(eventParts)) {

            System.out.println("Event is not a number!");
        }

        GameEventType gameEventType = GameEventType.values()[Integer.parseInt(eventParts[0])];

        switch (gameEventType) {

            case START_TIMER:

                return startTimerEvent();

            case GAME_START:

                return gameStartEvent();
        }

        throw new IllegalArgumentException();
    }

    public static StartTimerEvent startTimerEvent() {

        return new StartTimerEvent();

    }

    public static GameStartEvent gameStartEvent() {

        return new GameStartEvent();
    }
}
