package gettingstarted;

import ecommerce.ProductInfoGrpc;
import ecommerce.ProductInfoOuterClass;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ProductInfoImpl extends ProductInfoGrpc.ProductInfoImplBase {

    private Map<String, ProductInfoOuterClass.Product> productInfoOuterClassMap = new HashMap<>();

    @Override
    public void addProduct(ProductInfoOuterClass.Product request, StreamObserver<ProductInfoOuterClass.ProductID> responseObserver) {
        String uuid = UUID.randomUUID().toString();
        productInfoOuterClassMap.put(uuid, request);
        ProductInfoOuterClass.ProductID productID =
                ProductInfoOuterClass.ProductID.newBuilder().setValue(uuid).build();

        responseObserver.onNext(productID);
        responseObserver.onCompleted();
    }

    @Override
    public void getProduct(ProductInfoOuterClass.ProductID request, StreamObserver<ProductInfoOuterClass.Product> responseObserver) {
        String id = request.getValue();
        if (!productInfoOuterClassMap.containsKey(id)) {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
            return;
        }
        ProductInfoOuterClass.Product product = productInfoOuterClassMap.get(id);
        responseObserver.onNext(product);
        responseObserver.onCompleted();
        return;
    }
}
