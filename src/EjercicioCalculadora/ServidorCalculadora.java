package EjercicioCalculadora;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorCalculadora {
    public static void main(String[] args) {

        try (ServerSocket servidor = new ServerSocket(5000)) {
            System.out.println("Servidor a la escucha en el puerto 5000...");


            while (true) {
                System.out.println("Esperando cliente ...");

                try (Socket socket = servidor.accept();
                     BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                     PrintWriter salida = new PrintWriter(socket.getOutputStream(), true)) {

                    String linea1 = entrada.readLine();
                    String linea2 = entrada.readLine();


                    if (linea1 != null && linea2 != null) {

                        // Lee los números como texto y los convierte a números
                        int n1 = Integer.parseInt(linea1);
                        int n2 = Integer.parseInt(linea2);

                        int resultado = n1 + n2;
                        salida.println("Resultado: " + resultado);

                        System.out.println("Operación realizada :" + n1 + "+" + n2 + "=" + resultado);
                    }

                } catch (IOException e) {
                    System.err.println("Error procesando la conexión del cliente: " + e.getMessage());
                } catch (NumberFormatException e) {
                    System.err.println("Error: Los datos recibidos no son números enteros.");
                }
            }

        } catch (IOException e) {

            System.err.println("Error: " + e.getMessage());
        }
    }
}