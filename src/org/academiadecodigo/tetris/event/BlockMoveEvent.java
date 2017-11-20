package org.academiadecodigo.tetris.event;

import org.academiadecodigo.tetris.direction.Direction;

/**
 * Created by codecadet on 20/11/2017.
 */
public class BlockMoveEvent extends GameEvent{

    private Direction direction;

    public BlockMoveEvent(Direction direction) {
        super(GameEventType.BLOCK_MOVE);
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }
}
