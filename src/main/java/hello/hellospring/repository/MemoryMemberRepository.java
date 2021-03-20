package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();   //실무에서는 동시성문제가 있을 수 있어서 복류되는 변수일때는 ConcurrentHashMap<>()을 써야하지만 예제니까 단순히 HashMap<>()울 쓴다.
     private static long sequence = 0L;                         // sequence는 0, 1, 2 이렇게 키값을 생성해주는 얘라고 보면 된다.  실무에선 어터믹롱을 쓴다.

    @Override
    public Member save(Member member) {
        member.setId(++sequence);                //스토어에 넣기전에 멤버에 id값을 세팅해주고 (save하기전에 이름은 넘어온상태)
        store.put(member.getId(), member);       // 스토어에 저 (Map에 저장이 된다)
        return member;                           // 스펙에 따라 저장된 결과 반환
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); // null이여도 감쌀수가있음 (감싸서 반환) 나머지는 클라에서 처리
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    //getName이 파라미터로 넘어온 name과 같은지 확인하는 코드
                .findAny();
                                                                    //찾아지면 반환 없으면 Optional에 null이 포함되서 반환
    }

    @Override
    public List<Member> findAll() { //실무에선 List를 많이 쓴다더라
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();//스토어를 비워주는 코드
    }
}
