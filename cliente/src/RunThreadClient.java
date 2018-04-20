


public class RunThreadClient {

    public static void main(String[] args){

        Cliente cliente = new Cliente();

        Thread t1 = new Thread(cliente);
        t1.start();


    }

}
