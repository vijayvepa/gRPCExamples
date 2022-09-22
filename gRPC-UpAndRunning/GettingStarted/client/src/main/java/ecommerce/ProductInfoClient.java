package ecommerce;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ProductInfoClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 50051).usePlaintext().build();

        ProductInfoGrpc.ProductInfoBlockingStub stub =
                ProductInfoGrpc.newBlockingStub(channel);

        ProductInfoOuterClass.Product product = ProductInfoOuterClass.Product.newBuilder()
                .setName("iPhone 11")
                .setDescription("Meet iPhone 11 All new dual camera system with Ultrawide and night mode")
                .build();
        ProductInfoOuterClass.ProductID productID = stub.addProduct(product);
        System.out.println(productID.getValue());

        ProductInfoOuterClass.Product product1 = stub.getProduct(productID);
        System.out.println(product1.toString());
        channel.shutdown();

    }
}
