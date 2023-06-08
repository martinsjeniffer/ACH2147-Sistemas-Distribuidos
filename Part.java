import java.util.List;

public interface Part {

    Integer getCodigo();

    String getNomePeca();

    String getNomeRepo();

    String getDescricao();

    List<SubPart> getSubPecas();

    boolean ehPrimitiva();

    String toString2();
}