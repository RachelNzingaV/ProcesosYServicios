package Excepciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorExcep {
    public static void main(String[] args) {
        int puerto = 50000;


        try (ServerSocket serverSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor iniciado en el puerto " + puerto + ". Esperando conexión...");

            try (Socket socket = serverSocket.accept();
                 BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                 PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                System.out.println("Cliente conectado desde: " + socket.getInetAddress());
                String mensaje;
                while ((mensaje = entrada.readLine()) != null) {
                    if (mensaje.equalsIgnoreCase("salir")) {
                        System.out.println("El cliente ha solicitado salir. Cerrando conexión...");
                        break;
                    }
                    System.out.println("Cliente dice: " + mensaje);
                    salida.println("Mensaje recibido por el servidor");
                }
            } catch (IOException e) {
                System.err.println("Error de comunicación durante la conexión con el cliente: " + e.getMessage());
            }

        } catch (IOException e) {
            System.err.println("Error grave: No se pudo iniciar el servidor en el puerto " + puerto + ".");
            System.err.println("Detalle: " + e.getMessage());
        }
    }
}
