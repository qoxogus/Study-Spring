package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);                 //회원이 저장소에 저장이된다.
    Optional<Member> findById(Long id);         //저장소에서 찾아오는 기능
    Optional<Member> findByName(String name);   //저장소에서 찾아오는 기능
    List<Member> findAll();                     // 지금까지 저장된 모든 회원 리스트를 반환

    // Optional -> id, name이 null값일수 있는데 그대로 반환할수도있지만 Optional이라는걸로 감싸서 반환하는걸 요즘엔 많이 선호한다. (자바8에 들어가있는 기능)
}
