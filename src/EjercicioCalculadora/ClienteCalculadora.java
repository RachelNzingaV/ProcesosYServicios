package EjercicioCalculadora;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.net.Socket;
public class ClienteCalculadora {

    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000);
             PrintWriter salida = new PrintWriter(socket.getOutputStream(), true); // autoflush activado
             BufferedReader entrada = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            while (true){

            }
            // Envía  los numeros
            salida.println("5");
            salida.println("3");

            // Imprime el  resultado
            String respuesta = entrada.readLine();
            System.out.println( "Resultado recibido:" + respuesta);

        } catch (IOException e) {
            System.err.println("Error de conexión: " + e.getMessage());
        }
    }

}
