package org.academiadecodigo.tetris.networking;

import com.sun.deploy.util.SessionState;
import org.academiadecodigo.tetris.Constants;
import org.academiadecodigo.tetris.event.GameEvent;

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

    public NetworkThread(String ipAddress) {

        try {

            socket = new Socket(ipAddress, Constants.DEFAULT_PORT);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (!socket.isClosed()) {

                String str;

                while((str = reader.readLine()) != null) {

                    ClientEventHandler.handleEvent(str);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendEvent(String event) {

        if(!GameEvent.isEvent(event)) {
            System.out.println("Message received is not a valid event!");
            return;
        }

        try {

            PrintWriter writer = new PrintWriter(socket.getOutputStream());

            writer.write(event);
            writer.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
