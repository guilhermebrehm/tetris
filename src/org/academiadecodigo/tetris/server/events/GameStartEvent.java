package org.academiadecodigo.tetris.server.events;

/**
 * Created by codecadet on 14/11/2017.
 */
public class GameStartEvent extends GameEvent{

    public GameStartEvent() {
        super(GameEventType.GAME_START);
    }

}
