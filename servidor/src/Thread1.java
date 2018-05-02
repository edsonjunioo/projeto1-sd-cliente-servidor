import org.apache.log4j.Logger;


public class Thread1 extends Server implements Runnable {

    @Override
    public void run(){
        super.server();
    }
}
