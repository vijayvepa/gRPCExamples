package com.example.helloworld;

//#import

import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.Behaviors;
import akka.http.javadsl.ConnectionContext;
import akka.http.javadsl.Http;
import akka.http.javadsl.HttpsConnectionContext;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.japi.function.Function;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;
import java.util.Objects;
import java.util.concurrent.CompletionStage;

//#import

//#server
public class GreeterServer {

    public static void main(String[] args) throws Exception {
        // important to enable HTTP/2 in ActorSystem's config
        Config conf = ConfigFactory.parseString("akka.http.server.preview.enable-http2 = on")
                .withFallback(ConfigFactory.load());
        ActorSystem<Void> actorSystem = ActorSystem.create(Behaviors.empty(), "GreeterServer", conf);
        new GreeterServer(actorSystem).bindServer();
    }

    final ActorSystem<?> actorSystem;

    public GreeterServer(ActorSystem<?> actorSystem) {
        this.actorSystem = actorSystem;
    }

    public CompletionStage<ServerBinding> bindServer() throws Exception {

        Function<HttpRequest, CompletionStage<HttpResponse>> greeterServiceHandler =
                GreeterServiceHandlerFactory.create(
                        new AkkaGreeterService(actorSystem),
                        actorSystem);

        CompletionStage<ServerBinding> bound =
                Http.get(actorSystem)
                        .newServerAt("127.0.0.1", 8080)
                        .enableHttps(setupHttpsCertificates())
                        .bind(greeterServiceHandler);

        bound.thenAccept(binding ->
                System.out.println("gRPC server bound to: " + binding.localAddress())
        );

        return bound;
    }
    // #server


    // FIXME this will be replaced by a more convenient utility, see https://github.com/akka/akka-grpc/issues/89
    private static HttpsConnectionContext setupHttpsCertificates() throws Exception {
        String keyEncoded = read(Objects.requireNonNull(GreeterServer.class.getResourceAsStream("/certs/server1.key")))
                .replace("-----BEGIN PRIVATE KEY-----\n", "")
                .replace("-----END PRIVATE KEY-----\n", "")
                .replace("\n", "");

        byte[] decodedKey = Base64.getDecoder().decode(keyEncoded);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(decodedKey);

        KeyFactory kf = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = kf.generatePrivate(spec);

        CertificateFactory fact = CertificateFactory.getInstance("X.509");
        Certificate cer =
                fact.generateCertificate(GreeterServer.class.getResourceAsStream("/certs/server1.pem"));

        KeyStore ks = KeyStore.getInstance("PKCS12");
        ks.load(null);
        ks.setKeyEntry("private", privateKey, new char[0], new Certificate[]{cer});

        KeyManagerFactory keyManagerFactory = KeyManagerFactory.getInstance("SunX509");
        keyManagerFactory.init(ks, null);

        SSLContext context = SSLContext.getInstance("TLS");
        context.init(keyManagerFactory.getKeyManagers(), null, new SecureRandom());

        return ConnectionContext.httpsServer(context);
    }

    private static String read(InputStream in) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(64, in.available()));
        byte[] buffer = new byte[32 * 1024];

        int bytesRead = in.read(buffer);
        while (bytesRead >= 0) {
            byteArrayOutputStream.write(buffer, 0, bytesRead);
            bytesRead = in.read(buffer);
        }

        return byteArrayOutputStream.toString("UTF-8");
    }
    //#server
}
//#server
