package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest //스프링 컨테이너와 테스트를 함께 실행한다
@Transactional // 테스트는 반복할수있어야한다 db에 커밋하고 푸쉬를 하지않고 롤백을 해줌 즉 쿼리문이 db에 반영되지않게 해주는 옵션 (테스트마다 롤백적용) (test케이스일때 {서비스같은것들 제외})
class MemberServiceintegrationTest {

//    MemberService memberService = new MemberService(); //class MemberService, 변수 = new MemberService
//    MemoryMemberRepository memberRepository = new MemoryMemberRepository();

    @Autowired MemberService memberService; //그냥 이렇게 해주면된다 테스트를 어디가 가져다 쓸게 아니기 때문
    @Autowired MemberRepository memberRepository;

//    @BeforeEach //테스트를 시작전 만들어서 위쪽 변수에 넣어주는 것.
//    public void beforeEach() {
//        memberRepository = new MemoryMemberRepository();    //같은 MemoryMemberRepository가 사용되게된다...
//        memberService = new MemberService(memberRepository); //MemberService에다가 넣어준다
//    }

//    //테스트 끝날때마다 데이터를 초기화시켜줌
//    @AfterEach
//    public void afterEach() {
//        memberRepository.clearStore();
//    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");

        //when
        Long saveId = memberService.join(member);

        //then
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));

        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        } catch (IllegalStateException e) {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

//    @Test
//    void findMembers() {
//
//    }
//
//    @Test
//    void findOne() {
//
//    }
}