import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.4.0)",
    comments = "Source: service.proto")
public class ExecuteSendServiceGrpc {

  private ExecuteSendServiceGrpc() {}

  public static final String SERVICE_NAME = "ExecuteSendService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<ServiceRequest,
      ServiceResponse> METHOD_EXECUTE_SERVICE =
      io.grpc.MethodDescriptor.<ServiceRequest, ServiceResponse>newBuilder()
          .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
          .setFullMethodName(generateFullMethodName(
              "ExecuteSendService", "ExecuteService"))
          .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ServiceRequest.getDefaultInstance()))
          .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
              ServiceResponse.getDefaultInstance()))
          .build();

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ExecuteSendServiceStub newStub(io.grpc.Channel channel) {
    return new ExecuteSendServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ExecuteSendServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new ExecuteSendServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ExecuteSendServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new ExecuteSendServiceFutureStub(channel);
  }

  /**
   */
  public static abstract class ExecuteSendServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void executeService(ServiceRequest request,
        io.grpc.stub.StreamObserver<ServiceResponse> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_EXECUTE_SERVICE, responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_EXECUTE_SERVICE,
            asyncUnaryCall(
              new MethodHandlers<
                ServiceRequest,
                ServiceResponse>(
                  this, METHODID_EXECUTE_SERVICE)))
          .build();
    }
  }

  /**
   */
  public static final class ExecuteSendServiceStub extends io.grpc.stub.AbstractStub<ExecuteSendServiceStub> {
    private ExecuteSendServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ExecuteSendServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExecuteSendServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ExecuteSendServiceStub(channel, callOptions);
    }

    /**
     */
    public void executeService(ServiceRequest request,
        io.grpc.stub.StreamObserver<ServiceResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_EXECUTE_SERVICE, getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class ExecuteSendServiceBlockingStub extends io.grpc.stub.AbstractStub<ExecuteSendServiceBlockingStub> {
    private ExecuteSendServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ExecuteSendServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExecuteSendServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ExecuteSendServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public ServiceResponse executeService(ServiceRequest request) {
      return blockingUnaryCall(
          getChannel(), METHOD_EXECUTE_SERVICE, getCallOptions(), request);
    }
  }

  /**
   */
  public static final class ExecuteSendServiceFutureStub extends io.grpc.stub.AbstractStub<ExecuteSendServiceFutureStub> {
    private ExecuteSendServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private ExecuteSendServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ExecuteSendServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new ExecuteSendServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<ServiceResponse> executeService(
        ServiceRequest request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_EXECUTE_SERVICE, getCallOptions()), request);
    }
  }

  private static final int METHODID_EXECUTE_SERVICE = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final ExecuteSendServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(ExecuteSendServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_EXECUTE_SERVICE:
          serviceImpl.executeService((ServiceRequest) request,
              (io.grpc.stub.StreamObserver<ServiceResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static final class ExecuteSendServiceDescriptorSupplier implements io.grpc.protobuf.ProtoFileDescriptorSupplier {
    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return Service.getDescriptor();
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ExecuteSendServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ExecuteSendServiceDescriptorSupplier())
              .addMethod(METHOD_EXECUTE_SERVICE)
              .build();
        }
      }
    }
    return result;
  }
}
