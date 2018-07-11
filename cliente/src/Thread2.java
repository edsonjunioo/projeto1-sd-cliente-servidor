import org.apache.log4j.Logger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;


public class Thread2 implements Runnable {


    DatagramPacket receivePacket;

    String modifiedSentence;

    public Thread2(DatagramSocket clientSocket){
        RunClient.clientSocket = clientSocket;

    }


    @Override
    synchronized public void run() {

        final Logger logger = Logger.getLogger("client");
        logger.info("passou pelo run da Thread 2");

        while (true) {
            logger.info("passou pelo run da Thread 2");

            try {


                byte[] receiveData = new byte[1400];
                receivePacket = new DatagramPacket(receiveData, receiveData.length);

                try {
                    RunClient.clientSocket.receive(receivePacket);
                }catch (Exception e){
                    System.out.println(e.getMessage());
                }
                modifiedSentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

                System.out.println("Mapa atualizado:" + modifiedSentence + " e mensagem recebida com sucesso\n");

                Thread.sleep(300);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

}