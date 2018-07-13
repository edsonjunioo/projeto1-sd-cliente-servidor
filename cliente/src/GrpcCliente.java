import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.DefaultSocks4CommandResponse;

public class GrpcCliente implements Runnable{


    //private final GreeterGrpc.GreeterBlockingStub blockingStub;



    @Override
    public void run(){
        try {


            String host = RunClient.getProp().getProperty("prop.client.host");
            String port = RunClient.getProp().getProperty("prop.client.port");
            int porta = Integer.parseInt(port);


            ManagedChannel channel = ManagedChannelBuilder.forAddress(host,porta).usePlaintext().build();

            //blockingStub = GreeterGrpc.newBlockingStub(channel);



        }catch (Exception e){
            System.out.println();
        }
    }
}
