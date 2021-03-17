package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {


    @GetMapping("hello") //HTTP 메서드 ex) GET, POST
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");  // hello!! == 보내는 값  (바뀔수있는 값임)
        return "hello"; //return시 model(data : hello!!)  hello -> resources/templates/hello.html

    }
}
