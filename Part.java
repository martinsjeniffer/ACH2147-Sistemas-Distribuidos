import java.util.List;

public interface Part {

    Integer getCodigo();

    String getNome();

    String getDescricao();

    List<SubPeca> getSubPecas();

    boolean ehPrimitiva();
}
