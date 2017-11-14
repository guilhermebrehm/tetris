package org.academiadecodigo.tetris.server;

import org.academiadecodigo.tetris.Constants;
import org.academiadecodigo.tetris.server.events.GameEvent;
import org.academiadecodigo.tetris.server.events.GameStartEvent;
import org.academiadecodigo.tetris.server.events.StartTimerEvent;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
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

    public void init() {

        executorService = Executors.newFixedThreadPool(2);
        clientHandlers = new LinkedList<>();

        bindSockets();
        waitForConnections();
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
                ClientHandler clientHandler = new ClientHandler(clientSocket);

                clientHandlers.add(clientHandler);
                executorService.submit(clientHandler);

                if(clientHandlers.size() == Constants.MAX_PLAYERS) {
                    break;
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        start();
    }

    private void start() {

        broadcast(new StartTimerEvent());

        try {

            Thread.sleep(3000);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        broadcast(new GameStartEvent());
    }

    public void broadcast(GameEvent event) {

        for (ClientHandler clientHandler: clientHandlers) {

            clientHandler.sendEvent(event);
        }
    }

}
