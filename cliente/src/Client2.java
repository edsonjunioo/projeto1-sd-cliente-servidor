import java.io.*;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.*;
import org.apache.log4j.Logger;

public class Client2 {
    /*
    DatagramSocket client = null;
    Socket c = null;
    PrintStream ps = null;
    private boolean verifica_conexao = false;
    */

    public static Properties getProp() throws Exception{
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./cliente/properties/dados.properties");
        props.load(file);
        return props;

    }



    /*

    public void criarConection() {
        try {
            String host;
            String port;

            Properties prop = getProp();

            host = prop.getProperty("prop.client.host");
            port = prop.getProperty("prop.client.port");
            int porta = Integer.parseInt(port);
            client = new DatagramSocket(porta);
            verifica_conexao = true;
            System.out.println("Conexão Realizada");

        } catch (IOException e) {
            System.out.println("Algum problema ocorreu para criar ou receber o socket.");
        }


    }
    */

    /*
    public String receberDados() throws Exception {



            System.out.println("\nDigite algo para enviar ao servidor :");
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            String sentence = inFromUser.readLine();
            BigInteger key = BigInteger.ONE;

            Map<BigInteger,String> map = new HashMap<>();
            map.put(key,sentence);
            System.out.println("mapa" + map);





       return sentence;



    }

    */

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
            logger.info("Fila" + queue);
            //System.out.println("Digite 1 para incluir nova mensagem ao mapa ou 2 para CRUD");
            //Scanner ler = new Scanner(System.in);
            //int option = ler.nextInt();

            while (true) {

                System.out.println("Digite uma opção\n");
                System.out.println("1 - create\n");
                System.out.println("2 - read\n");
                System.out.println("3 - update\n");
                System.out.println("4 - delete\n");
                Scanner ler = new Scanner(System.in);
                int option = ler.nextInt();



                if(option == 1) {
                    System.out.println("\nDigite algo para enviar :");
                    BufferedReader inFromUser2 = new BufferedReader(new InputStreamReader(System.in));
                    String sentence = inFromUser2.readLine();

                    queue.add(sentence);
                    logger.info("Fila" + queue);


                    DatagramSocket clientSocket = new DatagramSocket();
                    InetAddress IPAddress = InetAddress.getByName(host);
                    byte[] sendOption = new byte[20];
                    byte[] sendData = new byte[20];
                    byte[] receiveData = new byte[20];
                    sendData = sentence.getBytes();

                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
                    clientSocket.send(sendPacket);
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    String modifiedSentence = new String(receivePacket.getData());
                    System.out.println("Resposta do Servidor:" + modifiedSentence + " recebido com sucesso");
                    //clientSocket.close();

                }

            }







        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }
}







