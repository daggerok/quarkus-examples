package daggerok;

import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.ResourcePath;
import lombok.extern.java.Log;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Log
@Path("")
@ApplicationScoped
public class MyMvcController {

  // static final java.util.logging.Logger log =
  //     org.jboss.logmanager.LogManager.getLogManager().getLogger(MyMvcController.class.getName());

  @ConfigProperty(name = "greeting", defaultValue = "Привед!")
  String greeting;

  @ResourcePath("index.html")
  Template template;

  @GET
  @Path("{path: .*}")
  @Produces(MediaType.TEXT_HTML)
  public TemplateInstance helloWorld(@PathParam("path") String path) {
    log.info("handling: '" + path + "'");
    return template.data("greeting", greeting)
                   .data("current-path", path);
  }
}
