import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Thread2 extends Thread1 implements Runnable {

    Queue<Object> queuef2 = new LinkedList<Object>();

    public Thread2(Queue<Object> queue){
        this.queuef2 = queue;
    }




    Map<BigInteger, String> map = new HashMap<>();


    @Override
    synchronized public void run() {
        final Logger logger = Logger.getLogger("server");
        logger.info("iniciou processamento Thread 2");
        logger.info(queuef2);



        if (((LinkedList<Object>) queuef2).getLast().toString().contains("create")) {
            String[] url = ((LinkedList<Object>) queuef2).getFirst().toString().split("/");

            int chave = Integer.parseInt(url[1]);
            BigInteger key1 = BigInteger.valueOf(chave);
            map.put(key1, url[2]);
            logger.info(map);
        }


        if (((LinkedList<Object>) queuef2).getFirst().toString().contains("read")) {

            logger.info(map);

            logger.info(queue);

        }

        if (((LinkedList<Object>) queuef2).getFirst().toString().contains("update")) {
            String[] url = ((LinkedList<Object>) queuef2).getFirst().toString().split("/");

            int chave = Integer.parseInt(url[1]);
            BigInteger key1 = BigInteger.valueOf(chave);
            map.remove(key1);
            map.put(key1, url[2]);
        }


        if (((LinkedList<Object>) queuef2).getFirst().toString().contains("delete")) {
            String[] url = ((LinkedList<Object>) queuef2).getFirst().toString().split("/");

            int chave = Integer.parseInt(url[1]);
            BigInteger key1 = BigInteger.valueOf(chave);
            map.remove(key1);
        }

        if (((LinkedList<Object>) queuef2).getFirst().toString().contains("limpar")) {
            logger.info("mapa reinicializado");
            map.clear();
            queuef2.clear();
            logger.info(map);
            logger.info(queuef2);

        }

    }

}
