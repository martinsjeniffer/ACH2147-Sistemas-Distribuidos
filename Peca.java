import java.util.List;
import java.io.Serializable;

public class Peca implements Part, Serializable {

    //atributos de uma peca
    private Integer codigo;
    private String nomePeca; //nome da peca
    private String nomeRepo; //nome de repo que armazena a peca
    private String descricao;
    private List<SubPart> subPecas;

    //construtor que cria peca agregada (com subpecas)
    public Peca(Integer codigo, String nomePeca, String nomeRepo, String descricao, List<SubPart> subPecas) {
        this.codigo = codigo;
        this.nomePeca = nomePeca;
        this.nomeRepo = nomeRepo;
        this.descricao = descricao;
        this.subPecas = subPecas;
    }

    @Override
    public Integer getCodigo() {
        return this.codigo;
    }

    @Override
    public String getNomePeca() {
        return this.nomePeca;
    }

    @Override
    public String getNomeRepo() {
        return this.nomeRepo;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public List<SubPart> getSubPecas() {
        return this.subPecas;
    }

    @Override
    public boolean ehPrimitiva() {
        return this.subPecas == null;
    }

    @Override
    public String toString() { //utilizado para exibir informacoes de uma peca ()
        return "\n[Peca: codigo= " + this.codigo
                + " nome da peca= " + this.nomePeca
                + " nome do repo= " + this.nomeRepo
                + " descricao= " + this.descricao + "]\n"
                + "----------------------------------------------------------------------------------------------------------------------------------------\n"
                + this.toStringSubparts();
    }

    @Override
    public String toString2() { //utilizado para exibir informacoes de todas as pecas (nao exibe subpecas)
        String str = "[Peca: codigo= " + this.codigo
                + " nome da peca= " + this.nomePeca
                + " nome do repo= " + this.nomeRepo
                + " descricao= " + this.descricao;

        if (this.subPecas == null) {
            str += " peca primitiva (nao ha subpecas)]\n";
        } else {
            str += " peca agregada (" + this.subPecas.size() + " subpecas)]\n";
        }

        return str;
    }

    private String toStringSubparts() {
        StringBuilder strSubparts = new StringBuilder();
        
        if (!(this.subPecas == null)) {
            for (SubPart subpeca: this.subPecas) {
                strSubparts.append("[SUBPECA-> ");
                strSubparts.append(subpeca.getPart().toString());
                strSubparts.append(" quantidade= ");
                strSubparts.append(subpeca.getQuantidade());
                strSubparts.append("]\n");
                strSubparts.append("--------------------------------------------------------------------------------------------------------------------------\n");
            }
        } 

        return  strSubparts.toString();
    }
}
