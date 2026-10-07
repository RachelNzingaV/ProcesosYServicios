package AdivinarNumero;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;


public class ServidorAdivina {
    public static void main(String[] args) {
        int puerto = 5000;

        try {

            ServerSocket serverSocket = new ServerSocket(puerto);
            System.out.println("Servidor iniciado. Esperando conexión en el puerto " + puerto + "...");

            Socket socket = serverSocket.accept();
            System.out.println("¡Cliente conectado con éxito!");


            Random random = new Random();
            int numeroSecreto = random.nextInt(20) + 1;

            BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter salida = new PrintWriter(socket.getOutputStream(), true);

            boolean juegoTerminado = false;


            while (!juegoTerminado) {
                String textoRecibido = entrada.readLine();

                if (textoRecibido == null) {
                    System.out.println("El cliente se ha desconectado.");
                    break;
                }

                int numeroCliente = Integer.parseInt(textoRecibido.trim());
                System.out.println("El cliente ha probado con el número: " + numeroCliente);


                if (numeroCliente < numeroSecreto) {
                    salida.println("MAYOR");
                } else if (numeroCliente > numeroSecreto) {
                    salida.println("MENOR");
                } else {
                    salida.println("¡CORRECTO!");
                    juegoTerminado = true;
                    System.out.println("¡El cliente ha acertado el número!");
                }
            }


            entrada.close();
            salida.close();
            socket.close();
            serverSocket.close();
            System.out.println("Servidor cerrado.");

        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }
    }
}