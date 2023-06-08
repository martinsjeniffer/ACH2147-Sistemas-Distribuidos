import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface PartRepository extends Remote {

    String getNameRepo() throws RemoteException;

    Integer getQtdPartsRepo() throws RemoteException;

    Part getPartRepo(Integer codigo) throws RemoteException;

    List<Part> getRepo() throws RemoteException;

    String listPartsRepo() throws RemoteException;

    Part addPartRepo(String nome, String descricao, List<SubPart> subparts) throws RemoteException;

    void addSubPartRepo(List<SubPart> subparts, Part part, int quantidade) throws RemoteException;

    void removePartRepo(Part part) throws RemoteException;

    void alterPartRepo(Part part, String nome, String descricao) throws RemoteException;
}