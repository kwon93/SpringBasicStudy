package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입.
    public Long join(Member member){
        //같은 이름이 있는 중복회원은 안된다.
//       Optional<Member> result = memberRepository.findByName(member.getName());
        //Optional로 감쌌기때문에 if( ~~~ == null) 같은 조건문대신 다양한 Optional 메서드를 사용할 수 있다. null값이 예상되는 로직엔 Optional을 감싸주자.

        Long start = System.currentTimeMillis();
            validateDuplicateMember(member); //중복회원 검증 cntrl + t 를 누르고 Extract Method 사용.
            memberRepository.save(member);
            return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())  // 이런식으로 코드를 줄일 수 있다.
        .ifPresent( m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //전체 회원 조회.
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    //멤버 아이디로 조회.
    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
