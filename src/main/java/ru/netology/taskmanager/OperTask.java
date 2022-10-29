package ru.netology.taskmanager;

public class OperTask {
    private TypeOper typeOper;
    private String task;

    public OperTask(TypeOper typeOper, String task) {
        this.typeOper = typeOper;
        this.task = task;
    }

    public TypeOper getTypeOper() {
        return typeOper;
    }

    public String getTask() {
        return task;
    }
}
