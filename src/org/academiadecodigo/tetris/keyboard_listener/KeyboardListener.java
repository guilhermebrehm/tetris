package org.academiadecodigo.tetris.keyboard_listener;

import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.tetris.Game;
import org.academiadecodigo.tetris.direction.Direction;
import org.academiadecodigo.tetris.event.GameEventFactory;
import org.academiadecodigo.tetris.movable.spinnable.block.Block;
import org.academiadecodigo.tetris.networking.NetworkThread;

public class KeyboardListener implements KeyboardHandler {

    private Keyboard kb;
    private Block block;
    private Game game;

    public KeyboardListener(Game game) {
        this.game = game;
        kb = new Keyboard(this);

        KeyboardEvent key = new KeyboardEvent();
        key.setKey(KeyboardEvent.KEY_RIGHT);
        key.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(key);

        key = new KeyboardEvent();
        key.setKey(KeyboardEvent.KEY_LEFT);
        key.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(key);

        key = new KeyboardEvent();
        key.setKey(KeyboardEvent.KEY_DOWN);
        key.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(key);

        key = new KeyboardEvent();
        key.setKey(KeyboardEvent.KEY_SPACE);
        key.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(key);

        key = new KeyboardEvent();
        key.setKey(KeyboardEvent.KEY_UP);
        key.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(key);

        key = new KeyboardEvent();
        key.setKey(KeyboardEvent.KEY_P);
        key.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(key);

        key = new KeyboardEvent();
        key.setKey(KeyboardEvent.KEY_R);
        key.setKeyboardEventType(KeyboardEventType.KEY_RELEASED);
        kb.addEventListener(key);
    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

        if (block == null) {
            return;
        }

        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_RIGHT:
                if (game.isPaused()) {
                    return;
                }

                NetworkThread.getInstance().sendEvent(GameEventFactory.blockMoveEvent(Direction.RIGHT));
                block.moveRight();
                break;

            case KeyboardEvent.KEY_LEFT:
                if (game.isPaused()) {
                    return;
                }

                NetworkThread.getInstance().sendEvent(GameEventFactory.blockMoveEvent(Direction.LEFT));
                block.moveLeft();
                break;

            case KeyboardEvent.KEY_DOWN:
                if (game.isPaused()) {
                    return;
                }

                NetworkThread.getInstance().sendEvent(GameEventFactory.blockMoveEvent(Direction.DOWN));
                block.moveDown();
                break;

            case KeyboardEvent.KEY_UP:
            case KeyboardEvent.KEY_SPACE:
                if (game.isPaused()) {
                    return;
                }

                block.spin();
                break;

            case KeyboardEvent.KEY_P:
                if (!game.isPaused()) {
                    game.pause();
                    break;
                }

                game.unPause();
                break;

            case KeyboardEvent.KEY_R:
                game.restart();
                break;

        }
    }

    public void setBlock(Block block) {
        this.block = block;
    }
}
