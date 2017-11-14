package org.academiadecodigo.tetris.server.events;

/**
 * Created by codecadet on 14/11/2017.
 */
public class StartTimerEvent extends GameEvent{

    public StartTimerEvent() {
        super(GameEventType.START_TIMER);
    }
}
