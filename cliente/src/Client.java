import java.io.*;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import org.apache.log4j.Logger;

public class Client {


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
                System.out.println("Client1 Running");
                System.out.println("Digite uma opção para iniciar o CRUD\n");
                System.out.println("1 - create\n");
                System.out.println("2 - read\n");
                System.out.println("3 - update\n");
                System.out.println("4 - delete\n");
                Scanner ler = new Scanner(System.in);
                int option = ler.nextInt();

                if(option == 1) {
                    System.out.println("\nDigite algo para enviar com a chave no seguinte formato: create/chave/msg");
                }

                if(option == 2){
                    System.out.println("\nPara visualizar o mapa digite read");
                }

                if(option == 3){
                    System.out.println("\nDigite chave a atualizar no seguinte formato update/x/msg onde x é a chave a ser atualizada:");
                }

                if(option == 4){
                    System.out.println("\nDigite uma chave a remover no seguinte formato delete/x/ onde x é a chave:\"");
                }






                    BufferedReader inFromUser2 = new BufferedReader(new InputStreamReader(System.in));
                    String sentence = inFromUser2.readLine();



                    DatagramSocket clientSocket = new DatagramSocket();
                    InetAddress IPAddress = InetAddress.getByName(host);
                    //byte[] sendOption = new byte[20];
                    byte[] sendData = new byte[20];
                    byte[] receiveData = new byte[20];
                    //sendOption = option.getBytes();
                    sendData = sentence.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
                    //DatagramPacket sendPacket2 = new DatagramPacket(sendOption, sendOption.length, IPAddress, porta);
                    //clientSocket.send(sendPacket2);
                    clientSocket.send(sendPacket);
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    String modifiedSentence = new String(receivePacket.getData());
                    System.out.println("Resposta do Servidor:" + modifiedSentence + " recebido com sucesso\n");
                    //clientSocket.close();

                }













        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }
}







