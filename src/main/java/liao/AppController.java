package liao;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ljj
 */
@RestController
public class AppController {

    @RequestMapping("/")
    public String index() {
        return "Hello!";
    }
}