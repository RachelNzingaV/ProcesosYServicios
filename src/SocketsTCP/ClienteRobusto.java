package SocketsTCP;

// CLIENTE ROBUSTO (ClienteRobusto.java)
import java.io.*;
import java.net.*;
public class ClienteRobusto {
    public static void main(String[] args) {
        String host = "83.38.46.217";
        int puerto =9999 ;
        try (
                Socket socket = new Socket(host , puerto );
                PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
                BufferedReader entrada = new BufferedReader(
                        new InputStreamReader(socket.getInputStream()))
        ) {
            System.out.println("Conectado al servidor.");
            salida.println("Mensaje de prueba");
            String respuesta = entrada.readLine();
            if (respuesta != null) {
                System.out.println("Respuesta recibida: " + respuesta);
            } else {
                System.err.println("El servidor cerró la conexión sin responder.");
            }
        } catch (UnknownHostException e) {
            System.err.println("[ERROR] Dirección IP/Host no válida.");
        } catch (ConnectException e) {
            System.err.println("[ERROR] Conexión rechazada. Servidor apagado o puerto incorrecto.");
        } catch (SocketException e) {
            System.err.println("[ERROR RED] Error en socket durante transmisión.");
        } catch (IOException e) {
            System.err.println("[ERROR E/S] General: " + e.getMessage());
        }
    }
}
