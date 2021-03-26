package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller //컨테이너가 생기고 컨트롤러 애노테이션이 있다면 MemberController객체를 생성해서 스프링에 넣어둠 (스프링이 관리) -> 스프링관련 콘트롤러 동작하게 되는것!
public class MemberController {

    private final MemberService memberService;

    @Autowired // @Autowired (연결한다)   스프링 컨테이너가 뜰때 멤버콘트롤러가 생성된다   @Autowired라고 되어있다면 스프링컨테이너에있는 멤버서비스를 가져다가 연결을 해준다
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
        System.out.println("memberService.getClass() = " + memberService.getClass()); //프록시와 연결되는지 콘솔로그로 확인
    }

    @GetMapping("/members/new") //get입 //회원가입 페이지 이동
    public String createForm() {
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") //post //회원가입
    public String create(MemberForm form) {
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName()); //soutv -> System.out.println 프린트 해주는 단축어

        memberService.join(member);

        return "redirect:/";
    }

    @GetMapping("/members") //회원목록 조회
    public String list(Model  model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); //멤버라는 모델객체에 멤버를 추가시켜준다.
        return "members/memberList";

    }
}
