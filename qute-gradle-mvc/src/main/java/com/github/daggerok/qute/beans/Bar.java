package com.github.daggerok.qute.beans;

import org.eclipse.microprofile.reactive.streams.operators.ReactiveStreams;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletionStage;

@Named
@ApplicationScoped
public class Bar {

  public List<String> getBaz() {
    return Arrays.asList("ololo", "trololo");
  }

  // // also works fine:
  // public CompletionStage<List<String>> getBaz() {
  //   return ReactiveStreams.of("ololo", "trololo")
  //                         .toList()
  //                         .run();
  // }
}
