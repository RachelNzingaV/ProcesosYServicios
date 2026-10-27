package PaqueteEntregas2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ConnectException;
import java.net.Socket;

public class ClienteMayus {
    public static void main(String[] args) {
        String direccionServidor = "localhost";
        int puerto = 6000;
        String mensajeAEnviar = "hola, este es un texto de prueba";

        try {

            Socket socket = new Socket(direccionServidor, puerto);
            System.out.println("Conectado exitosamente al servidor en " + direccionServidor + ":" + puerto);


            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            salida.println(mensajeAEnviar);
            System.out.println("Mensaje enviado al servidor: " + mensajeAEnviar);

            String respuestaServidor = entrada.readLine();

            if (respuestaServidor != null) {
                System.out.println("Respuesta recibida del servidor: " + respuestaServidor);
            }

            socket.close();

        } catch (ConnectException e) {
            System.err.println("Error: No se pudo conectar con el Servidor. Asegúrate de que el ServidorTCP esté encendido.");
        } catch (IOException e) {
            System.err.println("Error de E/S en el cliente: " + e.getMessage());
        }
    }
}

