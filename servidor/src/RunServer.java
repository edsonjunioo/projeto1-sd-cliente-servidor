import org.apache.log4j.Logger;

public class RunServer {

    final static Logger logger = Logger.getLogger("server");

    public static void main(String[] args){
       logger.info("iniciando aplicação");


        Thread1 thread1 = new Thread1();
        //Thread2 thread2 = new Thread2();

        Thread t1 = new Thread(thread1);
        //Thread t2 = new Thread(thread2);
        t1.start();
        //t2.start();
    }

}
