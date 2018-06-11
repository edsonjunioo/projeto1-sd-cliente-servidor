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
            Map<BigInteger, String> map = new HashMap<>();

            Queue<Object> queue = new LinkedList<Object>();

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

                if(sentence.contains("create") || sentence.contains("read") || sentence.contains("update") || sentence.contains("delete")) {
                    queue.add(sentence);
                }

                logger.info(queue);


                if (((LinkedList<Object>) queue).getFirst().toString().contains("create")) {
                    String[] url = sentence.split("/");

                    int chave = Integer.parseInt(url[1]);
                    BigInteger key1 = BigInteger.valueOf(chave);
                    map.put(key1, url[2]);
                }


                if (((LinkedList<Object>) queue).getFirst().toString().contains("read")) {

                        logger.info(map);

                        logger.info(queue);

                }

                if (((LinkedList<Object>) queue).getFirst().toString().contains("update")) {
                    String[] url = sentence.split("/");

                    int chave = Integer.parseInt(url[1]);
                    BigInteger key1 = BigInteger.valueOf(chave);
                    map.remove(key1);
                    map.put(key1, url[2]);
                }



                if (((LinkedList<Object>) queue).getFirst().toString().contains("delete")) {
                    String[] url = sentence.split("/");

                    int chave = Integer.parseInt(url[1]);
                    BigInteger key1 = BigInteger.valueOf(chave);
                    map.remove(key1);
                }

                if (sentence.contains("limpar")) {
                    logger.info("mapa reinicializado");
                    map.clear();
                    queue.clear();
                    logger.info(map);
                    logger.info(queue);

                }


            //logger.info("Mapa" + map);
            //sendData = capitalizedSentence.getBytes();
            //DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
            //serverSocket.send(sendPacket);

            logger.info("Mapa" + map);
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