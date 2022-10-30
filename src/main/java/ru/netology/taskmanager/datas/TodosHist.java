package ru.netology.taskmanager.datas;

import java.util.ArrayList;
import java.util.List;

public class TodosHist {
    private List<CommandTaks> listHist = new ArrayList<>();

    public void addHist(CommandTaks commandTaks) {
        listHist.add(commandTaks);
    }

    public CommandTaks lastHist() {
        return listHist.get(listHist.size() - 1);
    }

    public void removeLastHist() {
        listHist.remove(listHist.size() - 1);
    }
}
