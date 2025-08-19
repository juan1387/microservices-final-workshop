package org.example.cuentaservice.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Component;

@Component
public class TrasaccioneConsumer {
    private ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost",9090).usePlaintext().build();
    private TransaccionServiceGrpc.TransaccionServiceBlockingStub stub = TransaccionServiceGrpc.newBlockingStub(channel);
    public ListTransaccionesResponse getTransacciones(Long numerocuenta){
        TransaccionesRequest request = TransaccionesRequest.newBuilder()
                .setNumerocuenta(numerocuenta).build();

        return stub.getTransacciones(request);
    }
}
