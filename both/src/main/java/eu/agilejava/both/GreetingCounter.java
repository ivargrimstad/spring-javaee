package eu.agilejava.both;

import java.util.concurrent.atomic.AtomicLong;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Component
public class GreetingCounter {

   private final AtomicLong counter = new AtomicLong();

   public long next() {
      return counter.incrementAndGet();
   }
}
