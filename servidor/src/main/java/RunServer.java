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

    static int statusGRPC = 0;

    static DatagramSocket serverSocket;

    static BlockingQueue<String> queue = new LinkedBlockingQueue<>();

    static BlockingQueue<String> queuef2 = new LinkedBlockingQueue<>();

    static BlockingQueue<String> queuef3 = new LinkedBlockingQueue<>();

    static String mensagem;

    static String mensagemf2;

    static String mensagemf3;

    static Map<BigInteger, String> map = new HashMap<>();

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


        Thread2 thread2 = new Thread2(mensagem,mensagemf2,map,queuef2,queuef3,serverSocket);
        Thread t2 = new Thread(thread2);
        t2.start();


        Thread3 thread3 = new  Thread3(queuef3);
        Thread t3 = new Thread(thread3);
        t3.start();

        if(statusGRPC == 1) {
            GrpcServer grpcServer = new GrpcServer();
            Thread t4 = new Thread(grpcServer);
            t4.start();
        }



    }

}
