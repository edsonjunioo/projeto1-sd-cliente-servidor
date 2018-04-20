import java.io.*;
import java.net.*;
import java.util.Properties;

public class Servidor
{
    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./servidor/properties/dados.properties");
        props.load(file);
        return props;

    }

    public static void main(String args[]) throws Exception
    {
        String port;

        Properties prop = getProp();

        port = prop.getProperty("prop.server.port");
        int porta = Integer.parseInt(port);

        DatagramSocket serverSocket = new DatagramSocket(porta);
        byte[] receiveData = new byte[1400];
        byte[] sendData = new byte[1400];
        while(true)
        {
            System.out.println("Server Running");
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String( receivePacket.getData());
            System.out.println("Recebido: " + sentence);
            InetAddress IPAddress = receivePacket.getAddress();
            int port_defined = receivePacket.getPort();
            String capitalizedSentence = sentence.toUpperCase();
            sendData = capitalizedSentence.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
            serverSocket.send(sendPacket);
        }
    }
}