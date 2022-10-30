package ru.netology.taskmanager.server;

import com.google.gson.Gson;
import ru.netology.taskmanager.datas.CommandTaks;
import ru.netology.taskmanager.datas.Todos;

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
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                     PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)
                ) {
                    String request = in.readLine();
                    if (request != null) {
                        CommandTaks commandTaks = gson.fromJson(request, CommandTaks.class);
                        switch (commandTaks.getType()) {
                            case ADD:
                                if (!todos.addTask(commandTaks.getTask(), true)) {
                                    out.println("Вы превысили максимальное количество задач! \n");
                                }
                                break;
                            case REMOVE:
                                if (!todos.removeTask(commandTaks.getTask(), true)) {
                                    out.println("Нет такой задачи в списке \n");
                                }
                                break;
                            case RESTORE:
                                todos.restoreTask();
                                break;
                            default:
                                out.println("Неизвестная операция");
                        }
                        String answer = todos.getAllTasks();
                        out.println(answer);
                    } else {
                        out.println("Неверные данные");
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер :(");
            e.printStackTrace();
        }
    }
}
