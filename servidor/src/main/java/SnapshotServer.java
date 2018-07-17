

import java.io.*;

public class SnapshotServer implements Runnable {
    private Thread2 thread2;
    private Thread3 thread3;

    String vazio = null;

    private int min = 1;

    public SnapshotServer(){

    }

    public static ObjectOutputStream getSnapShot() throws Exception{
        FileOutputStream file = new FileOutputStream("./servidor/disco/snapshot.txt");

        ObjectOutputStream snapshot = new ObjectOutputStream(file);

        return snapshot;
    }

    @Override
    public void run() {
        while (true){
            try {
                Thread.sleep(this.min * 60 * 1000);


                ObjectOutputStream snapShot = getSnapShot();

                snapShot.writeObject(RunServer.map.toString());
                PrintWriter printWriter = new PrintWriter("./servidor/disco/disco.txt");
                printWriter.println("");
                RunServer.queuef3.clear();


                System.out.println("\nSNAPSHOT gerado!");
                System.out.println("Logs anteriores no disco descartados!\n");

            }catch (Exception e){
                System.out.println(e.getMessage());
            }



        }

    }
}
