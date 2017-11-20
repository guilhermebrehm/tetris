package org.academiadecodigo.tetris.gui;

import org.academiadecodigo.simplegraphics.graphics.Color;
import org.academiadecodigo.simplegraphics.graphics.Rectangle;
import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.tetris.Constants;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by codecadet on 16/11/2017.
 */
public class HUD {

    private Text pausedText;

    private Text[] overText;

    private Text scoreText;
    private Text otherPlayerScoreText;

    private Text[] timerText;

    private int timerIndex;

    public void init() {

        Rectangle background = new Rectangle(Constants.PADDING, Constants.PADDING, Constants.GAME_WIDTH, Constants.GAME_HEIGHT);
        background.setColor(Constants.BACKGROUND_COLOR);
        background.fill();

        Rectangle separator = new Rectangle(Constants.SEPARATOR_XMIN, Constants.PADDING, Constants.SEPARATOR_WIDTH, Constants.GAME_HEIGHT);
        separator.setColor(Color.WHITE);
        separator.fill();

        pausedText = new Text(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2, "PAUSE");
        pausedText.setColor(Color.LIGHT_GRAY);
        pausedText.translate(-pausedText.getWidth() / 2, -pausedText.getHeight() / 2);
        pausedText.grow(pausedText.getWidth() * 3, pausedText.getHeight() * 3);

        timerText = new Text[3];
        timerText[0] = new Text(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2 - 30, "3");
        timerText[0].setColor(Color.LIGHT_GRAY);
        timerText[1] = new Text(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2 - 30, "2");
        timerText[1].setColor(Color.LIGHT_GRAY);
        timerText[2] = new Text(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2 - 30, "1");
        timerText[2].setColor(Color.LIGHT_GRAY);

        timerIndex = 0;

        overText = new Text[2];

        overText[0] = new Text(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2 - 30, "GAME");
        overText[1] = new Text(Constants.GAME_WIDTH / 2, Constants.GAME_HEIGHT / 2 + 30, "OVER");

        overText[0].setColor(Color.LIGHT_GRAY);
        overText[1].setColor(Color.LIGHT_GRAY);

        overText[0].translate(-overText[0].getWidth() / 2, -overText[0].getHeight() / 2);
        overText[1].translate(-overText[1].getWidth() / 2, overText[1].getHeight() / 2);

        overText[0].grow(overText[0].getWidth() * 3, overText[0].getHeight() * 3);
        overText[1].grow(overText[1].getWidth() * 3, overText[1].getHeight() * 3);

    }

    public void drawOverText() {
        overText[0].draw();
        overText[1].draw();
    }

    public void deleteOverText() {
        overText[0].delete();
        overText[1].delete();
    }

    public void updateScore(int score) {

        if (scoreText != null) {
            scoreText.delete();
        }

        if (otherPlayerScoreText != null) {
            otherPlayerScoreText.delete();
        }

        scoreText = new Text(Constants.PADDING + 10, Constants.PADDING + 10, "Score: " + score);
        scoreText.setColor(Color.WHITE);
        scoreText.draw();

        otherPlayerScoreText = new Text(Constants.SEPARATOR_XMIN + Constants.SEPARATOR_WIDTH + 10, Constants.PADDING + 10, "Score: " + score);
        scoreText.setColor(Color.WHITE);
        scoreText.draw();
    }

    public void drawPausedText() {
        pausedText.draw();
    }

    public void deletePausedText() {
        pausedText.delete();
    }

    public void showTimer(int i) {

        if (i - 1 >= 0) {
            timerText[i - 1].delete();
        }

        if (i < 3) {
            timerText[i].draw();
        }
    }

    public void startTimerText() {

        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (timerIndex < 4) {

                    showTimer(timerIndex++);
                } else {

                    this.cancel();
                }
            }
        }, 0, 1000);

    }

}
