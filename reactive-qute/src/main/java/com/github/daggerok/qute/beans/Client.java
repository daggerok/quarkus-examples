package com.github.daggerok.qute.beans;

import io.smallrye.mutiny.Multi;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.CompletionStage;

@Named
@ApplicationScoped
public class Client {

  public CompletionStage<List<String>> getUni() {
    return Multi.createFrom().ticks().every(Duration.ofMillis(1))
                .map(tick -> String.format("Hello %d", tick))
                .transform().byTakingFirstItems(3)
                .collectItems()
                .asList()
                .onItem().apply(strings -> strings)
                .convert().toCompletionStage();
  }

  public CompletionStage<List<String>> getMulti() {
    return Multi.createFrom().ticks().every(Duration.ofMillis(11))
                .map(tick -> String.format("Hello %d", tick))
                .transform().byTakingFirstItems(10)
                .collectItems()
                .asList()
                .onItem().apply(strings -> strings)
                .convert().toCompletionStage();
  }
}
