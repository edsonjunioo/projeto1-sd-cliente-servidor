

public class Thread1 extends Client2 implements Runnable {



    @Override
    public void run() {

        try {


            //super.client();
            super.receberDados();

        } catch (Exception e){
            System.out.println("Erro" + e.getMessage());
        } finally {

        }
    }
}
