import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class RmiRegistryServer {

    public static void main(String[] args) {

        PartRepository server;

        try {
            //criando o objeto que representa o RMI Registry (porta padrao é 1099)
            Registry registry = LocateRegistry.createRegistry(1099);

            List<SubPart> subpecas = new ArrayList<>();
            SubPart subpeca = null;

            for (int i=1; i <= 10; i++) {

                //criando nosso objeto remoto e populando repository
                server = new Server("rmi://localhost/sd.ep1.DistributedPartsRepository" + i);

                //5 repositorios com apenas pecas primitivas
                if (i <= 5) {
                    for (int j=0; j < 10; j++) {
                        
                        Part peca = server.addPartRepo("part" + j, "peca " + j + " remote obj n=" + i, null);
                        subpeca = new SubPeca(peca, j + 3);
                        subpecas.add(subpeca);
                    }
                } 
                
                //5 repositorios com apenas pecas agregadas
                else {
                    for (int j=0; j < 10; j++) {
                        server.addPartRepo("part" + j, "peca " + j + " remote obj n=" + i, subpecas);
                    }
                }

                //registrando nosso objeto remoto no server
                registry.rebind(server.getNameRepo(), server);
            }

            System.out.println("Server on :)");

        } catch (RemoteException ex) {
            System.out.println("Server out :(");
            ex.printStackTrace();
        }
    }
}
