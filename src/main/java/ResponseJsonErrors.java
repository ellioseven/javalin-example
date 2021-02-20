import java.util.List;

public class ResponseJsonErrors {

  private List<String> errors;

  public ResponseJsonErrors(List<String> errors) {
    this.errors = errors;
  }

  public List<String> getErrors() {
    return errors;
  }

}
