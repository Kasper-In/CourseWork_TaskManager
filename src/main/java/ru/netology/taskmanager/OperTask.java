package ru.netology.taskmanager;

public class OperTask {
    private Command type;
    private String task;

    public OperTask(Command type, String task) {
        this.type = type;
        this.task = task;
    }

    public Command getType() {
        return type;
    }

    public String getTask() {
        return task;
    }
}
