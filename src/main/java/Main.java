import io.javalin.Javalin;
import io.javalin.core.validation.Validator;

import java.util.ArrayList;
import java.util.List;

public class Main {

  private static final ArrayList<Contact> contacts = new ArrayList<>();

  public static void main(String[] args) {
    initContacts();
    Javalin app = Javalin.create().start(7000);

    app.get("/contact/list", ctx -> ctx.json(contacts));

    app.get("/contact/:name", ctx -> {
      String name = ctx.pathParam("name");
      Contact contact = contacts.stream()
        .filter(_contact -> _contact.getName().equals(name))
        .findAny()
        .orElse(null);
      if (contact != null) ctx.json(contact);
      else ctx.json(ResponseJsonEmpty.create());
    });

    app.post("/contact", ctx -> {
      try {
        Validator<String> name = ctx.formParam("name", String.class);
        Validator<String> email = ctx.formParam("email", String.class)
          .check(_email -> _email.contains("@"), "Not a valid email address.");
        ResponseJsonMessage response = new ResponseJsonMessage("Contact created");
        Contact contact = new Contact(name.get(), email.get());
        contacts.add(contact);
        ctx.json(response);
      } catch (Exception error) {
        List<String> errors = new ArrayList<>();
        errors.add(error.getMessage());
        ResponseJsonErrors response = new ResponseJsonErrors(errors);
        ctx.json(response);
      }
    });
  }

  private static void initContacts() {
    contacts.add(new Contact("Jane Doe", "jane@example.com"));
    contacts.add(new Contact("John Doe", "john@example.com"));
  }

}