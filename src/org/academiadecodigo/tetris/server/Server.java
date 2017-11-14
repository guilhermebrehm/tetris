package org.academiadecodigo.tetris.server;

import org.academiadecodigo.tetris.Constants;
import org.academiadecodigo.tetris.server.events.GameEvent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private static Server server;

    private ServerSocket serverSocket;
    private ExecutorService executorService;
    private List<ClientHandler> clientHandlers;

    private Server() { }

    public static Server getInstance() {

        if(server == null) {

            server = new Server();
        }

        return server;
    }

    private void start() {

        executorService = Executors.newFixedThreadPool(2);
        bindSockets();
    }

    private void bindSockets() {

        try {
            serverSocket = new ServerSocket(Constants.DEFAULT_PORT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitForConnections () {

        while(!serverSocket.isClosed()) {

            try {
                Socket clientSocket = serverSocket.accept();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadcast(GameEvent event) {

        for (ClientHandler clientHandler: clientHandlers) {

            clientHandler.sendEvent(event);
        }
    }

}
