import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class Client {

    public static void main(String[] args) {

        try {
            //pegando referencia ao nosso objeto remoto e chamando um método
            PartRepository remoteRepo = (PartRepository) Naming.lookup("rmi://localhost/br.usp.sd.ep1.ServidorPecasDistribuido");
            System.out.println("Server name: " + remoteRepo.getNameRepo());
        } catch (NotBoundException | MalformedURLException | RemoteException ex) {
            throw new RuntimeException(ex);
        }
    }
}
