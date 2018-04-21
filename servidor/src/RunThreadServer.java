


public class RunThreadServer {

    public static void main(String[] args){

    Servidor servidor = new  Servidor();

    Thread t1 = new Thread(servidor);
    t1.start();

    }

}
