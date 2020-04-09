package com.github.daggerok.qute.beans;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Named("foo")
@ApplicationScoped
public class Foo {

  public CompletionStage<String> getHello() {
    return CompletableFuture.supplyAsync(() -> "ololo trololo");
  }
}
