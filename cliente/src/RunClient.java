import org.apache.log4j.Logger;

import java.net.DatagramSocket;

public class RunClient {


    static DatagramSocket clientSocket;


    public static void main(String[] args){

        final Logger logger = Logger.getLogger("client");

        logger.info("passou pelo main client");

        try {

            clientSocket = new DatagramSocket();

        }catch (Exception e){
            System.out.println(e.getMessage());
        }




        Thread1 thread1 = new Thread1(clientSocket);
        Thread t1 = new Thread(thread1);
        t1.start();

        Thread2 thread2 = new Thread2(clientSocket);
        Thread t2 = new Thread(thread2);
        t2.start();




    }

}
