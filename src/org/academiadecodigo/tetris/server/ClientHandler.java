package org.academiadecodigo.tetris.server;

import org.academiadecodigo.tetris.event.GameEvent;
import org.academiadecodigo.tetris.event.GameEventFactory;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable{

    Socket socket;

    public ClientHandler(Socket socket){

        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String str;

            while((str = bufferedReader.readLine()) != null) {

                if(!socket.isClosed()) {

                    System.out.println("received: " + str);

                    Server.getInstance().broadcast(GameEventFactory.getEventByString(str));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sendEvent(GameEvent event) {

        if (!socket.isClosed()) {

            try {

                PrintWriter socketWriter = new PrintWriter(new BufferedOutputStream(socket.getOutputStream()));
                socketWriter.print(event);
                socketWriter.println();
                socketWriter.flush();

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

}
