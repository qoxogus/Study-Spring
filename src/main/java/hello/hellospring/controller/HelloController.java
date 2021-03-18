package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @GetMapping("hello-string")
    @ResponseBody
    public String hellostring(@RequestParam("name") String name) {
        return "hello "+ name; //"hello spring"
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name) {
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // {  "name" : "spring!" } ( 객체는 JSON으로 반환) api개발은 객체를 반환하는것이라 생각하자.
    }

    static class Hello {
        private String name; //외부에서 바로 꺼내지 못하고 메서드를 통해서 꺼낼수있음
        public String getName() { //꺼낼때
            return name;
        }

        public void setName(String name) { //넣을
            this.name = name;
        }
    }
}
