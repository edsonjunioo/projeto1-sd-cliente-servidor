/*

import io.grpc.*;
import io.grpc.stub.StreamObserver;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.LinkedBlockingQueue;

public class GrpcServer{
    LinkedBlockingQueue<String> queue;

    public GrpcServer(LinkedBlockingQueue<String> queue) {
        RunServer.queue = queue;
    }

    @Override
    public void executeCommand(String request, StreamObserver<Thread2> responseStreamObserver) {
        try {
            SocketAddress clientSocketAddress = CallIntercepter.CLIENT_ADDRESS.get();
            String clientIp = ((InetSocketAddress)clientSocketAddress)
                    .getAddress()
                    .toString()
                    .split("/")[1];

            Command command = new Command(request);
            System.out.println("Receveived by gRPC protocol: " + clientSocketAddress.toString());


            command.setSource(clientIp + ":" + command.getSource().trim());

            //O TRATAMENTO DE UMA REQUISIÇÃO gRPC É O MESMO QUE UMA REQUISIÇÃO UDP
            //APENAS ADICIONAR A LISTA DE INPUTS
            this.queue.put(RunServer.mensagem);

            CommandResponse commandResponse = CommandResponse.newBuilder()
                    .setBuffer("Command" + command + "added to queue of processing")
                    .build();
            responseStreamObserver.onNext(commandResponse);
            responseStreamObserver.onCompleted();

        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}

*/