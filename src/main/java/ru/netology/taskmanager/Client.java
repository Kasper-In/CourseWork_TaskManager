package ru.netology.taskmanager;

import com.google.gson.Gson;

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
        OperTask operTask;
        System.out.println("Тебя приветствует менеджер задач. Я могу добавлять и удалять задачи");
        while (true) {
            System.out.println("Введи номер задачи и ее название через пробел:"
                    + "\n 1 - добавить задачу; 2 - Удалить задачу");
            String input = sc.nextLine();
            if (input.equals("end")) {
                break;
            } else {
                String[] request = input.split(" ");
                if (request.length != 2) {
                    System.out.println("Неверный ввод: введено не 2 значения. Попробуйте еще раз");
                } else {
                    try (Socket clientSocket = new Socket(HOST, PORT);
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))
                    ) {
                        String oper = request[0];
                        String task = request[1];
                        operTask = new OperTask(toCommand(oper), task);
                        String output = gson.toJson(operTask);
                        out.println(output);
                        String answerServer = in.readLine();
                        System.out.println(answerServer.replace(" ", "\n"));
                    } catch (IOException e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
    }

    private static Command toCommand(String oper){
        switch (oper){
            case "1": return Command.ADD;
            case "2": return Command.REMOVE;
            case "3": return Command.RESTORE;
            default:
                throw new IllegalArgumentException("Неверный номер команды");
        }
    }
}
