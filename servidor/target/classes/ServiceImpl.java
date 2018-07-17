import io.grpc.reflection.v1alpha.ServiceResponse;
import io.grpc.stub.StreamObserver;

import java.util.concurrent.BlockingQueue;

public class ServiceImpl extends ExecuteSendServiceGrpc.ExecuteSendServiceImplBase{

    String mensagem;

    public ServiceImpl(String mensagem){
        this.mensagem = mensagem;

    }

    /*

    public void executeService(ServiceRequest request, StreamObserver<ServiceResponse> responseObserver){
            ServiceResponse response = ServiceResponse.newBuilder().setName(request.getName()).build();
            responseObserver.onNext(response);
            responseObserver.onCompleted();


    }

    */

}





