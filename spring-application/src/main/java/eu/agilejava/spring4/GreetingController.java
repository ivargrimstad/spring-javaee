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
package eu.agilejava.spring4;

import eu.agilejava.javaee7.AwsomeJavaEECounter;
import eu.agilejava.javaee7.SimpleJavaEECounter;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 *
 * @author Ivar Grimstad <ivar.grimstad@gmail.com>
 */
@RestController
public class GreetingController {

   private static final Logger LOGGER = Logger.getLogger("SPRING4");

   private static final String TEMPLATE = "Hello, %s";

   @Autowired
   private GreetingCounter counter;
   
   @Autowired
   private SimpleJavaEECounter simpleJavaEECounter;
   
   @Autowired
   private AwsomeJavaEECounter awsomeJavaEECounter;

   @RequestMapping(value = "/greeting", method = RequestMethod.GET, produces = APPLICATION_JSON_VALUE)
   public Greeting greet(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {

      Greeting greeting = new Greeting(counter.next(), String.format(TEMPLATE, name));

      greeting.setSimpleCount(simpleJavaEECounter.next());
      greeting.setAwsomeCount(awsomeJavaEECounter.next());
      
      return greeting;
   }

   public GreetingController() {
      LOGGER.fine(() -> this.getClass().getSimpleName() + " created");
   }
}
