package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HelloController {


    @GetMapping("hello") //HTTP 메서드 ex) GET, POST
    public String hello(Model model) {
        model.addAttribute("data", "hello!!");  // hello!! == 보내는 값  (바뀔수있는 값임)    model에 hello!! 담는다
        return "hello"; //return시 model(data : hello!!)  hello -> resources/templates/hello.html
    }

    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name")  String name, Model model) { //name이라는 파라미터를 받는다 ( localhost:8080/hello-mvc?name=spring ) {spring}
        model.addAttribute("name", name); //model에 이름{spring}을 담는다
        return "hello-template"; //model로 hello-template.html로 반환
    }
}
