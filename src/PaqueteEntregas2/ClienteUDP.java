package PaqueteEntregas2;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {
    public static void main(String[] args) {
        try {

            DatagramSocket socket = new DatagramSocket();
            InetAddress ipServidor = InetAddress.getByName("localhost");

            Scanner scanner = new Scanner(System.in);
            System.out.print("Escribe un mensaje para el servidor: ");
            String mensajeTexto = scanner.nextLine();


            byte[] datosEnviar = mensajeTexto.getBytes();


            DatagramPacket paqueteEnviar = new DatagramPacket(
                    datosEnviar, datosEnviar.length, ipServidor, 9876
            );

            System.out.println("3. Enviando paquete al servidor...");
            socket.send(paqueteEnviar);


            byte[] bufferRecibo = new byte[1024];
            DatagramPacket paqueteRecibo = new DatagramPacket(bufferRecibo, bufferRecibo.length);

            socket.receive(paqueteRecibo);


            String respuestaServidor = new String(paqueteRecibo.getData(), 0, paqueteRecibo.getLength());
            System.out.println("4. Servidor respondió: " + respuestaServidor);

            socket.close();
            scanner.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
