import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.util.Map;
import java.util.Properties;

public class Server
{
    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./servidor/properties/dados.properties");
        props.load(file);
        return props;

    }


    public void server() {
    try {


        String port;

        Properties prop = getProp();

        port = prop.getProperty("prop.server.port");
        int porta = Integer.parseInt(port);

        DatagramSocket serverSocket = new DatagramSocket(porta);
        while (true) {
            byte[] receiveData = new byte[1400];
            byte[] sendData = new byte[1400];
            System.out.println("Server Running");
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            System.out.println("Recebido: " + sentence);
            InetAddress IPAddress = receivePacket.getAddress();
            int port_defined = receivePacket.getPort();
            //1
            //ByteArrayOutputStream a = new ByteArrayOutputStream();
            //ObjectOutputStream b = new ObjectOutputStream(a);
            //b.writeObject(data2);


            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
            serverSocket.send(sendPacket);


        }




        }catch(Exception e){
        System.out.println("Erro: " + e.getMessage());
        }
    }
}