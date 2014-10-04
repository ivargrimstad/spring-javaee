package eu.agilejava.spring4;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Component
public class Counter {

   private final AtomicLong counter = new AtomicLong();

   public long next() {
      return counter.incrementAndGet();
   }
}
