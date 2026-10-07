package Excepciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;
import java.util.Scanner;

public class ClienteExcep {
    public static void main(String[] args) {
        String host = "127.0.0.1";
        int puerto = 50000;

        try (Socket socket = new Socket(host, puerto);
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Conectado al servidor. Escribe tu mensaje (escribe 'salir' para terminar):");

            while (true) {
                System.out.print("> ");
                String mensaje = scanner.nextLine();

                salida.println(mensaje);


                if (mensaje.equalsIgnoreCase("salir")) {
                    break;
                }


                String respuesta = entrada.readLine();
                if (respuesta == null) {
                    System.err.println("El servidor ha cerrado la conexión.");
                    break;
                }
                System.out.println("Servidor: " + respuesta);
            }
            System.out.println("Cerrando socket y saliendo de la aplicación correctamente.");

        } catch (ConnectException e) {
            System.err.println("Fallo de conexión: No se ha podido contactar con el servidor en " + host + ":" + puerto);
            System.err.println("¿Está el servidor arrancado?");
        } catch (IOException e) {
            System.err.println("Error de entrada/salida o de comunicación: " + e.getMessage());
        }
    }
}
