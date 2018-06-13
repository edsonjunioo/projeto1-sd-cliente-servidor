import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;


public class Thread1 implements Runnable {

    //Thread2 thread2 = new Thread2();
    //Map<BigInteger,String> map = thread2.getMap();


    Queue<Object> queue = new LinkedList<Object>();

    Map<BigInteger, String> map = new HashMap<>();

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./servidor/properties/dados.properties");
        props.load(file);
        return props;

    }



    @Override
    synchronized public void run(){
        try {

            final Logger logger = Logger.getLogger("server");


            String port;

            Properties prop = getProp();

            port = prop.getProperty("prop.server.port");
            int porta = Integer.parseInt(port);

            DatagramSocket serverSocket = new DatagramSocket(porta);
            logger.info("Conexão do servidor iniciada");


            while (true) {


                byte[] receiveData = new byte[20];
                byte[] sendData = new byte[20];
                System.out.println("\nServer Running");
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                //System.out.println("Recebido: " + sentence);
                logger.info("Recebido: " + sentence);
                InetAddress IPAddress = receivePacket.getAddress();
                int port_defined = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();
                //sendData = capitalizedSentence.getBytes();
                //DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
                //serverSocket.send(sendPacket);

                if(sentence.contains("create") || sentence.contains("read") || sentence.contains("update") || sentence.contains("delete") || sentence.contains("limpar")) {
                    queue.add(sentence);
                }

                logger.info(queue);

                String mensagem = ((LinkedList<Object>) queue).getFirst().toString();




                Thread2 thread2 = new Thread2(mensagem);
                Thread t2 = new Thread(thread2);
                t2.start();

                ((LinkedList<Object>) queue).removeFirst();






                //logger.info("Mapa" + map);
                //sendData = capitalizedSentence.getBytes();
                //DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
                //serverSocket.send(sendPacket);

                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
                serverSocket.send(sendPacket);

            }

        } catch(
                Exception e)

        {
            //Logger logger = process();
            //logger.error("Erro" + e.getMessage());
            System.out.println("Erro" + e.getMessage());
        }
    }
}
