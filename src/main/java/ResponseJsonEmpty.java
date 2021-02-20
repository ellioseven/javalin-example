import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class ResponseJsonEmpty {

  public static ObjectNode create() {
    ObjectMapper mapper = new ObjectMapper();
    return mapper.createObjectNode();
  }

}
