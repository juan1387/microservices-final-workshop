package org.example.trasanccionesservices.grpc;

import io.grpc.stub.StreamObserver;
import org.example.trasanccionesservices.repository.ItransaccionRepository;
import org.springframework.grpc.server.service.GrpcService;

import java.util.List;

@GrpcService
public class TrasaccionesProvider extends TransaccionServiceGrpc.TransaccionServiceImplBase {
    private final ItransaccionRepository trasrepo;

    public TrasaccionesProvider(ItransaccionRepository trasrepo) {
        this.trasrepo = trasrepo;
    }

    @Override
    public void getTransacciones(TransaccionesRequest request, StreamObserver<ListTransaccionesResponse> responseObserver) {
       trasrepo.findByCuentaorigen(request.getNumerocuenta()).collectList().subscribe(transaCuenta ->{
           List<TransaccionesResponse> items = transaCuenta.stream().map(transacciones -> {
               return TransaccionesResponse.newBuilder()
                       .setId(transacciones.getId())
                       .setCuentaorigen(transacciones.getCuentaorigen())
                       .setCuentadestino(transacciones.getCuentadestino())
                       .setTipo(transacciones.getTipo())
                       .setValortransaccion(transacciones.getMonto())
                       .setImpuesto(transacciones.getImpuesto())
                       .setFecha(transacciones.getFechatransaccion().toString())
                       .build();
           }).toList();

           ListTransaccionesResponse listTransaccionesResponse=   ListTransaccionesResponse.newBuilder()
                           .addAllTransacciones(items)
                                   .build();

           responseObserver.onNext(listTransaccionesResponse);
           responseObserver.onCompleted();

       }).dispose();
    }

}
