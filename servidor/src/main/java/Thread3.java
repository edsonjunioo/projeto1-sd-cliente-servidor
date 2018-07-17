import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.concurrent.BlockingQueue;

public class Thread3 implements Runnable {

    static ObjectOutputStream disco;

    public Thread3(BlockingQueue<String> queuef3){
        RunServer.queuef3 = queuef3;

    }


    public static ObjectOutputStream getFile() throws Exception{
        FileOutputStream file = new FileOutputStream("./servidor/disco/disco.txt");

        disco = new ObjectOutputStream(file);

        return disco;
    }


    @Override
    public void run(){

        while (true) {
            try {
                RunServer.mensagemf3 =  RunServer.queuef2.take();
            } catch (InterruptedException e){
                continue;
            }


            try {
                disco = getFile();


                disco.writeObject(RunServer.queuef3.toString());
            } catch (Exception e) {
                System.out.println("erro arquivo" + e.getMessage());
            }



        }

    }
}
