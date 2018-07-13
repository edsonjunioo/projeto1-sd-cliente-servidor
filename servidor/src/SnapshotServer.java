

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class SnapshotServer implements Runnable {
    private Thread2 thread2;
    private Thread3 thread3;


    private int min = 1;

    public SnapshotServer(){

    }

    public static ObjectOutputStream getSnapShot() throws Exception{
        FileOutputStream file = new FileOutputStream("./servidor/disco/snapshot.txt");

        ObjectOutputStream disco = new ObjectOutputStream(file);

        return disco;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(this.min * 60 * 1000);


                ObjectOutputStream disco = getSnapShot();

                disco.writeObject(RunServer.map.toString());
            }catch (Exception e){
                System.out.println(e.getMessage());
            }

            System.out.println(": SNAPSHOT gerado");

        }

    }
}
