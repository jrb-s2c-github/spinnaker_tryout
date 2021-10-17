package s2c.hello_svc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HelloController {

    @RequestMapping("/")
    public String index() {
        return "Greetings from " + name;
    }

    @Value(value = "${my_name}")
    private String name;

}
