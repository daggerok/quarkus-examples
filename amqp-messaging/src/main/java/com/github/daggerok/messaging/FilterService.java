package com.github.daggerok.messaging;

import com.github.daggerok.infrastructure.LogMe;
import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.annotations.Merge;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import org.slf4j.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.math.BigDecimal;

@ApplicationScoped
public class FilterService {

    @LogMe
    @Inject
    Logger log;

    @Incoming("price-fix")
    @Outgoing("fixed-price")
    // @Merge(Merge.Mode.MERGE)
    public Flowable<BigDecimal> filterPrices(BigDecimal price) {
        return Flowable.just(price)
                       .doOnNext(p -> log.info("2) filtering: {}", p))
                       .filter(bigDecimal -> BigDecimal.ZERO.compareTo(bigDecimal) < 0)
                       .doOnNext(p -> log.info("3) filtered: {}", p));
    }
}
