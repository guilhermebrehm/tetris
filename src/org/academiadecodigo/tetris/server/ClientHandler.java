package org.academiadecodigo.tetris.server;

import org.academiadecodigo.tetris.server.events.GameEvent;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable{

    Socket socket;

    public ClientHandler(Socket socket){

        this.socket = socket;
    }

    @Override
    public void run() {

        while(!socket.isClosed()) {


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
