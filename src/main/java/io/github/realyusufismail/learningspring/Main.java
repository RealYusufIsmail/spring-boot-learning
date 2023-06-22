package io.github.realyusufismail.learningspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//Needed to indicate that this is a Spring Boot application
@SpringBootApplication // needed for the application to run
//Makes this class a controller that can handle HTTP requests
@RestController
public class Main {

    /**
     * The main method of the application
     *
     * @param args anything that comes from the command line
     */
    public static void main(String[] args) {
        //Needed to run the Spring Boot application
        SpringApplication.run(Main.class, args);

        //Tomcat is a web server that is used to run Java web applications
    }

    //In order to expose method as a rest endpoint, we need to annotate it with @GetMapping
    @GetMapping("/greet")
    public GreetResponse greet() {
        // if record is just returned like this, it will just give you the toString() of the record but if @RestController is used, it will automatically convert it to JSON
        return new GreetResponse("Hello World!", List.of(new favProgrammingLanguage("Java"), new favProgrammingLanguage("Kotlin")));
    }

    public record favProgrammingLanguage(String language) { }

    // records are object that are immutable and have a constructor that takes all the fields
    //immutable means that once the object is created, it cannot be changed
    public record GreetResponse(String message, List<favProgrammingLanguage> languages) { }
}
