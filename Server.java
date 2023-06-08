import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Server extends UnicastRemoteObject implements PartRepository {

    private String name;
    private List<Part> repository;

    public Server(String name) throws RemoteException {
        this.name = name;
        this.repository = new ArrayList<>();
    }

    @Override
    public String getNameRepo() throws RemoteException {
        return this.name;
    }

    @Override
    public Integer getQtdPartsRepo() throws RemoteException {
        return this.repository.size();
    }

    @Override
    public Part getPartRepo(Integer codigo) throws RemoteException {
        return this.repository.stream()
                .filter(p -> p.getCodigo().equals(codigo))
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Part> getRepo() throws RemoteException {
        return this.repository;
    }

    @Override
    public String listPartsRepo() throws RemoteException {
        StringBuilder str = new StringBuilder();

        this.repository.forEach(part -> {
            str.append(part.toString2());
        });

        return str.toString();
    }

    @Override
    public Part addPartRepo(String nome, String descricao, List<SubPart> subparts) throws RemoteException {
        int codigo = this.repository.size();
        Part part = new Peca(codigo, nome, this.name, descricao, subparts); 
        this.repository.add(part);
        return part;
    }

    @Override
    public void addSubPartRepo(List<SubPart> subparts, Part part, int quantidade) throws RemoteException {
        SubPart subpart = new SubPeca(part, quantidade);
        subparts.add(subpart);
    }

    @Override
    public void removePartRepo(Part part) throws RemoteException {
        for (int i=0; i < this.repository.size(); i++) {
            Part pecaAtual = this.repository.get(i);
            if (pecaAtual.getCodigo().equals(part.getCodigo())) {
                this.repository.remove(i);
                break;
            }
        }
    }

    @Override
    public void alterPartRepo(Part part, String nome, String descricao) throws RemoteException {
        for (int i=0; i < this.repository.size(); i++) {
            Part pecaAtual = this.repository.get(i);

            if (pecaAtual.getCodigo().equals(part.getCodigo())) {
                Part novaPeca = new Peca(pecaAtual.getCodigo(), nome, this.name, descricao, pecaAtual.getSubPecas());
                this.repository.remove(i);
                this.repository.add(i, novaPeca);
                break;
            }
        }
    }
}