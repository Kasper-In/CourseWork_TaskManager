package ru.netology.taskmanager.client;

import com.google.gson.Gson;
import ru.netology.taskmanager.datas.Command;
import ru.netology.taskmanager.datas.CommandTaks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private static final int PORT = 8989;
    private static final String HOST = "localHost";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Gson gson = new Gson();
        CommandTaks commandTaks;
        System.out.println("Тебя приветствует менеджер задач. Я могу добавлять и удалять задачи");
        while (true) {
            try (Socket clientSocket = new Socket(HOST, PORT);
                 PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
            ) {
                System.out.println("Введи номер задачи и ее название через пробел:"
                        + "\n 1 - добавить задачу; 2 - Удалить задачу; 3 - Отменить последнее действие");
                String input = sc.nextLine();
                if (input.equals("end")) {
                    out.println("end");
                    System.out.println("Всего доброго");
                    break;
                } else {
                    String[] request = input.split(" ", 2);
                    String numberCommand = request[0];
                    String task = null;
                    if (request.length > 1) {
                        task = request[1];
                    }
                    commandTaks = new CommandTaks(toCommand(numberCommand), task);
                    String output = gson.toJson(commandTaks);
                    out.println(output);
                    String answerServer = in.readLine();
                    System.out.println(answerServer.replace("#", "\n"));

                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static Command toCommand(String numberCommand) {
        switch (numberCommand) {
            case "1":
                return Command.ADD;
            case "2":
                return Command.REMOVE;
            case "3":
                return Command.RESTORE;
        }
        return Command.NULL;
    }
}
