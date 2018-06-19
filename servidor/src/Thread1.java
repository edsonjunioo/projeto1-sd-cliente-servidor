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

    //Thread2 thread2 = new Thread2();
    //Map<BigInteger,String> map = thread2.getMap();

    String[] backupComandos;


    BlockingQueue<Object> queue = new LinkedBlockingQueue<>();

    BlockingQueue<Object> queuef2 = new LinkedBlockingQueue<>();


    Map<BigInteger, String> map = new HashMap<>();

    DatagramSocket serverSocket;

    InetAddress IPAddress;

    int port_defined;

    public static Properties getProp() throws IOException {
        Properties props = new Properties();
        FileInputStream file = new FileInputStream("./servidor/properties/dados.properties");
        props.load(file);
        return props;

    }



    @Override
    synchronized public void run(){
        try {

            final Logger logger = Logger.getLogger("server");
            logger.info("Thread1");

            String port;

            Properties prop = getProp();

            port = prop.getProperty("prop.server.port");
            int porta = Integer.parseInt(port);

            serverSocket = new DatagramSocket(porta);
            logger.info("Conexão do servidor iniciada");

            try {


                FileInputStream fileInputStream = new FileInputStream("./servidor/disco/disco.txt");

                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

                String texto = objectInputStream.readObject().toString();

                //queuef2.clear();

                logger.info("comandos recuperados: " + texto);

                texto.replace("[","");
                texto.replace("]","");
                backupComandos = texto.split(",");

                for (String valor : backupComandos){

                    queuef2.add(valor);

                    if (valor.contains("create")) {

                        String[] compare = valor.split("/");
                        int chaveCompare = Integer.parseInt(compare[1]);
                        BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
                        map.get(keyCompare);

                        if (map.get(keyCompare) == null) {

                            String[] urlRecuperada = valor.split("/");

                            int chave = Integer.parseInt(urlRecuperada[1]);
                            BigInteger key1 = BigInteger.valueOf(chave);

                            if (chave < 5200) {
                                map.put(key1, urlRecuperada[2]);
                            }
                            logger.info("Mapa:" + map);
                            logger.info("F2" + queuef2);
                        }

                    }

                    if (valor.contains("read")) {
                        String[] compare = valor.split("/");
                        int chaveCompare = Integer.parseInt(compare[1]);
                        BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
                        map.get(keyCompare);

                        if (map.get(keyCompare) != null) {
                            String[] url = valor.split("/");
                            int chave = Integer.parseInt(url[1]);
                            BigInteger key1 = BigInteger.valueOf(chave);
                            logger.info("F2:" + key1 + "=" + map.get(key1));
                        }


                    }

                    if (valor.contains("update")) {
                        String[] compare = valor.split("/");
                        int chaveCompare = Integer.parseInt(compare[1]);
                        BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
                        map.get(keyCompare);

                        if (map.get(keyCompare) != null) {

                            String[] urlRecuperada = valor.split("/");
                            int chave = Integer.parseInt(urlRecuperada[1]);
                            BigInteger key1 = BigInteger.valueOf(chave);
                            map.remove(key1);
                            map.put(key1, urlRecuperada[2]);
                        }

                    }


                    if (valor.contains("delete")) {
                        String[] compare = valor.split("/");
                        int chaveCompare = Integer.parseInt(compare[1]);
                        BigInteger keyCompare = BigInteger.valueOf(chaveCompare);
                        map.get(keyCompare);

                        if (map.get(keyCompare) != null) {
                            String[] url = valor.split("/");

                            int chave = Integer.parseInt(url[1]);
                            BigInteger key1 = BigInteger.valueOf(chave);
                            map.remove(key1);
                        }

                    }


                }

            }catch (Exception e){
                System.out.println(e.getMessage());
            }


            while (true) {


                byte[] receiveData = new byte[1400];

                System.out.println("\nServer Running");
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData(),0, receivePacket.getLength());
                //System.out.println("Recebido: " + sentence);
                logger.info("Recebido: " + sentence);
                IPAddress = receivePacket.getAddress();
                port_defined = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();

                if(sentence.contains("create") || sentence.contains("read") || sentence.contains("update") || sentence.contains("delete") || sentence.contains("limpar")) {
                    queue.add(sentence);
                }

                logger.info("F1" + queue);

                String mensagem = queue.peek().toString();



                Thread2 thread2 = new Thread2(mensagem,map,queuef2,serverSocket,IPAddress,port_defined);
                Thread t2 = new Thread(thread2);
                t2.start();

                queue.remove();

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
