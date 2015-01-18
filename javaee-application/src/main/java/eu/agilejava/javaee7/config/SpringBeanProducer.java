/*
 * The MIT License
 *
 * Copyright 2014 ivar.grimstad.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package eu.agilejava.javaee7.config;

import eu.agilejava.spring4.SpringApplicationConfig;
import eu.agilejava.spring4.AwsomeSpringCounter;
import eu.agilejava.spring4.SimpleSpringCounter;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Spring bean producer.
 * Shows how to make beans managed by Spring be available to CDI.
 * 
 * @author ivar.grimstad <ivar.grimstad@gmail.com>
 */
@ApplicationScoped
public class SpringBeanProducer {

   private static final Logger LOGGER = Logger.getLogger("Java EE 7 Application");
   
   private final SimpleSpringCounter simpleSpringCounter = new SimpleSpringCounter();
   private AnnotationConfigApplicationContext ctx;

   /**
    * Creates an instance of SpringBeanProducer.
    */
   public SpringBeanProducer() {
      LOGGER.fine(() -> this.getClass().getSimpleName() + " created");
   }

    /**
    * Produces a SimpleSpringCounter.
    * 
    * @return The an instance of SimpleSpringCounter managed by this app's context.
    */
   @Produces
   public SimpleSpringCounter simpleCounter() {
      
      LOGGER.info(() -> this.getClass().getSimpleName() + " producing simpleCounter");  
//      return simpleSpringCounter; // cdi managed
      return ctx.getBean(SimpleSpringCounter.class); // spring managed
   }
   
   /**
    * Produces an AwsomeSpringCounter. 
    * Retrieves the AwsomeSpringCounterBean from spring context.
    *
    * @return The Spring Bean instance of AwsomeSpringCounter
    */
   @Produces
   public AwsomeSpringCounter awsomeCounter() {

      LOGGER.info(() -> this.getClass().getSimpleName() + " producing awsomeCounter");
      return ctx.getBean(AwsomeSpringCounter.class); // spring managed
   }

   @PostConstruct
   private void init() {
      LOGGER.info(() -> this.getClass().getSimpleName() + " init start");

      ctx = new AnnotationConfigApplicationContext();
      ctx.register(SpringApplicationConfig.class);
      ctx.refresh();
   }
}
