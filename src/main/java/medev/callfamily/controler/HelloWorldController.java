package medev.callfamily.controler;

import lombok.AllArgsConstructor;
import medev.callfamily.service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello-world")
@AllArgsConstructor
public class HelloWorldController {

    private EmailService emailService;
    @GetMapping
    public String hello() {
        emailService.sendSimpleEmail("amine.mohamed.rg@gmail.com", "call family", "hey this an email to make you remember your family");
        return "Hello world";
    }
}
