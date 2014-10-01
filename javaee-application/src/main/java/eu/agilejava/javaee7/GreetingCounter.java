package eu.agilejava.javaee7;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import javax.inject.Singleton;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@Singleton
public class GreetingCounter {
   
   private static final Logger LOGGER = Logger.getLogger("Java EE 7 Application");
   
   private final AtomicLong counter = new AtomicLong();
   
   public GreetingCounter() {
      LOGGER.info(() -> this.getClass().getSimpleName() + " created");
   }
   
   public long next() {
      return counter.incrementAndGet();
   }
}
