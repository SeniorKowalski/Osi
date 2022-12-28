package ru.kowalski.osi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static final String OK = "OK";
    public static final String NOT_OK = "NOT OK";
    public static final String FIRST_CITY = "???";

    public static void main(String... args) {

        List<String> cities = new ArrayList<>();

        try (ServerSocket server = new ServerSocket(ServerConfig.PORT)) {
            System.out.println("Server started");
            while (true) {
                try (Socket client = server.accept();
                     PrintWriter writer = new PrintWriter(client.getOutputStream(), true);
                     BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()))) {
                    writer.println("What is your name?");
                    final String name = reader.readLine();
                    writer.println(String.format("Hi %s, your port is %d", name, client.getPort()));
                    if (!cities.isEmpty()) {
                        String lastCity = cities.get(cities.size() - 1);
                        writer.println(lastCity);
                        String city = reader.readLine();
                        if (lastCity.endsWith(String.valueOf(city.toLowerCase().charAt(0)))) {
                            writer.println(OK);
                            cities.add(city);
                        } else {
                            writer.println(NOT_OK);
                        }
                    } else {
                        writer.println(FIRST_CITY);
                        String city = reader.readLine();
                        cities.add(city);
                        writer.println(OK);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Can't start server");
            e.printStackTrace();
        }
    }
}
