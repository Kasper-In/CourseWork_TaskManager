package ru.netology.taskmanager.datas;

import java.util.ArrayList;
import java.util.List;

public class Todos {
    private List<String> listTodos = new ArrayList<>();
    private final int MAX_TASK = 7;


    public boolean addTask(String task) {
        if (listTodos.size() < MAX_TASK) {
            listTodos.add(task);
            return true;
        } else {
            return false;
        }
    }

    public boolean removeTask(String task) {
        return listTodos.remove(task);
    }

    public String getAllTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Список задач:").append("#");
        listTodos.stream().sorted().forEach(todo -> sb.append(todo).append("#"));
        return sb.toString();
    }

}
