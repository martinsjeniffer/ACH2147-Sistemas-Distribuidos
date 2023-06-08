public class RepositoryException extends RuntimeException {

    public RepositoryException(Exception ex) {
        super(ex);
    }

    public RepositoryException(String message) {
        super(message);
    }
}
