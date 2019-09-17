package com.github.daggerok.messaging;

import com.github.daggerok.infrastructure.LogMe;
import io.smallrye.reactive.messaging.annotations.Merge;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;

@ApplicationScoped
public class ConsumerService {

    @LogMe
    @Inject
    Logger log;

    @Incoming("fixed-price")
    @Merge(Merge.Mode.MERGE)
    public void consumePrices(BigDecimal price) {
        log.info("3) consumed: {}", price);
    }
}
