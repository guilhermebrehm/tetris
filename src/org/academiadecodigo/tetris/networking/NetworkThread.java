package org.academiadecodigo.tetris.networking;

import org.academiadecodigo.tetris.Constants;
import org.academiadecodigo.tetris.event.GameEvent;
import org.academiadecodigo.tetris.event.GameEventFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by codecadet on 14/11/2017.
 */
public class NetworkThread implements Runnable {

    private Socket socket;
    private static NetworkThread networkThread;

    private NetworkThread(){}

    public static NetworkThread getInstance(){

        if(networkThread == null) {

            networkThread = new NetworkThread();
        }

        return networkThread;
    }

    @Override
    public void run() {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!socket.isClosed()) {

                String str;

                while((str = reader.readLine()) != null) {

                    ClientEventHandler.handleEvent(GameEventFactory.getEventByString(str));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendEvent(GameEvent gameEvent) {

        try {

            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            writer.write(gameEvent.toString());
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void connect(String ipAddress) {

        try {

            socket = new Socket(ipAddress, Constants.DEFAULT_PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
