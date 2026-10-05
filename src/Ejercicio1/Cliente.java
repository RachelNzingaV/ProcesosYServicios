package Ejercicio1;

import java.io.PrintWriter;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;




public class Cliente{
    public static void main(String[] args) {

        try {
            Socket socket = new Socket ("localhost", 5000);

            System.out.println("Conexion establecida correctamente");

            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            salida.println("Hola , servidor");

            socket.close();
        } catch (Exception e) {
            System.out.println("No se ha podido establecer la conexion" + e.getMessage());
        }
    }
}