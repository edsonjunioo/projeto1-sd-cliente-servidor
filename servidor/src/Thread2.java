import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Thread2 extends Thread1 implements Runnable {

    final Logger logger = Logger.getLogger("server");

    Queue<Object> queuef2 = new LinkedList<Object>();

    Queue<Object> queuef3 = new LinkedList<Object>();

    String mensagem;

    public Thread2(String mensagem){
        this.mensagem = mensagem;
    }



    @Override
    synchronized public void run() {


            queuef2.add(mensagem);
            queuef3.add(mensagem);

            final Logger logger = Logger.getLogger("server");
            logger.info("Thread 2");
            logger.info("Log em disco" + queuef2);


            logger.info("processamento");
            if (mensagem.contains("create")) {
                String[] url = ((LinkedList<Object>) queuef3).getFirst().toString().split("/");

                int chave = Integer.parseInt(url[1]);
                BigInteger key1 = BigInteger.valueOf(chave);
                map.put(key1, url[2]);
                logger.info("Mapa:" + map);
                logger.info(queuef3);
            }


            if (((LinkedList<Object>) queuef3).getFirst().toString().contains("read")) {

                logger.info(map);

                logger.info(queue);

            }

            if (((LinkedList<Object>) queuef3).getFirst().toString().contains("update")) {
                String[] url = ((LinkedList<Object>) queuef3).getFirst().toString().split("/");

                int chave = Integer.parseInt(url[1]);
                BigInteger key1 = BigInteger.valueOf(chave);
                map.remove(key1);
                map.put(key1, url[2]);
            }


            if (((LinkedList<Object>) queuef3).getFirst().toString().contains("delete")) {
                String[] url = ((LinkedList<Object>) queuef3).getFirst().toString().split("/");

                int chave = Integer.parseInt(url[1]);
                BigInteger key1 = BigInteger.valueOf(chave);
                map.remove(key1);
            }

            if (((LinkedList<Object>) queuef3).getFirst().toString().contains("limpar")) {
                logger.info("mapa reinicializado");
                map.clear();
                queuef3.clear();
                logger.info(map);
                logger.info(queuef3);

            }

    }

}
