import java.util.List;

public class Peca implements Part {

    private Integer codigo;
    private String nome;
    private String descricao;
    private List<SubPeca> subPecas;

    public Peca() {}

    public Peca(Integer codigo, String nome, String descricao, List<SubPeca> subPecas) {
        this.codigo = codigo;
        this.nome = nome;
        this.descricao = descricao;
        this.subPecas = subPecas;
    }

    @Override
    public Integer getCodigo() {
        return this.codigo;
    }

    @Override
    public String getNome() {
        return this.nome;
    }

    @Override
    public String getDescricao() {
        return this.descricao;
    }

    @Override
    public List<SubPeca> getSubPecas() {
        return this.subPecas;
    }

    @Override
    public boolean ehPrimitiva() {
        return this.subPecas == null;
    }
}
