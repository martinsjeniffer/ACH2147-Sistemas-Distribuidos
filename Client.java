import java.util.List;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        //repositorio, peca e lista de subpecas correntes
        PartRepository repositorioCorrente = null;
        Part pecaCorrente = null;
        List<SubPart> subpecasCorrente = null;

        //objeto que contém as funcionalidades do sistema
        RepositoryService service = new RepositoryService();

        System.out.println("Bem-vindo ao Repositorio Distribuido de Pecas!");
        Scanner sc = new Scanner(System.in);
        int opcao = 0;

        while (true) {
            System.out.println("\nSelecione uma opcao:\n");
            System.out.println("---------------------------------------------------------------------------------");
            System.out.println("1  - Conectar com um repositorio");
            System.out.println("2  - Mostrar atributos do repositorio corrente");
            System.out.println("3  - Buscar determinada peca pelo codigo");
            System.out.println("4  - Mostrar atributos da peca corrente");
            System.out.println("5  - Buscar a lista de subpecas da peca corrente ou de outra peca");
            System.out.println("6  - Listar as pecas do repositorio corrente");
            System.out.println("7  - Adicionar peca ao repositorio corrente");
            System.out.println("8  - Adicionar n unidades da peca correnta na lista de subpecas corrente");
            System.out.println("9  - Remover peca do repositorio corrente");
            System.out.println("10 - Alterar peca corrente");
            System.out.println("11 - Limpar lista de subpecas corrente");
            System.out.println("12 - Sair do sistema");
            System.out.println("---------------------------------------------------------------------------------\n");

            opcao = sc.nextInt();
            sc.nextLine();

            try {
                switch(opcao) {
                    case 1:
                        repositorioCorrente = service.bind();
                        break;
                    case 2:
                        service.showr(repositorioCorrente);
                        break;
                    case 3:
                        pecaCorrente = service.getp(repositorioCorrente);
                        break;
                    case 4:
                        service.showp(pecaCorrente);
                        break;
                    case 5:
                        subpecasCorrente = service.getsubp(repositorioCorrente, pecaCorrente);   
                        break;
                    case 6:
                        service.listp(repositorioCorrente);
                        break;
                    case 7:
                        service.addpart(repositorioCorrente, subpecasCorrente); 
                        break;
                    case 8:
                        service.addsubpart(repositorioCorrente, pecaCorrente, subpecasCorrente);
                        break;
                    case 9:
                        service.removep(repositorioCorrente);
                        break;
                    case 10:
                        service.alterp(repositorioCorrente, pecaCorrente);
                        break;
                    case 11:
                        service.clearlist(subpecasCorrente);
                        break;
                    case 12:
                        sc.close();
                        service.quit();
                        break;
                    default:
                        System.out.println("Opcao invalida: deve ser um numero entre 1 e 12!");
                        break;
                }

            } catch(RepositoryException ex) {
                System.out.println("\n---------------------------------------------");
                System.out.println("Repository Exception: " + ex.getMessage());
                System.out.println("---------------------------------------------");
            }
        }
    }
}