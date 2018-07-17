
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptor;

public class GrpcServer implements Runnable{

    String mensagem;

    static int porta;

    @Override
    public void run(){


        try {
            String port = RunServer.getProp().getProperty("prop.client.port");
            porta = Integer.parseInt(port);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }


        Server server = ServerBuilder.forPort(porta).addService(new ServiceImpl(mensagem)).build();



    }


}

