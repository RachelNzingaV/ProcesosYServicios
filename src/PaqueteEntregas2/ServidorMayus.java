package PaqueteEntregas2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.BindException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorMayus {
    public static void main(String[] args) {
        int puerto = 6000;


        try (ServerSocket servidorSocket = new ServerSocket(puerto)) {
            System.out.println("Servidor TCP iniciado y escuchando en el puerto " + puerto + "...");


            while (true) {
                try {

                    Socket socketCliente = servidorSocket.accept();
                    System.out.println("¡Cliente conectado desde: " + socketCliente.getInetAddress() + "!");

                    BufferedReader entrada = new BufferedReader(new InputStreamReader(socketCliente.getInputStream()));
                    PrintWriter salida = new PrintWriter(socketCliente.getOutputStream(), true);


                    String mensajeRecibido = entrada.readLine();

                    if (mensajeRecibido != null) {
                        System.out.println("Mensaje recibido: " + mensajeRecibido);


                        String mensajeMayusculas = mensajeRecibido.toUpperCase();


                        salida.println(mensajeMayusculas);
                    }


                    socketCliente.close();
                    System.out.println("Conexión con el cliente cerrada.\n");

                } catch (IOException e) {
                    System.err.println("Error al procesar la comunicación con un cliente: " + e.getMessage());
                }
            }

        } catch (BindException e) {

            System.err.println("Error crítico: El puerto " + puerto + " ya se encuentra ocupado por otra aplicación.");
        } catch (IOException e) {
            System.err.println("Error de E/S en el servidor: " + e.getMessage());
        }
    }
}

