import org.apache.log4j.Logger;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Thread2 implements Runnable {

    Thread1 thread1 = new Thread1();
    Queue<Object> queuef2 = thread1.getQueue();


    Map<BigInteger, String> map = new HashMap<>();

    public Map<BigInteger, String> getMap(){
        return map;
    }


    @Override
    public void run() {


        while (true) {

            final Logger logger = Logger.getLogger("server");

            if (((LinkedList<Object>) queuef2).getFirst().toString().contains("create")) {
                String[] url = ((LinkedList<Object>) queuef2).getFirst().toString().split("/");

                int chave = Integer.parseInt(url[1]);
                BigInteger key1 = BigInteger.valueOf(chave);
                map.put(key1, url[2]);
                logger.info(map);
            }


            if (((LinkedList<Object>) queuef2).getFirst().toString().contains("read")) {

                logger.info(map);

                logger.info(queuef2);

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

}
