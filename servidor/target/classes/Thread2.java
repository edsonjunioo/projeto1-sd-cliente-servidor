import org.apache.log4j.Logger;

import java.io.*;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Thread2 implements Runnable {

    final Logger logger = Logger.getLogger("Thread 2");



    public Thread2(String mensagem, String mensagemf2, Map<BigInteger,String> map, BlockingQueue<String> queuef2, BlockingQueue<String> queuef3, DatagramSocket serverSocket){

    }

    // ./servidor/disco/disco.txt


    @Override
    public void run() {

        while (true) {
            try {
                RunServer.mensagem = RunServer.queue.take();
            } catch (InterruptedException e){
                continue;
            }




            final Logger logger = Logger.getLogger("server");
            logger.info("Thread 2");
            logger.info("Log em disco" + RunServer.queuef2);
            logger.info("Mapa" + RunServer.map);

            logger.info("processamento");
            try {

            if (RunServer.mensagemf2.contains("create")) {

                String[] compare = RunServer.mensagemf2.split("/");
                int chaveCompare = Integer.parseInt(compare[1]);
                BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
                RunServer.map.get(keyCompare);

                if (RunServer.map.get(keyCompare) == null && compare[2].length() < 1400 && chaveCompare < 5200) {

                    String[] url = RunServer.mensagemf2.split("/");

                    int chave = Integer.parseInt(url[1]);
                    BigInteger key1 = BigInteger.valueOf(chave);
                    RunServer.map.put(key1, url[2]);

                    //RunServer.queuef2.remove();

                    logger.info("Mapa:" + RunServer.map);
                    logger.info("F2" + RunServer.queuef2);
                }
                //else {
                //    System.out.println("chave já utilizada ou tamanho da chave/valor incompativeis");
                //}

            }

            if (RunServer.mensagemf2.contains("read")) {
                String[] compare = RunServer.mensagemf2.split("/");
                int chaveCompare = Integer.parseInt(compare[1]);
                BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
                RunServer.map.get(keyCompare);

                if (RunServer.map.get(keyCompare) != null) {
                    String[] url = RunServer.mensagemf2.split("/");
                    int chave = Integer.parseInt(url[1]);
                    BigInteger key1 = BigInteger.valueOf(chave);
                    logger.info("F2:" + key1 + "=" + RunServer.map.get(key1));

                    //RunServer.queuef2.remove();
                } else {
                    System.out.println("Chave não está no mapa");
                }


            }

            if (RunServer.mensagemf2.contains("update")) {
                String[] compare = RunServer.mensagemf2.split("/");
                int chaveCompare = Integer.parseInt(compare[1]);
                BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
                RunServer.map.get(keyCompare);

                if (RunServer.map.get(keyCompare) != null && compare[2].length() < 1400) {

                    String[] url = RunServer.mensagemf2.split("/");

                    int chave = Integer.parseInt(url[1]);
                    BigInteger key1 = BigInteger.valueOf(chave);
                    RunServer.map.remove(key1);
                    RunServer.map.put(key1, url[2]);

                    //RunServer.queuef2.remove();
                } else {
                    System.out.println("chave não está no mapa ou tamanho do valor maior que 1400 bytes");
                }

            }


            if (RunServer.mensagemf2.contains("delete")) {
                String[] compare = RunServer.mensagemf2.split("/");
                int chaveCompare = Integer.parseInt(compare[1]);
                BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
                RunServer.map.get(keyCompare);

                if (RunServer.map.get(keyCompare) != null) {
                    String[] url = RunServer.mensagemf2.split("/");

                    int chave = Integer.parseInt(url[1]);
                    BigInteger key1 = BigInteger.valueOf(chave);
                    RunServer.map.remove(key1);

                    //RunServer.queuef2.remove();
                } else {
                    System.out.println("Chave não está no mapa");
                }

            }




            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }

}
