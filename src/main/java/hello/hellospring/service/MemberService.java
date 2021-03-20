package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();     //순서 인터페이스 변수이름 = new MemoryMemberRepository클래스

    //result.orElseGet(); 값이 있으면 꺼내고 없으면 여기있는 메서드 실행. 이런것도 있다~

    /*
    회원가입
    */
    public Long join(Member member) {
        validateDuplicateMember(member); //중복회원 검증
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())   //메소드로 뽑기 컨트롤 + T(리팩터링관련) -> method 검색 -> Extract Method (뽑고싶은 코드 드래그선택 후 -> 커멘드 + 옵션 + m)
                .ifPresent(m -> {                       //null이 아니라 값이 있으면 실행된다 Optional이라 가능하다.
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /*
    전체 회원 조회
    */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}