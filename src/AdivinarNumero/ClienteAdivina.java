package AdivinarNumero;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ClienteAdivina {
    public static void main(String[] args) {
        String host = "localhost";
        int puerto = 5000;

        try {

            Socket socket = new Socket(host, puerto);
            System.out.println("Conectado al servidor de adivinanzas.");


            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            boolean acertado = false;


            while (!acertado) {
                System.out.print("Introduce un número entre 1 y 20: ");
                String intento = scanner.nextLine();


                salida.println(intento);


                String respuesta = entrada.readLine();

                if (respuesta == null) {
                    System.out.println("Conexión perdida con el servidor.");
                    break;
                }

                System.out.println("Pista del servidor: " + respuesta);

                if (respuesta.equals("¡CORRECTO!")) {
                    acertado = true;
                    System.out.println("¡Enhorabuena, has ganado la partida!");
                }
            }

            scanner.close();
            entrada.close();
            salida.close();
            socket.close();
            System.out.println("Juego finalizado. ¡Hasta la próxima!");

        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
        }
    }
}