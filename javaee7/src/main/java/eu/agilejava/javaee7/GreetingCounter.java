package eu.agilejava.javaee7;

import java.util.concurrent.atomic.AtomicLong;
import javax.enterprise.context.Dependent;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Dependent
public class GreetingCounter {

   private final AtomicLong counter = new AtomicLong();

   public long next() {
      return counter.incrementAndGet();
   }
}
