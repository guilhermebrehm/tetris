package org.academiadecodigo.tetris.networking;

import org.academiadecodigo.tetris.Constants;
import org.academiadecodigo.tetris.Utils;
import org.academiadecodigo.tetris.event.GameEvent;
import org.academiadecodigo.tetris.event.GameEventType;

/**
 * Created by codecadet on 14/11/2017.
 */
public class ClientEventHandler {

    public static void handleEvent(GameEvent gameEvent) {

        switch (gameEvent.getGameEventType()) {

            case START_TIMER:

                System.out.println("start timer");
                break;

            case GAME_START:

                System.out.println("game start");
                break;
        }
    }
}
