package eu.agilejava.javaee7;

import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import javax.enterprise.context.ApplicationScoped;

/**
 *
 * @author Ivar Grimstad (ivar.grimstad@gmail.com)
 */
@ApplicationScoped
public class Counter {
   
   private static final Logger LOGGER = Logger.getLogger("Java EE 7 Application");
   
   private final AtomicLong counter = new AtomicLong();

   /**
    * Creates a counter.
    */
   public Counter() {
      LOGGER.info(() -> this.getClass().getSimpleName() + " created");
   }
   
   /**
    * Retrieves the next number.
    * @return the next count
    */
   public long next() {
      return counter.incrementAndGet();
   }
}
