package org.academiadecodigo.tetris.event;

import org.academiadecodigo.tetris.Constants;
import org.academiadecodigo.tetris.movable.spinnable.block.BlockType;

/**
 * Created by codecadet on 17/11/2017.
 */
public class BlockSpawnEvent extends GameEvent{

    private BlockType blockType;

    public BlockSpawnEvent(BlockType blockType) {
        super(GameEventType.BLOCK_SPAWN);
        this.blockType = blockType;
    }

    @Override
    public String toString() {
        return super.toString() + Constants.EVENT_DELIMITER + blockType.ordinal();
    }


    public BlockType getBlockType() {
        return blockType;
    }
}
