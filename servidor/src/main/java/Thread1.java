import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.math.BigInteger;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;


public class Thread1 implements Runnable {

    String[] backupComandos;

    String texto;

    String snapshot;

    FileInputStream fileInputStream;

    FileInputStream fileInputStream2;

    ObjectInputStream objectInputStream;

    ObjectInputStream objectInputStream2;


    public Thread1(DatagramSocket serverSocket){
        RunServer.serverSocket = serverSocket;
    }

    @Override
    public void run(){


        final Logger logger = Logger.getLogger("server");
        logger.info("Thread1");


        logger.info("Conexão do servidor iniciada");

        try {


            fileInputStream = new FileInputStream("./servidor/disco/disco.txt");

            objectInputStream = new ObjectInputStream(fileInputStream);

            texto = objectInputStream.readObject().toString();


            fileInputStream2 = new FileInputStream("./servidor/disco/snapshot.txt");

            objectInputStream2 = new ObjectInputStream(fileInputStream2);

            snapshot = objectInputStream2.readObject().toString();






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

        logger.info("comandos recuperados: " + this.texto);
        logger.info("ultimo snapshot gerado: " + this.snapshot);
        logger.info("Mapa recuperado" + RunServer.map);

        while (true) {
            try {
                byte[] receiveData = new byte[1400];
                byte[] sendData = new byte[1400];

                System.out.println("\nServer Running");
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                RunServer.serverSocket.receive(receivePacket);
                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());
                //System.out.println("Recebido: " + sentence);
                logger.info("Recebido: " + sentence);
                InetAddress IPAddress = receivePacket.getAddress();
                int port_defined = receivePacket.getPort();
                String capitalizedSentence = sentence.toUpperCase();

                if (sentence.contains("create") || sentence.contains("read") || sentence.contains("update") || sentence.contains("delete") || sentence.contains("limpar")) {
                    RunServer.queue.add(sentence);
                    RunServer.queuef3.add(sentence);
                    RunServer.queuef2.add(sentence);
                }

                logger.info("F1" + RunServer.queue);
                logger.info("Mapa" + RunServer.map);
                logger.info("F2" + RunServer.queuef2);
                logger.info("F3" + RunServer.queuef3);

                //RunServer.mensagem = RunServer.queue.peek();
                RunServer.mensagemf2 = RunServer.queue.peek().toString();

                //RunServer.queuef2.add(RunServer.queue.peek().toString());
                //RunServer.queuef3.add(RunServer.queue.peek().toString());
                sendData = capitalizedSentence.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port_defined);
                RunServer.serverSocket.send(sendPacket);




            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }


    }
}
