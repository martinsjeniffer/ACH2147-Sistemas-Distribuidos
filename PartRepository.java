import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PartRepository extends Remote {

    String getNameRepo() throws RemoteException;

    Integer getQtdPartsRepo() throws RemoteException;

    Part getPart(Integer codigo) throws RemoteException;

    List<Part> getParts() throws RemoteException;

    void addPartRepo(Part part) throws RemoteException;
}
