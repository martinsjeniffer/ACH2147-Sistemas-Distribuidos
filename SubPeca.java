import java.util.List;

public class SubPeca implements Part {

    private Part peca;
    private int quantidade;

    @Override
    public Integer getCodigo() {
        return this.peca.getCodigo();
    }

    @Override
    public String getNome() {
        return this.peca.getNome();
    }

    @Override
    public String getDescricao() {
        return this.peca.getDescricao();
    }

    @Override
    public List<SubPeca> getSubPecas() {
        return this.peca.getSubPecas();
    }

    @Override
    public boolean ehPrimitiva() {
        return this.peca.ehPrimitiva();
    }

    public int getQuantidade() {
        return this.quantidade;
    }
}
