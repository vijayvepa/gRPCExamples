import akka.actor.typed.ActorSystem;
import com.vijayvepa.akkagrpc.dagger.DaggerAkkaManagementApp_AkkaApp;
import com.vijayvepa.akkagrpc.dagger.AkkaManagementApp;
import com.vijayvepa.akkagrpc.server.CommandServer;
import org.junit.Assert;
import org.junit.Test;

public class AkkaManagementDaggerTest {

    @Test
    public void givenGeneratedComponent_whenBuildingAkkaApp_thenDependenciesInjected() {
        AkkaManagementApp.AkkaApp app = DaggerAkkaManagementApp_AkkaApp.create();

        CommandServer injectedCommandServer = app.commandServer();
        ActorSystem<?> injectedActorSystem = app.actorSystem();

        Assert.assertNotNull(injectedCommandServer);
        Assert.assertNotNull(injectedActorSystem);
    }
}
