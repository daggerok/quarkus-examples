package com.github.daggerok.messaging;

import com.github.daggerok.infrastructure.LogMe;
import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.annotations.Stream;
import io.vavr.control.Try;
import org.reactivestreams.Publisher;
import org.slf4j.Logger;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.bind.JsonbBuilder;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.math.BigDecimal;
import java.util.Map;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static javax.ws.rs.core.MediaType.SERVER_SENT_EVENTS;

@Path("/v1/price")
// @ApplicationScoped
@Produces(APPLICATION_JSON)
public class ProducerService {

    @LogMe
    @Inject
    Logger log;

    @Inject
    @Stream("price-fix")
    Emitter<BigDecimal> price;

    @POST
    @Consumes(APPLICATION_JSON)
    @Produces(SERVER_SENT_EVENTS)
    public Publisher<String> post(String jsonString) {
        Map request = JsonbBuilder.create().fromJson(jsonString, Map.class);
        String string = "" + request.get("value");
        Try<BigDecimal> aTry = Try.of(() -> new BigDecimal(string));
        if (aTry.isFailure()) return Flowable.empty();

        return Flowable.just(aTry.get())
                       .doOnNext(p -> log.info("1) sending: {}", p))
                       .doOnNext(price::send)
                       .map(res -> Json.createObjectBuilder()
                                       .add("price has been sent", res)
                                       .build())
                       .map(JsonObject::toString);
    }
}
