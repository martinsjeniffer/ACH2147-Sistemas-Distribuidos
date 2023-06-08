import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import java.util.List;
import java.util.Scanner;

//repository service methods (used by client interface)
public class RepositoryService {

    Scanner sc = new Scanner(System.in);

    public PartRepository bind() { //conecta com um servidor
        System.out.println("Digite o nome do servidor que deseja se conectar:");
        String nomeRepo = sc.nextLine();
        System.out.println("Digite o endereco IP da maquina onde se encontra o servidor:");
        String serverIP = sc.nextLine();

        try {
            Registry registry = LocateRegistry.getRegistry(serverIP, 1099);
            PartRepository repository = (PartRepository) registry.lookup(nomeRepo);
            System.out.println("\n----------------------------------------------------------------------------");
            System.out.println("Connected to repository " + repository.getNameRepo());
            System.out.println("----------------------------------------------------------------------------");
            return repository;
        } catch (NotBoundException exception) {
            throw new RepositoryException("Repository not found.");
        } catch (RemoteException exception) {
            throw new RepositoryException("Error connecting to repository " + nomeRepo);
        }
    }

    public void showr(PartRepository repositorioCorrente) {
        if (repoNotNull(repositorioCorrente)) {
            try {
                System.out.println("Nome do servidor: " + repositorioCorrente.getNameRepo());
                System.out.println(("Quantidade de pecas no repositorio: " + repositorioCorrente.getQtdPartsRepo()));
            } catch (RemoteException e) {
                throw new RepositoryException(e);
            }
        } else throw new RepositoryException("Repository is null");
    }

    public Part getp(PartRepository repositorioCorrente) {
        if (repoNotNull(repositorioCorrente)) {
            System.out.println("Digite o codigo da peca que deseja buscar:");
            int codigo = sc.nextInt();
            sc.nextLine();

            try {
                Part part = repositorioCorrente.getPartRepo(codigo);
                if (partNotNull(part)) {
                    System.out.println("Peca encontrada!\n");
                    return part;
                }
                else throw new RepositoryException("Part not found");

            } catch (RemoteException e) {
                throw new RepositoryException(e);
            }

        } else throw new RepositoryException("Repository is null");
    }

    public void showp(Part pecaCorrente) {
        if (partNotNull(pecaCorrente)) {
            System.out.println(pecaCorrente);
        } else {
            throw new RepositoryException("Part is null");
        }
    }

    public List<SubPart> getsubp(PartRepository repositorioCorrente, Part pecaCorrente) {
        System.out.println("Deseja utilizar a peca corrente ou outra peca?");
        System.out.println("1 - Peca Corrente / 2 - Outra Peca");
        int opcao = sc.nextInt();
        sc.nextLine();

        if (opcao == 1) { //cliente deseja recuperar as subpecas da peca corrente

            if (partNotNull(pecaCorrente)) {
                if (subpartsNotNull(pecaCorrente.getSubPecas())) {
                    return pecaCorrente.getSubPecas();
                } else {
                    throw new RepositoryException("Part doesnt have subparts");
                }
            } else {
                throw new RepositoryException("Part is null");
            }  
        } else if (opcao == 2) { //cliente deseja recuperar as subpecas de outra peca

            System.out.println("Deseja buscar uma peca no repositorio atual ou outro repositorio?");
            System.out.println("1 - Repositorio Atual / 2 - Outro Repositorio");
            opcao = sc.nextInt();
            sc.nextLine();

            if (opcao == 1) { //cliente deseja recuperar uma peca do repo atual

                if (repoNotNull(repositorioCorrente)) {
                    Part peca = this.getp(repositorioCorrente);
                    List<SubPart> subpecas = peca.getSubPecas();

                    if (subpartsNotNull(subpecas)) {
                        return subpecas;
                    } else {
                        throw new RepositoryException("Part doesnt have subparts"); 
                    }
                } else {
                    throw new RepositoryException("Repository is null");
                }

            } else if (opcao == 2) { //cliente deseja recuperar uma peca de outro repo

                //conectando com repo
                PartRepository repo = this.bind();
                Part peca = this.getp(repo);
                List<SubPart> subpecas = peca.getSubPecas();

                if (subpartsNotNull(subpecas)) {
                    return subpecas;
                } else {
                    throw new RepositoryException("Part doesnt have subparts");
                }
            } else {
                throw new RepositoryException("Opcao invalida!");
            }

        } else {
            throw new RepositoryException("Opcao invalida!");
        }
    }

