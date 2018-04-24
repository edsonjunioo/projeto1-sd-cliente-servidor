import java.io.*;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Scanner;

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

    public Map<BigInteger, String> receberDados() throws Exception {

            Map<BigInteger, String> bigIntegerStringMap = new HashMap<>();

            Scanner ler = new Scanner(System.in);
            System.out.print("Digite uma chave: ");
            BigInteger chave = ler.nextBigInteger();

            System.out.println("\nDigite algo para enviar ao servidor :");
            BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
            String sentence = inFromUser.readLine();


            bigIntegerStringMap.put(chave, sentence);
            System.out.println("\nMensagem a ser enviada: " + bigIntegerStringMap);


       return bigIntegerStringMap;



    }

    public void enviarDados(){

        try {
            String host;
            String port;

            Map<BigInteger, String> bigIntegerStringMap = receberDados();

            Properties prop = getProp();

            host = prop.getProperty("prop.client.host");
            port = prop.getProperty("prop.client.port");
            int porta = Integer.parseInt(port);

            DatagramSocket clientSocket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName(host);
            byte[] sendData = new byte[1400];
            byte[] receiveData = new byte[1400];

            ByteArrayOutputStream a = new ByteArrayOutputStream();
            ObjectOutputStream b = new ObjectOutputStream(a);
            b.writeObject(bigIntegerStringMap);
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
            clientSocket.send(sendPacket);

            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            clientSocket.receive(receivePacket);
            String modifiedSentence = new String(receivePacket.getData());
            System.out.println("Resposta do Servidor:" + modifiedSentence);
            clientSocket.close();

        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }
}







