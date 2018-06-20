import org.apache.log4j.Logger;


public class Thread2 extends Thread1 implements Runnable {

    public Thread2(String modifiedSentence){
        this.modifiedSentence = modifiedSentence;

    }


    @Override
    public void run() {

        final Logger logger = Logger.getLogger("client");
        logger.info("passou pelo run da Thread 2");

        System.out.println("Mapa atualizado:" + modifiedSentence + " mensagem recebida com sucesso\n");



    }

}