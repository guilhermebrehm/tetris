package org.academiadecodigo.tetris.movable.spinnable.block;

import org.academiadecodigo.tetris.grid.Grid;

public abstract class BlockFactory {

    public static Block getBlock(Grid grid) {

        BlockType rand = BlockType.getRandom();

        return getBlockByType(rand, grid);
    }

    public static Block getBlockByType(BlockType type, Grid grid) {

        switch (type) {
            case IBLOCK:
                return new IBlock(grid);

            case LBLOCK:
                return new LBlock(grid);

            case JBLOCK:
                return new JBlock(grid);

            case SBLOCK:
                return new SBlock(grid);

            case ZBLOCK:
                return new ZBlock(grid);

            case TBLOCK:
                return new TBlock(grid);

            case SQUARE:
                return new Square(grid);
        }

        return null;
    }
}
