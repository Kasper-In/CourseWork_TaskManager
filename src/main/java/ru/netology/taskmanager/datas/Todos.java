package ru.netology.taskmanager.datas;

import java.util.ArrayList;
import java.util.List;

public class Todos {
    protected List<String> listTodos = new ArrayList<>();
    protected TodosHist todosHist = new TodosHist();
    protected final int MAX_TASK = 7;


    public boolean addTask(String task, boolean isNeedHist) {
        if (listTodos.size() < MAX_TASK) {
            listTodos.add(task);
            if (isNeedHist) {
                todosHist.addHist(new CommandTaks(Command.ADD, task));
            }
            return true;
        } else {
            return false;
        }
    }

    public boolean removeTask(String task, boolean isNeedHist) {
        if (listTodos.remove(task)) {
            if (isNeedHist) {
                todosHist.addHist(new CommandTaks(Command.REMOVE, task));
            }
            return true;
        } else {
            return false;
        }
    }

    public void restoreTask() {
        CommandTaks lastCommandTask = todosHist.lastHist();
        if (lastCommandTask.getType() == Command.ADD) {
            removeTask(lastCommandTask.getTask(), false);
        } else {
            addTask(lastCommandTask.getTask(), false);
        }
        todosHist.removeLastHist();
    }

    public String getAllTasks() {
        StringBuilder sb = new StringBuilder();
        sb.append("Список задач:").append("#");
        listTodos.stream().sorted().forEach(todo -> sb.append(todo).append("#"));
        return sb.toString();
    }

}
