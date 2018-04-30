import org.apache.log4j.Logger;

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;


public class Server {

    final static Logger logger = Logger.getLogger("server");

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
            logger.info("Conexão do servidor iniciada");
            BigInteger LIMIT = new BigInteger("4");
            BigInteger key = BigInteger.ZERO;
            Queue<Object> queue = new LinkedList<Object>();



            while (true) {
                byte[] receiveData = new byte[20];
                byte[] sendData = new byte[20];
                System.out.println("Server Running");
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                System.out.println("Recebido: " + sentence);
                InetAddress IPAddress = receivePacket.getAddress();
                int port_defined = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();
                //sendData = capitalizedSentence.getBytes();
                //DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
                //serverSocket.send(sendPacket);




                if(key.compareTo(LIMIT) < 0) {
                    System.out.println("\nQueue Criada/Atualizada");
                    key = key.add(new BigInteger("1"));
                    Map<BigInteger,String> message = new HashMap<>();
                    message.put(key,sentence);
                    //System.out.println("Recebido:" + message);
                    logger.info("Recebido" + message);
                    sendData = capitalizedSentence.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
                    serverSocket.send(sendPacket);
                    Object object = message;
                    queue.add(object);
                    logger.info("Fila: " + queue);
                    System.out.println("Queue: " + queue);

                    System.out.println("\nDigite umas das opcoes\n2-visualizar fila\n3-remove head queue\n4-update queue\n");
                    int opcao;
                    Scanner ler = new Scanner(System.in);
                    opcao = ler.nextInt();

                    if (opcao == 2){
                        System.out.println("Queue: " + queue);
                        logger.info("visualização" + queue);
                    }

                    if (opcao == 3){
                        Object removehead = queue.remove();
                        //System.out.println("Queue" + queue);
                        logger.info("Remoção realizada");
                        logger.info("Fila atualizada" + queue);

                    } else {
                        System.out.println("Queue" + queue);
                    }





                } else {
                    logger.warn("WARN");
                    System.out.println("Ultrapassou limite da chave para envio de mensagens");
                }
            }

        } catch (Exception e) {
            logger.error("Erro");
            System.out.println("Erro" + e.getMessage());
        }



    }


}