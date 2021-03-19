package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //순서에 의존적으로 설계하면 절대 안된다
    //테스트를 먼저 짜고 개발을 들어갈수도 있다 (검증틀을 먼저 만들어놓고 구현클래스를 만드는것) 이것을 테스트주도개발, TDD 라고 한다
    //테스트코드는 엄청 중요하다!!!

    //하나의 테스트가 끝날때마다 저장소, 데이터들을 비워줌으로써 테스트 할 때 오류가 나지않게 해준다.
    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();
        assertThat(member).isEqualTo(result);  // Assertions.assertThat().isEqualTo();   스태틱선언을 해주면 그냥 assertThat으로 편하게 줄여 쓸 수 있다. (assertj)
    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // shift + F6 똑같은 이름 바꾸기
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() { //findAll이 먼저 실행되어 이미 저장되어 버린상태가 되어 findbyName에서 이상한 객체가 나와 오류를 일으킴
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        assertThat(result.size()).isEqualTo(2); //3이되면 오류를 일으킴 저장되어있는게 2개이기 때문
    }
}
