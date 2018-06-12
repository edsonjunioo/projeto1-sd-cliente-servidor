import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Thread2 implements Runnable {

    Thread1 thread1 = new Thread1();
    String sentence = thread1.getSentence();
    String host = thread1.getHost();
    String porta = thread1.getPort();


    @Override
    public void run() {

        try {
            DatagramSocket clientSocket = new DatagramSocket();
            byte[] receiveData = new byte[20];
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("Resposta do Servidor:" + modifiedSentence + " recebido com sucesso\n");

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }

}