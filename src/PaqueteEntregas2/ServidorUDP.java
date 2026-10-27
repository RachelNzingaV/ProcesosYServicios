package PaqueteEntregas2;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ServidorUDP {
    public static void main(String[] args) {
        try {
            System.out.println("1. Creando socket del servidor en el puerto 9876...");
            DatagramSocket socket = new DatagramSocket(9876);


            byte[] buffer = new byte[1024];
            DatagramPacket paqueteRecibido = new DatagramPacket(buffer, buffer.length);

            System.out.println("2. Esperando mensaje del cliente...");
            socket.receive(paqueteRecibido);


            String mensajeTexto = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            System.out.println("3. Mensaje recibido (en String): " + mensajeTexto);


            InetAddress ipCliente = paqueteRecibido.getAddress();
            int puertoCliente = paqueteRecibido.getPort();

            String respuestaTexto = "¡Hola cliente, mensaje recibido con éxito!";
            byte[] datosRespuesta = respuestaTexto.getBytes();

            DatagramPacket paqueteRespuesta = new DatagramPacket(
                    datosRespuesta, datosRespuesta.length, ipCliente, puertoCliente
            );

            socket.send(paqueteRespuesta);
            System.out.println("4. Respuesta enviada al cliente.");

            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
