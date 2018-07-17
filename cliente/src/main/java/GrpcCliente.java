import io.grpc.Grpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.netty.shaded.io.netty.handler.codec.socksx.v4.DefaultSocks4CommandResponse;
import io.grpc.testing.protobuf.SimpleRequest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class GrpcCliente implements Runnable{

    //static private final ManagedChannel channel;
    //static private final ExecuteSendService


    @Override
    public void run(){
        try {


            String host = RunClient.getProp().getProperty("prop.client.host");
            String port = RunClient.getProp().getProperty("prop.client.port");
            int porta = Integer.parseInt(port);

            System.out.println("Client1 Running");
            System.out.println("Digite uma opção de 1 a 4 para o CRUD\n");
            System.out.println("1 - create\n");
            System.out.println("2 - read\n");
            System.out.println("3 - update\n");
            System.out.println("4 - delete\n");
            Scanner ler = new Scanner(System.in);
            int option = ler.nextInt();

            if (option == 1) {
                System.out.println("\nDigite algo para enviar com a chave no seguinte formato: create/chave/msg");
            }

            if (option == 2) {
                System.out.println("\nPara visualizar uma chave do mapa digite read/x/ onde x é a chave");
            }

            if (option == 3) {
                System.out.println("\nDigite chave a atualizar no seguinte formato update/x/msg onde x é a chave a ser atualizada:");
            }

            if (option == 4) {
                System.out.println("\nDigite uma chave a remover no seguinte formato delete/x/ onde x é a chave:\"");
            }

            if (option == 5) {
                System.out.println("\nDigite limpar para reinicilizar o disco\"");
            }


            BufferedReader inFromUser2 = new BufferedReader(new InputStreamReader(System.in));
            RunClient.sentence = inFromUser2.readLine();

            ManagedChannel channel = ManagedChannelBuilder.forAddress(host,porta).usePlaintext().build();

            ExecuteSendServiceGrpc.ExecuteSendServiceBlockingStub stub = ExecuteSendServiceGrpc.newBlockingStub(channel);

            ServiceRequest request = ServiceRequest.newBuilder().setName(RunClient.sentence).build();

            ServiceResponse response = stub.executeService(request);






            //ExecuteComandServiceGrpc.

            //blockingStub = GreeterGrpc.newBlockingStub(channel);



        }catch (Exception e){
            System.out.println();
        }





    }
}
