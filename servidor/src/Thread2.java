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

    BlockingQueue<Object> queuef3 = new LinkedBlockingQueue<>();

    String mensagem;

    DatagramSocket serverSocket;

    Map<BigInteger,String> map;

    InetAddress IPAddress;

    int port_defined;

    public Thread2(String mensagem, Map<BigInteger,String> map, BlockingQueue<Object> queuef2, DatagramSocket serverSocket,InetAddress IPAddress,int port_defined){
        this.mensagem = mensagem;
        this.map = map;
        this.queuef2 = queuef2;
        this.serverSocket = serverSocket;
        this.IPAddress = IPAddress;
        this.port_defined = port_defined;
    }

    // ./servidor/disco/disco.txt

    public static ObjectOutputStream getFile() throws Exception{
        FileOutputStream file = new FileOutputStream("./servidor/disco/disco.txt");

        ObjectOutputStream disco = new ObjectOutputStream(file);

        return disco;
    }

    @Override
    synchronized public void run() {


        queuef2.add(mensagem);
        queuef3.add(mensagem);


        try {
            ObjectOutputStream disco = getFile();


            disco.writeObject(queuef2.toString());
        } catch (Exception e){
            System.out.println("erro arquivo" + e.getMessage());
        }


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
