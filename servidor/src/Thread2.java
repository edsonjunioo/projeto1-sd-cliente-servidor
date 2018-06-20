import org.apache.log4j.Logger;

import java.io.*;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Thread2 extends Thread1 implements Runnable {

    final Logger logger = Logger.getLogger("server");

    BlockingQueue<Object> queuef2;

    BlockingQueue<Object> queuef3;

    String mensagem;

    DatagramSocket serverSocket;

    Map<BigInteger,String> map;

    InetAddress IPAddress;

    int port_defined;

    public Thread2(String mensagem, Map<BigInteger,String> map, BlockingQueue<Object> queuef2, BlockingQueue<Object> queuef3, DatagramSocket serverSocket,InetAddress IPAddress,int port_defined){
        this.mensagem = mensagem;
        this.map = map;
        this.queuef2 = queuef2;
        this.queuef3 = queuef3;
        this.serverSocket = serverSocket;
        this.IPAddress = IPAddress;
        this.port_defined = port_defined;
    }

    // ./servidor/disco/disco.txt


    @Override
    synchronized public void run() {


        queuef2.add(mensagem);
        queuef3.add(mensagem);

        Thread3 thread3 = new  Thread3(queuef3);
        Thread t3 = new Thread(thread3);
        t3.start();




        final Logger logger = Logger.getLogger("server");
        logger.info("Thread 2");
        logger.info("Log em disco" + queuef2);
        logger.info("Mapa" + map);

        logger.info("processamento");
        if (mensagem.contains("create")) {

            String[] compare = mensagem.split("/");
            int chaveCompare = Integer.parseInt(compare[1]);
            BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
            map.get(keyCompare);

            if (map.get(keyCompare) == null) {

                String[] url = mensagem.split("/");

                int chave = Integer.parseInt(url[1]);
                BigInteger key1 = BigInteger.valueOf(chave);

                if (chave < 5200) {
                    map.put(key1, url[2]);
                }
                logger.info("Mapa:" + map);
                logger.info("F2" + queuef2);
            } else {
                System.out.println("chave já utilizada");
            }

        }

        if (mensagem.contains("read")) {
            String[] compare = mensagem.split("/");
            int chaveCompare = Integer.parseInt(compare[1]);
            BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
            map.get(keyCompare);

            if (map.get(keyCompare) != null) {
                String[] url = mensagem.split("/");
                int chave = Integer.parseInt(url[1]);
                BigInteger key1 = BigInteger.valueOf(chave);
                logger.info("F2:" + key1 + "=" + map.get(key1));
            } else {
                System.out.println("Chave não está no mapa");
            }



        }

        if (mensagem.contains("update")) {
            String[] compare = mensagem.split("/");
            int chaveCompare = Integer.parseInt(compare[1]);
            BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
            map.get(keyCompare);

            if (map.get(keyCompare) != null) {

                String[] url = mensagem.split("/");

                int chave = Integer.parseInt(url[1]);
                BigInteger key1 = BigInteger.valueOf(chave);
                map.remove(key1);
                map.put(key1, url[2]);
            } else {
                System.out.println("chave não está no mapa");
            }

        }


        if (mensagem.contains("delete")) {
            String[] compare = mensagem.split("/");
            int chaveCompare = Integer.parseInt(compare[1]);
            BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
            map.get(keyCompare);

            if (map.get(keyCompare) != null) {
                String[] url = mensagem.split("/");

                int chave = Integer.parseInt(url[1]);
                BigInteger key1 = BigInteger.valueOf(chave);
                map.remove(key1);
            } else {
                System.out.println("Chave não está no mapa");
            }

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
