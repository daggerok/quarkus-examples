package daggerok;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.jboss.logmanager.LogManager;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;

@QuarkusTest
public class MyMvcControllerTest {

  @Test
  public void testHelloEndpoint() {
    given().accept(ContentType.HTML)

           .when().get("/api/v1/my-resource")

           .then().statusCode(200)
                  .body(containsStringIgnoringCase("hello"))
    ;
  }

  @Test
  public void logHelloEndpointResponse() {
    given().accept(ContentType.HTML)

           .when().contentType(ContentType.HTML)
                  .get("/api/v1/my-resource")

           .then().statusCode(200)
                  .log().all()
    ;
  }
}
