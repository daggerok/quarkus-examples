package com.github.daggerok.quarkus;

import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.annotations.Stream;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/hello")
@ApplicationScoped
public class ExampleResource {

    @Inject
    @Stream("write-prices")
    Emitter<Integer> emitter;

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        emitter.send(Long.valueOf(System.nanoTime()).intValue());
        return "hello";
    }
}
