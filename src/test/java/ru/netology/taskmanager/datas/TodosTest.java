package ru.netology.taskmanager.datas;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class TodosTest {

    Todos todos;
    List<String> testList;

    @BeforeEach
    public void setUp() {
        todos = new Todos();
        testList = new ArrayList<>();
        for (int i = todos.MAX_TASK - 1; i >= 1; i--) {
            testList.add(Integer.toString(i));
        }
        todos.listTodos = testList;
    }

    @Test
    @DisplayName("Проверка добавления в список (без сортировки)")
    void addTaskWithoutSort() {
        Assertions.assertTrue(todos.addTask("7", true));
        Assertions.assertEquals("6 5 45 3 2 1 7", todos.toString());
    }

    @Test
    @DisplayName("Проверка добавления в список больше максимального(без сортировки)")
    void addMaxTaskWithoutSort() {
        Assertions.assertTrue(todos.addTask("7", true));
        Assertions.assertFalse(todos.addTask("8", true));
        Assertions.assertEquals("6 5 4 3 2 1 7", todos.toString());
    }

    @Test
    @DisplayName("Проверка удаления из списка (без сортировки)")
    void removeTaskWithoutSort() {
        Assertions.assertTrue(todos.removeTask("3", true));
        Assertions.assertEquals("6 5 4 2 1", todos.toString());
    }

    @Test
    @DisplayName("Проверка удаления из списка не существующей задачи(без сортировки)")
    void removeTaskNoTask() {
        Assertions.assertFalse(todos.removeTask("10", true));
        Assertions.assertEquals("6 5 4 3 2 1", todos.toString());
    }

    @Test
    @DisplayName("Проверка отмены последнего действия - из условия")
    void restoreTask() {
        Todos restore = new Todos();
        restore.addTask("Первая", true);
        restore.addTask("Вторая", true);
        restore.removeTask("Первая", true);
        restore.addTask("Третья", true);
        restore.restoreTask();
        restore.restoreTask();
        Assertions.assertEquals("Вторая Первая", restore.getAllTasks().replace("#", " ").trim());
    }

    @Test
    @DisplayName("Проверка вывода отсортированного списка")
    void getAllTasks() {
        todos.addTask("0", true);
        Assertions.assertEquals("0 1 2 3 4 5 6", todos.getAllTasks().replace("#", " ").trim());
    }
}