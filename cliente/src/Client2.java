import java.io.*;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import org.apache.log4j.Logger;

public class Client2 {


    public static Properties getProp() throws Exception{
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./cliente/properties/dados.properties");
        props.load(file);
        return props;

    }

    public void enviarDados(){

        final Logger logger = Logger.getLogger("server");

        try {
            String host;
            String port;

            Properties prop = getProp();

            host = prop.getProperty("prop.client.host");
            port = prop.getProperty("prop.client.port");
            int porta = Integer.parseInt(port);
            Queue<Object> queue = new LinkedList<Object>();
            logger.info("Fila de comandos" + queue);
            //System.out.println("Digite 1 para incluir nova mensagem ao mapa ou 2 para CRUD");
            //Scanner ler = new Scanner(System.in);
            //int option = ler.nextInt();

            while (true) {

                System.out.println("Digite uma opção\n");
                System.out.println("create\n");
                System.out.println("read\n");
                System.out.println("update\n");
                System.out.println("delete\n");
                Scanner ler = new Scanner(System.in);
                String option = ler.next();


                if(option.contains("delele")){
                    System.out.println("Digite uma chave a remover");
                }

                if(option.contains("create")) {
                    System.out.println("\nDigite algo para enviar :");
                }

                if(option.contains("update")){
                    System.out.println("\nDigite chave a atualizar:");
                }

                    BufferedReader inFromUser2 = new BufferedReader(new InputStreamReader(System.in));
                    String sentence = inFromUser2.readLine();


                    queue.add(option);
                    System.out.println("Fila" + queue);


                    DatagramSocket clientSocket = new DatagramSocket();
                    InetAddress IPAddress = InetAddress.getByName(host);
                    byte[] sendOption = new byte[20];
                    byte[] sendData = new byte[20];
                    byte[] receiveData = new byte[20];
                    sendOption = option.getBytes();
                    sendData = sentence.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
                    DatagramPacket sendPacket2 = new DatagramPacket(sendOption, sendOption.length, IPAddress, porta);
                    clientSocket.send(sendPacket);
                    clientSocket.send(sendPacket2);
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    String modifiedSentence = new String(receivePacket.getData());
                    System.out.println("Resposta do Servidor:" + modifiedSentence + " recebido com sucesso");
                    //clientSocket.close();

                }













        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }
}







