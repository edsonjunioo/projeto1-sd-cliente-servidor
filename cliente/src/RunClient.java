import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RunClient {

    static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    static DatagramSocket clientSocket;

    static DatagramPacket receivePacket;

    static DatagramPacket sendPacket;

    static String sentence;


    public static Properties getProp() throws Exception{
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./cliente/properties/dados.properties");
        props.load(file);
        return props;

    }


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

        //Thread2 thread2 = new Thread2(clientSocket);
        //Thread t2 = new Thread(thread2);
        //t2.start();




    }

}
