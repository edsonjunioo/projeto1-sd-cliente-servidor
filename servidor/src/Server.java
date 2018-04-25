

import java.io.*;
import java.math.BigInteger;
import java.net.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;

public class Server {
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
            BigInteger LIMIT = new BigInteger("20");
            BigInteger key = BigInteger.ZERO;
            Queue<Object> queue = new LinkedList<Object>();



            while (true) {
                byte[] receiveData = new byte[1400];
                byte[] sendData = new byte[1400];
                System.out.println("Server Running");
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData());
                //System.out.println("Recebido: " + sentence);
                InetAddress IPAddress = receivePacket.getAddress();
                int port_defined = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();
                //sendData = capitalizedSentence.getBytes();
                //DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
                //serverSocket.send(sendPacket);




                if(key.compareTo(LIMIT) < 0) {
                    key = key.add(new BigInteger("1"));
                    Map<BigInteger,String> message = new HashMap<>();
                    message.put(key,sentence);
                    System.out.println("Recebido:" + message);
                    sendData = capitalizedSentence.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
                    serverSocket.send(sendPacket);
                    Object object = message;
                    queue.add(object);
                    //System.out.println("Fila: " + queue);

                    System.out.println("\nDigite umas das opcoes\n1-visualizar fila\n2-atualizar fila\n3-remover item da fila\n4-sair");
                    int opcao;
                    Scanner ler = new Scanner(System.in);
                    opcao = ler.nextInt();

                    if (opcao == 1){
                        System.out.println("Fila: " + queue);
                    }

                    if (opcao == 3){
                        Object removehead = queue.remove();
                        System.out.println("Fila" + queue);

                    } else {
                        System.out.println("Fila" + queue);
                    }





                } else {
                    System.out.println("Ultrapassou limite da chave para envio de mensagens");
                }
            }

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }



    }


}