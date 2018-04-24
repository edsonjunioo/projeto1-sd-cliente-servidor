


public class RunServer {

    public static void main(String[] args){

        Thread1 thread1 = new Thread1();

        Thread t1 = new  Thread(thread1);
        t1.start();
    }

}
