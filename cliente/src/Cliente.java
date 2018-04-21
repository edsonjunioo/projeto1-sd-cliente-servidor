import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Properties;

public class Cliente implements Runnable{


            public static Properties getProp () throws IOException {
                Properties props = new Properties();
                FileInputStream file = new FileInputStream("./cliente/properties/dados.properties");
                props.load(file);
                return props;

            }

            @Override
            public void run(){

             try {
                String host;
                String port;

                Properties prop = getProp();

                host = prop.getProperty("prop.server.host");
                port = prop.getProperty("prop.server.port");
                int porta = Integer.parseInt(port);


                    System.out.println("Digite algo para enviar ao servidor");
                    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
                    DatagramSocket clientSocket = new DatagramSocket();
                    InetAddress IPAddress = InetAddress.getByName(host);
                    byte[] sendData = new byte[1400];
                    byte[] receiveData = new byte[1400];
                    String sentence = inFromUser.readLine();
                    sendData = sentence.getBytes();
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
                    clientSocket.send(sendPacket);
                    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                    clientSocket.receive(receivePacket);
                    String modifiedSentence = new String(receivePacket.getData());
                    System.out.println("Resposta do Servidor:" + modifiedSentence);
                    clientSocket.close();


            } catch (Exception e){
                 System.out.println("Erro: " + e.getMessage());
             }
        }

}

