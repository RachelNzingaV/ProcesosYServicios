package SocketsTCP;

import java.io.*;
import java.net.*;

public class ServidorRobusto {
    public static void main(String[] args) {
        int puerto = 9999;

        try (ServerSocket servidor = new ServerSocket(puerto)) {
            System.out.println("Servidor escuchando en puerto " + puerto);

            while (true) {
                try (Socket cliente = servidor.accept()) {
                    System.out.println("Cliente conectado: " + cliente.getInetAddress());
                    cliente.setSoTimeout(5000); // 5s máx de espera

                    BufferedReader entrada = new BufferedReader(
                            new InputStreamReader(cliente.getInputStream()));
                    PrintWriter salida = new PrintWriter(
                            cliente.getOutputStream(), true);

                    String linea;
                    while ((linea = entrada.readLine()) != null) {
                        if (linea.equalsIgnoreCase("FIN")) break;
                        salida.println("ECO: " + linea);
                    }
                    System.out.println("Cliente desconectado limpiamente.");

                } catch (SocketTimeoutException e) {
                    System.out.println("Tiempo de espera agotado para el cliente.");
                } catch (IOException e) {
                    System.out.println("Error con el cliente: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}
