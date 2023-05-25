import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Repository extends UnicastRemoteObject implements PartRepository {
    private static final long serialVersionUID = 1L;

    private String name;

    private List<Part> parts = new ArrayList<>();

    public Repository(String name) throws RemoteException {
        this.name = name;
    }

    @Override
    public String getNameRepo() throws RemoteException {
        return this.name;
    }

    @Override
    public Integer getQtdPartsRepo() throws RemoteException {
        return this.parts.size();
    }

    @Override
    public Part getPart(Integer codigo) throws RemoteException {
        return this.parts.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Part> getParts() throws RemoteException {
        return this.parts;
    }

    @Override
    public void addPartRepo(Part part) throws RemoteException {
        this.parts.add(part);
    }
}