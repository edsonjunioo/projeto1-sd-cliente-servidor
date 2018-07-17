import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Thread1 implements Runnable {



    public Thread1(DatagramSocket clientSocket){
        RunClient.clientSocket = clientSocket;

    }


    @Override
    synchronized public void run() {

        final Logger logger = Logger.getLogger("client");

        logger.info("passou pelo metodo run da thread 1");

        try {

            String host = RunClient.getProp().getProperty("prop.client.host");
            String port = RunClient.getProp().getProperty("prop.client.port");
            int porta = Integer.parseInt(port);

            while (true) {
                System.out.println("Client1 Running");
                System.out.println("Digite uma opção de 1 a 4 para o CRUD\n");
                System.out.println("1 - create\n");
                System.out.println("2 - read\n");
                System.out.println("3 - update\n");
                System.out.println("4 - delete\n");
                Scanner ler = new Scanner(System.in);
                int option = ler.nextInt();

                if (option == 1) {
                    System.out.println("\nDigite algo para enviar com a chave no seguinte formato: create/chave/msg");
                }

                if (option == 2) {
                    System.out.println("\nPara visualizar uma chave do mapa digite read/x/ onde x é a chave");
                }

                if (option == 3) {
                    System.out.println("\nDigite chave a atualizar no seguinte formato update/x/msg onde x é a chave a ser atualizada:");
                }

                if (option == 4) {
                    System.out.println("\nDigite uma chave a remover no seguinte formato delete/x/ onde x é a chave:\"");
                }

                if (option == 5) {
                    System.out.println("\nDigite limpar para reinicilizar o disco\"");
                }


                BufferedReader inFromUser2 = new BufferedReader(new InputStreamReader(System.in));
                RunClient.sentence = inFromUser2.readLine();

                InetAddress IPAddress = InetAddress.getByName(host);
                byte[] sendData;
                sendData = RunClient.sentence.getBytes();
                RunClient.sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, porta);
                RunClient.clientSocket.send(RunClient.sendPacket);
                System.out.println("mensagem enviada ao servidor");


            }


        } catch (Exception e) {
            System.out.println("Erro" + e.getMessage());
        }
    }


}
