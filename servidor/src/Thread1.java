import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class Thread1 implements Runnable {

    String[] backupComandos;


    public Thread1(DatagramSocket serverSocket){
        RunServer.serverSocket = serverSocket;
    }

    @Override
    synchronized public void run(){
        try {

            final Logger logger = Logger.getLogger("server");
            logger.info("Thread1");


            logger.info("Conexão do servidor iniciada");

            try {


                FileInputStream fileInputStream = new FileInputStream("./servidor/disco/disco.txt");

                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                String texto = objectInputStream.readObject().toString();

                logger.info("comandos recuperados: " + texto);

                backupComandos = texto.replace("[","").replace("]","").split(",");

                for (String valor : backupComandos){

                    RunServer.queuef3.add(valor);
                    RunServer.queuef2.add(valor);

                    if (valor.contains("create")) {

                        String[] compare = valor.split("/");
                        int chaveCompare = Integer.parseInt(compare[1]);
                        BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
                        RunServer.map.get(keyCompare);

                        if (RunServer.map.get(keyCompare) == null) {

                            String[] urlRecuperada = valor.split("/");

                            int chave = Integer.parseInt(urlRecuperada[1]);
                            BigInteger key1 = BigInteger.valueOf(chave);

                            if (chave < 5200) {
                                RunServer.map.put(key1, urlRecuperada[2]);
                            }
                        }

                    }

                    if (valor.contains("read")) {
                        String[] compare = valor.split("/");
                        int chaveCompare = Integer.parseInt(compare[1]);
                        BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
                        RunServer.map.get(keyCompare);

                        if (RunServer.map.get(keyCompare) != null) {
                            String[] url = valor.split("/");
                            int chave = Integer.parseInt(url[1]);
                            BigInteger key1 = BigInteger.valueOf(chave);
                        }


                    }

                    if (valor.contains("update")) {
                        String[] compare = valor.split("/");
                        int chaveCompare = Integer.parseInt(compare[1]);
                        BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
                        RunServer.map.get(keyCompare);

                        if (RunServer.map.get(keyCompare) != null) {

                            String[] urlRecuperada = valor.split("/");
                            int chave = Integer.parseInt(urlRecuperada[1]);
                            BigInteger key1 = BigInteger.valueOf(chave);
                            RunServer.map.remove(key1);
                            RunServer.map.put(key1, urlRecuperada[2]);
                        }

                    }


                    if (valor.contains("delete")) {
                        String[] compare = valor.split("/");
                        int chaveCompare = Integer.parseInt(compare[1]);
                        BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
                        RunServer.map.get(keyCompare);

                        if (RunServer.map.get(keyCompare) != null) {
                            String[] url = valor.split("/");

                            int chave = Integer.parseInt(url[1]);
                            BigInteger key1 = BigInteger.valueOf(chave);
                            RunServer.map.remove(key1);
                        }

                    }


                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            logger.info("Mapa recuperado" + RunServer.map);

            while (true) {


                byte[] receiveData = new byte[1400];

                System.out.println("\nServer Running");
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                RunServer.serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData(),0, receivePacket.getLength());
                //System.out.println("Recebido: " + sentence);
                logger.info("Recebido: " + sentence);
                RunServer.IPAddress = receivePacket.getAddress();
                RunServer.port_defined = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();

                if(sentence.contains("create") || sentence.contains("read") || sentence.contains("update") || sentence.contains("delete") || sentence.contains("limpar")) {
                    RunServer.queue.add(sentence);
                }

                logger.info("F1" + RunServer.queue);



            }

        } catch(
                Exception e)

        {
            //Logger logger = process();
            //logger.error("Erro" + e.getMessage());
            System.out.println("Erro" + e.getMessage());
        }
    }
}
