package org.academiadecodigo.tetris;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.tetris.event.GameEventFactory;
import org.academiadecodigo.tetris.gui.HUD;
import org.academiadecodigo.tetris.movable.spinnable.block.Block;
import org.academiadecodigo.tetris.movable.spinnable.block.BlockFactory;
import org.academiadecodigo.tetris.grid.Grid;
import org.academiadecodigo.tetris.keyboard_listener.KeyboardListener;
import org.academiadecodigo.tetris.movable.spinnable.block.BlockType;
import org.academiadecodigo.tetris.networking.NetworkThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Game {

    private KeyboardListener keyboardListener;

    private boolean paused;

    private boolean end;

    private HUD hud;

    private Grid grid;
    private Block activeBlock;

    private int score;

    private static Game game;

    private Game() {}

    public static Game getInstance() {

        if(game == null) {

            game = new Game();
        }

        return game;
    }

    public void init() {

        ExecutorService executorService = Executors.newCachedThreadPool();

        NetworkThread networkThread = NetworkThread.getInstance();
        networkThread.connect("localhost");

        executorService.submit(networkThread);

        hud = new HUD();

        grid = new Grid(10, 20);
        activeBlock = BlockFactory.getBlock(grid);

        keyboardListener = new KeyboardListener(this);
        keyboardListener.setBlock(activeBlock);

        paused = false;
        end = false;

        score = 0;

        hud.init();

        hud.updateScore(score);
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
                if (activeBlock.hitBottom()) {
                    score += grid.checkLines();
                    hud.updateScore(score);

                    activeBlock = BlockFactory.getBlock(grid);

                    if (activeBlock.hitBottom()) {
                        gameOver();
                        end = true;
                    }

                    keyboardListener.setBlock(activeBlock);
                }

                activeBlock.moveDown();
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

        activeBlock = BlockFactory.getBlock(grid);
        keyboardListener.setBlock(activeBlock);

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

    /*public void insertNewBlock(BlockType blockType) {
        Block block = BlockFactory.getBlock(grid);

        NetworkThread.getInstance().sendEvent(GameEventFactory.blockSpawnEvent(block.));

    }*/



}
