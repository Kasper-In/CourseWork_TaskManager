package ru.netology.taskmanager;

import com.google.gson.Gson;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TodoServer {
    private final int PORT;
    private Todos todos;

    public TodoServer(int port, Todos todos) {
        this.PORT = port;
        this.todos = todos;
    }

    public void start() {
        System.out.println("Сервер стартовал на порту " + PORT + "...");
        Gson gson = new Gson();
        try (ServerSocket serverSocket = new ServerSocket(PORT)){
            while (true){
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
                    ){
                    String request = in.readLine();
                    OperTask operTask = gson.fromJson(request, OperTask.class);
                    switch (operTask.getType()){
                        case ADD:
                            todos.addTask(operTask.getTask());
                            break;
                        case REMOVE:
                            todos.removeTask(operTask.getTask());
                            break;
                        default:
                            System.out.println("Неизвестная операция: " + operTask.getType());
                    }
                    String answer = todos.getAllTasks();
                    out.println(answer);
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер :(");
            e.printStackTrace();
        }
    }
}