    public void listp(PartRepository repositorioCorrente) {
        if (repoNotNull(repositorioCorrente)) {
            try {
                System.out.println(repositorioCorrente.listPartsRepo());
            } catch (RemoteException e) {
                throw new RepositoryException(e);
            }
        } else {
            throw new RepositoryException("Repository is null");
        }
    }

    public void addpart(PartRepository repositorioCorrente, List<SubPart> subpecasCorrente) {
        if (repoNotNull(repositorioCorrente)) {

            System.out.println("Digite o nome da peca que deseja adicionar:");
            String nome = sc.nextLine();
            System.out.println("Digite a descricao da peca que deseja adicionar:");
            String descricao = sc.nextLine();

            try {
                repositorioCorrente.addPartRepo(nome, descricao, subpecasCorrente);
                System.out.println("Peca adicionada com sucesso!");
            } catch (RemoteException e) {
                throw new RepositoryException(e);
            }
        } else throw new RepositoryException("Repository is null");
    }

    public void addsubpart(PartRepository repositorioCorrente, Part pecaCorrente, List<SubPart> subpecasCorrente) {
        if (repoNotNull(repositorioCorrente) && partNotNull(pecaCorrente) && subpartsNotNull(subpecasCorrente)) {

            System.out.println("Digite quantas unidades da peca corrente deseja adicionar na lista de subpecas corrente");
            int quantidade = sc.nextInt();
            sc.nextLine();

            try {
                repositorioCorrente.addSubPartRepo(subpecasCorrente, pecaCorrente, quantidade);
            } catch(RemoteException e) {
                throw new RepositoryException(e);
            }
        } else throw new RepositoryException("Repository/Part/Subparts null");
    }

    public void removep(PartRepository repositorioCorrente) {
        Part peca = this.getp(repositorioCorrente);

        try {
            repositorioCorrente.removePartRepo(peca);
            System.out.println("Peca removida do repositorio com sucesso!");
        } catch (RemoteException e) {
            throw new RepositoryException(e);
        }
    }

    public void alterp(PartRepository repositorioCorrente, Part pecaCorrente) {
        if (repoNotNull(repositorioCorrente) && partNotNull(pecaCorrente)) {

            try {
                //verificando se a peca corrente esta no repositorio corrente
                repositorioCorrente.getPartRepo(pecaCorrente.getCodigo()); 
                
                //obtendo dados da peca para alterar
                System.out.println("Digite o novo nome da peca:");
                String nome = sc.nextLine();
                System.out.println("Digite a nova descricao da peca:");
                String descricao = sc.nextLine();

                repositorioCorrente.alterPartRepo(pecaCorrente, nome, descricao);
                System.out.println("Peca alterada com sucesso!");

            } catch (RemoteException e) {
                throw new RepositoryException(e);
            }
        } 
        
        else throw new RepositoryException("Part is null");
    }

    public void clearlist(List<SubPart> subparts) {
        if (subpartsNotNull(subparts)) {
            subparts.clear();
        } else {
            throw new RepositoryException("Subparts list is null");
        }
    }

    public void quit() {
        System.out.println("Obrigado por utilizar o Repositorio Distribuido de Pecas!");
        sc.close();
        System.exit(0);
    }

    //métodos auxiliares da service (usados internamente)
    private static boolean repoNotNull(PartRepository repository) {
        return !(repository == null);
    }

    private static boolean partNotNull(Part part) {
        return !(part == null);
    }

    private static boolean subpartsNotNull(List<SubPart> subparts) {
        return !(subparts == null);
    }
}