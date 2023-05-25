import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Server {

    public static void main(String[] args) {

        PartRepository repository;
        String nameRepo = "rmi://localhost/br.usp.sd.ep1.ServidorPecasDistribuido";

        try {
            //criando o objeto que representa o RMI Registry (porta padrao é 1099)
            LocateRegistry.createRegistry(1099);
            //criando nosso objeto remoto
            repository = new Repository(nameRepo);
            //registrando nosso objeto remoto no server (padrao de nome é URL)
            Naming.rebind(nameRepo, repository);

            System.out.println("Server on :)");

        } catch (RemoteException | MalformedURLException ex) {
            System.out.println("Server out :(");
            ex.printStackTrace();
        }
    }
}
