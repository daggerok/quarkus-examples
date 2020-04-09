package com.github.daggerok.qute;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.reactive.converters.ReactiveTypeConverter;
import io.smallrye.reactive.converters.Registry;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;

@ApplicationScoped
public class Factories {

  @Produces
  public ReactiveTypeConverter<Multi> multiConverter() {
    return Registry.lookup(Multi.class)
                   .orElseThrow(() -> new AssertionError("Multi converter should be found"));
  }

  @Produces
  public ReactiveTypeConverter<Uni> uniConverter() {
    return Registry.lookup(Uni.class)
                   .orElseThrow(() -> new AssertionError("Uni converter should be found"));
  }
}
