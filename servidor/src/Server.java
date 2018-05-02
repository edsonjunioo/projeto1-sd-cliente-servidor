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

    public Logger process(){

        //Queue<Object> queue = instaciaFila();

        final Logger logger = Logger.getLogger("server");

        Queue<Object> queueLog = instaciaFila();
        logger.info("Log em disco");
        logger.info("Fila: " + queueLog);
        return logger;
    }


    public Queue<Object> instaciaFila(){
        final Logger logger = Logger.getLogger("server");

        Queue<Object> queue = new LinkedList<Object>();

        logger.info("Estado da fila" + queue);
        return queue;
    }


    public void server() {



        try {

            Logger logger = process();


            String port;

            Properties prop = getProp();

            port = prop.getProperty("prop.server.port");
            int porta = Integer.parseInt(port);

            DatagramSocket serverSocket = new DatagramSocket(porta);
            logger.info("Conexão do servidor iniciada");
            BigInteger LIMIT = new BigInteger("2048");
            BigInteger key = BigInteger.ZERO;
            Queue<Object> queue = instaciaFila();



            while (true) {
                byte[] receiveData = new byte[1400];
                byte[] sendData = new byte[1400];
                System.out.println("Server Running");
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




                if(key.compareTo(LIMIT) < 0) {
                    logger.info("1 - Fila Criada");
                    key = key.add(new BigInteger("1"));
                    Map<BigInteger, String> message = new HashMap<>();
                    message.put(key, sentence);
                    //System.out.println("Recebido:" + message);
                    //logger.info("Recebido" + message);
                    sendData = capitalizedSentence.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
                    serverSocket.send(sendPacket);
                    Object object = message;
                    queue.add(object);
                    logger.info("Fila atualizada: " + queue);
                    //System.out.println("Queue: " + queue);

                    System.out.println("\nDigite umas das opcoes\n2-visualizar fila\n3-remove head queue\n4-update queue\n5-zerar fila e reinicializar");
                    int opcao;
                    Scanner ler = new Scanner(System.in);
                    opcao = ler.nextInt();

                    if (opcao == 2) {
                        System.out.println("Opção 2 - visualizar fila");
                        logger.info("visualização" + queue);
                    }

                    if (opcao == 3) {
                        System.out.println("Opção 3 - remove head queue");
                        Object removehead = queue.remove();
                        //System.out.println("Queue" + queue);
                        logger.info("Remoção realizada");
                        //logger.info("Fila atualizada" + queue);

                    }

                    if (opcao == 5){
                        System.out.println("Opção 5 - zerar fila e reinicializar");
                        logger.info("Mapa recuperado" + queue);
                        queue.clear();
                        logger.info("Fila Zerada e Reinicializada" + queue);
                        //logger.info("Mapa recuperado" + queueBackup);
                    }

                    else {
                        logger.info("Queue" + queue);
                        System.out.println("Queue" + queue);
                    }





                } else {
                    //logger.warn("Ultrapassou limite da chave para envio de mensagens");
                    System.out.println("Ultrapassou limite da chave para envio de mensagens");
                }
            }


        } catch (Exception e) {
            //Logger logger = process();
            //logger.error("Erro" + e.getMessage());
            System.out.println("Erro" + e.getMessage());
        }

    }


}