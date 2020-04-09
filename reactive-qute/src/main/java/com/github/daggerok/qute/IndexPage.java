package com.github.daggerok.qute;

import com.github.daggerok.qute.beans.Bar;
import com.github.daggerok.qute.beans.Client;
import com.github.daggerok.qute.beans.Foo;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateInstance;
import io.quarkus.qute.api.ResourcePath;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@ApplicationScoped
public class IndexPage {

    @Inject
    // @ResourcePath("index.html")
    Template index;

    @Inject
    Foo foo;

    @Inject
    Bar bar;

    @Inject
    Client client;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public TemplateInstance index() {
        return index.data("hello", "world")
                    .data("client", client)
                    .data("bar", bar)
                    .data("foo", foo);
    }
}
