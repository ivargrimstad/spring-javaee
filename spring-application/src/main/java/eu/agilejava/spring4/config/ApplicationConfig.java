/**
 * The MIT License
 *
 * Copyright 2014 Ivar Grimstad <ivar.grimstad@gmail.com>.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation the
 * rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the
 * Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE
 * WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR
 * COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR
 * OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package eu.agilejava.spring4.config;

import eu.agilejava.javaee7.AwsomeJavaEECounter;
import eu.agilejava.javaee7.SimpleJavaEECounter;
import java.util.logging.Logger;
import javax.enterprise.context.spi.CreationalContext;
import javax.enterprise.inject.spi.BeanManager;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Spring configuration. Example how to manage beans manually by spring.
 *
 * @author Ivar Grimstad <ivar.grimstad@gmail.com>
 */
@Configuration
@ComponentScan("eu.agilejava.spring4")
@EnableWebMvc
public class ApplicationConfig extends WebMvcConfigurerAdapter {

   private static final Logger LOGGER = Logger.getLogger("Spring 4 Application");

   /**
    * Produces a spring managed simple counter.
    *
    * @return simple counter
    */
   @Bean
   public SimpleJavaEECounter simpleJavaEECounter() {
//      return new SimpleJavaEECounter(); // spring managed
      return getCDIBean(SimpleJavaEECounter.class); // cdi managed
   }

   /**
    * Produces a spring managed awsome counter.
    *
    * @return awsome counter
    */
   @Bean
   public AwsomeJavaEECounter awsomeJavaEECounter() {
      // spring managed
      return new AwsomeJavaEECounter(); // spring managed
//      return getCDIBean(AwsomeJavaEECounter.class); // cdi managed
   }

   /**
    * Retrieves a CDI managed bean.
    * @param <T> Type of the managed bean
    * @param t the class of the managed bean
    * @return the managed bean
    */
   private <T> T getCDIBean(Class<T> t) {
      BeanManager bm = getCDIBeanManager();
      javax.enterprise.inject.spi.Bean<T> bean
              = (javax.enterprise.inject.spi.Bean<T>) bm.getBeans(t).iterator().next();
      CreationalContext<T> ctx = bm.createCreationalContext(bean);
      T cdiBean = (T) bm.getReference(bean, t, ctx);
      return cdiBean;
   }

   /**
    * Get the CDI Manager from initial context.
    * @return the cdi manager
    */
   private BeanManager getCDIBeanManager() {
      try {
         InitialContext initialContext = new InitialContext();
         return (BeanManager) initialContext.lookup("java:comp/BeanManager");
      } catch (NamingException e) {
         LOGGER.severe(() -> "Could not look up initialcontext: " + e.getMessage());
         return null;
      }
   }
}
