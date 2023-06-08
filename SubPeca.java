import java.io.Serializable;
import java.util.List;

public class SubPeca implements SubPart, Serializable {

    //atributos de uma subpeca
    private Part peca;
    private int quantidade;

    public SubPeca(Part peca, int quantidade) {
        this.peca = peca;
        this.quantidade = quantidade;
    }

    @Override
    public Part getPart() {
        return this.peca;
    }

    @Override
    public int getQuantidade() {
        return this.quantidade;
    }

    @Override
    public Integer getCodigo() {
        return this.peca.getCodigo();
    }

    @Override
    public String getNomePeca() {
        return this.peca.getNomePeca();
    }

    @Override
    public String getNomeRepo() {
        return this.peca.getNomeRepo();
    }

    @Override
    public String getDescricao() {
        return this.peca.getDescricao();
    }

    @Override
    public List<SubPart> getSubPecas() {
        return this.peca.getSubPecas();
    }

    @Override
    public boolean ehPrimitiva() {
        return this.peca.ehPrimitiva();
    }

    @Override
    public String toString2() {
        return this.peca.toString2();
    }
}
