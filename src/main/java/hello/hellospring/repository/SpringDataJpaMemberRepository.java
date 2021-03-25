package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository { // t= Member id = entity 식별자 pk{id} (이 코드에선 type {Long}) (인터페이스는 다중상속이 된
    //인터페이스만 있다  spring data jpa가 jparepository를 받고있으면 구현체를 자동으로 만들어준다 (스프링 빈 자동등록) 내가 등록하는게 아닌 spring data jpa가 보고 구현체를 만들어서 등록해

    //JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name); //이 아이는 들어있지않음
}
//JpaRepository<interface> -> PagingAndSortingRepository<interface> -> CrudRepository<interface> -> Repository<interface> v