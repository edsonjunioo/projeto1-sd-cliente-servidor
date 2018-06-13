import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Thread2 extends Thread1 implements Runnable {

    final Logger logger = Logger.getLogger("server");

    Queue<Object> queuef2;

    Queue<Object> queuef3 = new LinkedList<Object>();

    String mensagem;

    Map<BigInteger,String> map;

    public Thread2(String mensagem, Map<BigInteger,String> map, Queue<Object> queuef2){
        this.mensagem = mensagem;
        this.map = map;
        this.queuef2 = queuef2;
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
            map.put(key1, url[2]);
            logger.info("Mapa:" + map);
            logger.info("F2" + queuef2);
        }


        if (mensagem.contains("read")) {

            logger.info(map);

            logger.info("F2" + queuef2);

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


    }

}
