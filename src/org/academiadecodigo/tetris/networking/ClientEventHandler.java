package org.academiadecodigo.tetris.networking;

import org.academiadecodigo.tetris.Constants;
import org.academiadecodigo.tetris.Utils;
import org.academiadecodigo.tetris.event.GameEventType;

/**
 * Created by codecadet on 14/11/2017.
 */
public class ClientEventHandler {

    public static void handleEvent(String eventMessage) {

        String[] eventParts = eventMessage.split(Constants.EVENT_DELIMITER);

        if(!Utils.areNumbers(eventParts)) {

            System.out.println("Event is not a number!");
        }

        GameEventType gameEventType = GameEventType.values()[Integer.parseInt(eventParts[0])];

        switch (gameEventType) {

            case START_TIMER:

                System.out.println("start timer");
                break;

            case GAME_START:

                System.out.println("game start");
                break;
        }
    }
}
