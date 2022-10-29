package ru.netology.taskmanager;

import java.util.ArrayList;
import java.util.List;

public class Todos {
    private List<String> listTodos = new ArrayList<>();
    private final int MAX_TASK = 7;


    public void addTask(String task) {
        if (listTodos.size() < 7){
            listTodos.add(task);
        }
    }

    public void removeTask(String task) {
        if (!listTodos.remove(task)){
            System.out.println("Такой задачи нет в списке");
        }
    }

    public String getAllTasks() {
        StringBuilder sb = new StringBuilder();
        listTodos.stream().sorted().forEach(todo -> sb.append(todo).append(" "));
        String s = sb.toString();
        return s;//sb.toString();
    }

}
