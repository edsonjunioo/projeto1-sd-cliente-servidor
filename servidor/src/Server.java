import org.apache.log4j.Logger;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;


public class Server {

    //final static Logger logger = Logger.getLogger("server");

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./servidor/properties/dados.properties");
        props.load(file);
        return props;

    }

    public void server() {


        try {

            final Logger logger = Logger.getLogger("server");


            String port;

            Properties prop = getProp();

            port = prop.getProperty("prop.server.port");
            int porta = Integer.parseInt(port);

            DatagramSocket serverSocket = new DatagramSocket(porta);
            logger.info("Conexão do servidor iniciada");
            BigInteger LIMIT = new BigInteger("5120");
            BigInteger key = BigInteger.ZERO;
            Map<BigInteger, String> map = new HashMap<>();

            Queue<Object> queue = new LinkedList<Object>();

            while (true || key.compareTo(LIMIT) < 0) {


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


                queue.add(sentence);
                //sendData = capitalizedSentence.getBytes();
                //DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
                //serverSocket.send(sendPacket);
                if(sentence.contains(""))


                if(sentence.contains("create")) {

                    queue.add(sentence);

                } else {
                    key = key.add(new BigInteger("1"));
                    map.put(key, sentence);
                    logger.info("Mapa" + map);
                    sendData = capitalizedSentence.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
                    serverSocket.send(sendPacket);

                }


                //logger.info("Mapa" + map);
                //sendData = capitalizedSentence.getBytes();
                //DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
                //serverSocket.send(sendPacket);


            }

        } catch (Exception e) {
            //Logger logger = process();
            //logger.error("Erro" + e.getMessage());
            System.out.println("Erro" + e.getMessage());
        }

    }


}