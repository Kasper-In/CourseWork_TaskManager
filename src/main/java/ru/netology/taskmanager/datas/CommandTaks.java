package ru.netology.taskmanager.datas;

public class CommandTaks {
    private Command type;
    private String task;

    public CommandTaks(Command type, String task) {
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
