package org.academiadecodigo.tetris.networking;

import org.academiadecodigo.tetris.Constants;
import org.academiadecodigo.tetris.Game;
import org.academiadecodigo.tetris.Utils;
import org.academiadecodigo.tetris.event.BlockSpawnEvent;
import org.academiadecodigo.tetris.event.GameEvent;
import org.academiadecodigo.tetris.event.GameEventType;

/**
 * Created by codecadet on 14/11/2017.
 */
public class ClientEventHandler {

    public static void handleEvent(GameEvent gameEvent) {

        switch (gameEvent.getGameEventType()) {

            case START_TIMER:

                Game.getInstance().showStart();
                break;

            case GAME_START:

                break;

            case BLOCK_SPAWN:

                handleBlockSpawnEvent((BlockSpawnEvent) gameEvent);
                break;

        }
    }

    private static void handleBlockSpawnEvent(BlockSpawnEvent event) {

        Game.getInstance().insertOtherPlayerBlock(event.getBlockType());
    }
}
