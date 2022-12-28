package ru.kowalski.osi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import static ru.kowalski.osi.ServerConfig.HOST;

public class Client {

    public static void main(String... args) {

        try (Socket socket = new Socket(HOST, ServerConfig.PORT);
             PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            System.out.println(reader.readLine());
            String name = scanner.nextLine();
            writer.println(name);
            System.out.println(reader.readLine());
            System.out.println(reader.readLine());
            String city = scanner.nextLine();
            writer.println(city);
            System.out.println(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
