package Ejercicio1;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
    public static void main(String[] args) {
        try {
            ServerSocket Servidor = new ServerSocket(5000);

            System.out.println("Servidor iniciado.");
            System.out.println("Esperando cliente");

            Socket Cliente = Servidor.accept();

            System.out.println("Cliente conectado correctamente");

            Cliente.close();
            Servidor.close();

            BufferedReader entrada = new BufferedReader (new InputStreamReader(Cliente.getInputStream()));

            String mensaje = entrada.readLine();

            System.out.println("Mensaje recibido:" + mensaje);

            Cliente.close();
            Servidor.close();

        } catch (Exception e) {
            System.out.println("Error" + e.getMessage());
        }
    }



}
