import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Properties;
import java.util.Scanner;

public class Thread1 implements Runnable {

    String sentence;

    public String getSentence(){
        return sentence;
    }

    public void setSentence(String sentence){
        this.sentence = sentence;
    }

    public static Properties getProp() throws Exception{
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./cliente/properties/dados.properties");
        props.load(file);
        return props;

    }

    String host;

    String port;

    public String getHost(){
        return host;
    }

    public String getPort(){
        return port;
    }





    @Override
    public void run() {

        try {

            Properties prop = getProp();

            host = prop.getProperty("prop.client.host");
            port = prop.getProperty("prop.client.port");
            int porta = Integer.parseInt(port);

            while (true) {
                System.out.println("Client1 Running");
                System.out.println("Digite uma opção de 1 a 4 para o CRUD ou 5 para reinicializar o disco \n");
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

                if(option == 5){
                    System.out.println("\nDigite limpar para reinicilizar o disco\"");
                }


                BufferedReader inFromUser2 = new BufferedReader(new InputStreamReader(System.in));
                sentence = inFromUser2.readLine();

                DatagramSocket clientSocket = new DatagramSocket();
                InetAddress IPAddress = InetAddress.getByName(host);
                byte[] sendData = new byte[20];
                sendData = sentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
                clientSocket.send(sendPacket);

            }


        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }


    }

