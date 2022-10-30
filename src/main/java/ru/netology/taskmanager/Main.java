package ru.netology.taskmanager;

import ru.netology.taskmanager.datas.Todos;
import ru.netology.taskmanager.server.TodoServer;

public class Main {

    public static void main(String[] args) {
        Todos todos = new Todos();
        TodoServer server = new TodoServer(8989, todos);
        server.start();
    }
}
