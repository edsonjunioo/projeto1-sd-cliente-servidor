import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Thread2 extends Thread1 implements Runnable {

    final Logger logger = Logger.getLogger("server");

    Queue<Object> queuef2;

    Queue<Object> queuef3 = new LinkedList<Object>();

    String mensagem;

    DatagramSocket serverSocket;

    Map<BigInteger,String> map;

    InetAddress IPAddress;

    int port_defined;

    public Thread2(String mensagem, Map<BigInteger,String> map, Queue<Object> queuef2, DatagramSocket serverSocket,InetAddress IPAddress,int port_defined){
        this.mensagem = mensagem;
        this.map = map;
        this.queuef2 = queuef2;
        this.serverSocket = serverSocket;
        this.IPAddress = IPAddress;
        this.port_defined = port_defined;
    }



    @Override
    synchronized public void run() {


        queuef2.add(mensagem);
        queuef3.add(mensagem);

        final Logger logger = Logger.getLogger("server");
        logger.info("Thread 2");
        logger.info("Log em disco" + queuef2);
        logger.info("Mapa" + map);

        logger.info("processamento");
        if (mensagem.contains("create")) {
            String[] url = mensagem.split("/");

            int chave = Integer.parseInt(url[1]);
            BigInteger key1 = BigInteger.valueOf(chave);

            if(chave < 5200) {
                map.put(key1, url[2]);
            }
            logger.info("Mapa:" + map);
            logger.info("F2" + queuef2);
        }


        if (mensagem.contains("read")) {
            String[] url = mensagem.split("/");
            int chave = Integer.parseInt(url[1]);
            BigInteger key1 = BigInteger.valueOf(chave);
            logger.info("F2:" + key1 +"="+ map.get(key1));

        }

        if (mensagem.contains("update")) {
            String[] url = mensagem.split("/");

            int chave = Integer.parseInt(url[1]);
            BigInteger key1 = BigInteger.valueOf(chave);
            map.remove(key1);
            map.put(key1, url[2]);
        }


        if (mensagem.contains("delete")) {
            String[] url = mensagem.split("/");

            int chave = Integer.parseInt(url[1]);
            BigInteger key1 = BigInteger.valueOf(chave);
            map.remove(key1);
        }

        if (mensagem.contains("limpar")) {
            logger.info("Mapa recuperado" + map);
            logger.info("Fila f1 recuperada" + queue);
            logger.info("Fila f2 recuperada" + queuef2);
            logger.info("Fila f3 recuperada" + queuef3);
            logger.info("mapa reinicializado");
            map.clear();
            queuef2.clear();
            queuef3.clear();
            logger.info(map);
            logger.info("F2" + queuef2);

        }

        try {
            String mapa = map.toString();
            byte[] sendData = new byte[map.size()];
            sendData = mapa.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
            serverSocket.send(sendPacket);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

    }

}
