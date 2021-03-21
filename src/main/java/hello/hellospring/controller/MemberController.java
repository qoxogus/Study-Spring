package hello.hellospring.controller;

import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;


@Controller //컨테이너가 생기고 컨트롤러 애노테이션이 있다면 MemberController객체를 생성해서 스프링에 넣어둠 (스프링이 관리) -> 스프링관련 콘트롤러 동작하게 되는것!
public class MemberController {

    private final MemberService memberService;

    @Autowired // @Autowired (연결한다)   스프링 컨테이너가 뜰때 멤버콘트롤러가 생성된다   @Autowired라고 되어있다면 스프링컨테이너에있는 멤버서비스를 가져다가 연결을 해준다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
}
