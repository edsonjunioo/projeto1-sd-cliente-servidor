import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;

public class Thread3 extends Thread1 implements Runnable {

    BlockingQueue<Object> queuef3;

    public Thread3(BlockingQueue<Object> queuef3){
        this.queuef3 = queuef3;

    }


    public static ObjectOutputStream getFile() throws Exception{
        FileOutputStream file = new FileOutputStream("./servidor/disco/disco.txt");

        ObjectOutputStream disco = new ObjectOutputStream(file);

        return disco;
    }


    @Override
    public void run(){

        try {
            ObjectOutputStream disco = getFile();


            disco.writeObject(queuef3.toString());
        } catch (Exception e){
            System.out.println("erro arquivo" + e.getMessage());
        }

    }
}
