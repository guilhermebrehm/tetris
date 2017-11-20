package org.academiadecodigo.tetris;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.tetris.direction.Direction;
import org.academiadecodigo.tetris.event.BlockMoveEvent;
import org.academiadecodigo.tetris.event.GameEventFactory;
import org.academiadecodigo.tetris.gui.HUD;
import org.academiadecodigo.tetris.movable.spinnable.block.Block;
import org.academiadecodigo.tetris.movable.spinnable.block.BlockFactory;
import org.academiadecodigo.tetris.grid.Grid;
import org.academiadecodigo.tetris.keyboard_listener.KeyboardListener;
import org.academiadecodigo.tetris.movable.spinnable.block.BlockType;
import org.academiadecodigo.tetris.networking.NetworkThread;
import sun.nio.ch.Net;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {

    private KeyboardListener keyboardListener;

    private boolean paused;

    private boolean end;

    private HUD hud;

    private Grid grid;
    private Grid otherPlayerGrid;

    private Block activeBlock;
    private Block otherPlayerActiveBlock;

    private int score;
    private int otherPlayerScore;

    private static Game game;

    private Game() {}

    public static Game getInstance() {

        if(game == null) {

            game = new Game();
        }

        return game;
    }

    public void init() {


        hud = new HUD();

        grid = new Grid(10, 20);

        otherPlayerGrid = new Grid(10, 20, Constants.SEPARATOR_XMIN + Constants.SEPARATOR_WIDTH);

        activeBlock = BlockFactory.getBlock(grid);

        keyboardListener = new KeyboardListener(this);
        keyboardListener.setBlock(activeBlock);

        paused = false;
        end = false;

        score = 0;

        hud.init();

        hud.updateScore(score);

        ExecutorService executorService = Executors.newCachedThreadPool();

        NetworkThread networkThread = NetworkThread.getInstance();
        networkThread.connect("localhost");

        executorService.submit(networkThread);
    }

    public void start() {
        try {
            gameLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void gameLoop() throws InterruptedException {

        while (true) {

            if (!paused && !end) {
                if (activeBlockHitBottom()) {
                    score += grid.checkLines();
                    hud.updateScore(score);

                    newBlock();

                    if (activeBlockHitBottom()) {
                        gameOver();
                        end = true;
                    }

                }

                moveDownActiveBlock();
            }

            Thread.sleep(Constants.DELAY - score / Constants.LEVEL_SCORE * Constants.LEVEL_TIME_INCREASE);
        }
    }



    private void gameOver() {

        hud.drawOverText();
        activeBlock.erase();
    }

    public void restart() {

        if (paused) {
            return;
        }

        grid.reset();

        newBlock();

        score = 0;
        hud.updateScore(score);

        end = false;

        hud.deleteOverText();
    }

    public boolean isPaused() {
        return paused;
    }

    public void pause() {

        if (end) {
            return;
        }

        hud.drawOverText();
        paused = true;
    }

    public void unPause() {

        hud.drawOverText();
        paused = false;
    }

    public void showStart() {
        hud.startTimerText();
    }

    private void newBlock() {

        activeBlock = BlockFactory.getBlock(grid);
        NetworkThread.getInstance().sendEvent(GameEventFactory.blockSpawnEvent(activeBlock.getType()));
        keyboardListener.setBlock(activeBlock);
    }

    private void moveDownActiveBlock() {

        activeBlock.moveDown();
        NetworkThread.getInstance().sendEvent(GameEventFactory.blockMoveEvent(Direction.DOWN));
    }

    private boolean activeBlockHitBottom() {

        return activeBlock.hitBottom();
    }

    public void insertOtherPlayerBlock(BlockType blockType) {

        BlockFactory.getBlockByType(blockType, otherPlayerGrid);
    }

    public void moveOtherPlayerBlock(Direction direction) {

        switch (direction) {

            case RIGHT:
                otherPlayerActiveBlock.moveRight();
                break;
            case DOWN:
                otherPlayerActiveBlock.moveDown();
                break;
            case LEFT:
                otherPlayerActiveBlock.moveLeft();
                break;
        }
    }


}
