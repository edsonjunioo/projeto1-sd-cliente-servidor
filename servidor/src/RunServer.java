import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class RunServer {

    static DatagramSocket serverSocket;

    static BlockingQueue<Object> queue = new LinkedBlockingQueue<>();

    static BlockingQueue<Object> queuef2 = new LinkedBlockingQueue<>();

    static BlockingQueue<Object> queuef3 = new LinkedBlockingQueue<>();

    static String mensagem;

    static Map<BigInteger, String> map = new HashMap<>();

    static InetAddress IPAddress;

    static int port_defined;

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./servidor/properties/dados.properties");
        props.load(file);
        return props;

    }



    final static Logger logger = Logger.getLogger("server");

    public static void main(String[] args){
       logger.info("iniciando aplicação");


        try {

            String port = getProp().getProperty("prop.server.port");

            int porta = Integer.parseInt(port);

            serverSocket = new DatagramSocket(porta);

        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        Thread1 thread1 = new Thread1(serverSocket);
        Thread t1 = new Thread(thread1);
        t1.start();


        Thread2 thread2 = new Thread2(mensagem,map,queuef2,queuef3,serverSocket,IPAddress,port_defined);
        Thread t2 = new Thread(thread2);
        t2.start();


        Thread3 thread3 = new  Thread3(queuef3);
        Thread t3 = new Thread(thread3);
        t3.start();



    }

}
