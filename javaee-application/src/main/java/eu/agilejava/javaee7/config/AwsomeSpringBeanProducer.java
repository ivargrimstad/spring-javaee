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
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.enterprise.inject.Produces;
import javax.inject.Singleton;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author ivar.grimstad <ivar.grimstad@gmail.com>
 */
@Singleton
public class AwsomeSpringBeanProducer {

   private static final Logger LOGGER = Logger.getLogger("JAVAEE7");

   private AnnotationConfigApplicationContext ctx;

   public AwsomeSpringBeanProducer() {
      LOGGER.info(() -> this.getClass().getSimpleName() + " created");
   }

   @Produces
   public AwsomeSpringCounter awsomeCounter() {

      LOGGER.info(() -> this.getClass().getSimpleName() + " producing awsomeCounter");
      return ctx.getBean(AwsomeSpringCounter.class);
   }

/**
   @Produces
   public SimpleSpringCounter simpleCounter() {

      LOGGER.info(() -> this.getClass().getSimpleName() + " producing simpleCounter");
      return ctx.getBean(SimpleSpringCounter.class);
   }
*/

   @PostConstruct
   private void init() {
      LOGGER.info(() -> this.getClass().getSimpleName() + " init start");

      ctx = new AnnotationConfigApplicationContext();
      ctx.register(SpringApplicationConfig.class);
      ctx.refresh();
   }
}
