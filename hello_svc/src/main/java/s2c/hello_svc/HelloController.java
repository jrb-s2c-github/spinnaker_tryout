package s2c.hello_svc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from " + name;
    }

//    @Value("${my_name}")
    private String name = "Minikube29";

}
