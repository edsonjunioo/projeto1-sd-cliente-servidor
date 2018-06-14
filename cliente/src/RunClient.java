import org.apache.log4j.Logger;

public class RunClient {



    public static void main(String[] args){

        final Logger logger = Logger.getLogger("client");

        logger.info("passou pelo main client");

        //Client
        Thread1 thread1 = new Thread1();
        Thread t1 = new Thread(thread1);
        t1.start();






    }

}
