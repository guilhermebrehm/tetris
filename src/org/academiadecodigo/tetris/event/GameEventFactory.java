package org.academiadecodigo.tetris.event;

import org.academiadecodigo.tetris.Constants;
import org.academiadecodigo.tetris.Utils;
import org.academiadecodigo.tetris.movable.spinnable.block.Block;
import org.academiadecodigo.tetris.movable.spinnable.block.BlockType;

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

            case BLOCK_SPAWN:

                BlockType blockType = BlockType.values()[Integer.parseInt(eventParts[1])];
                return blockSpawnEvent(blockType);
        }

        throw new IllegalArgumentException();
    }

    public static StartTimerEvent startTimerEvent() {

        return new StartTimerEvent();

    }

    public static GameStartEvent gameStartEvent() {

        return new GameStartEvent();
    }

    public static BlockSpawnEvent blockSpawnEvent(BlockType blockType) {

        return new BlockSpawnEvent(blockType);
    }
}
