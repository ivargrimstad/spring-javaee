package eu.agilejava.javaee7;

import java.util.concurrent.atomic.AtomicLong;
import javax.inject.Singleton;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Singleton
public class GreetingCounter {

   private final AtomicLong counter = new AtomicLong();

   public long next() {
      return counter.incrementAndGet();
   }
}
