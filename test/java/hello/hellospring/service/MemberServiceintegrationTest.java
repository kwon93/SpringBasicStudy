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
//DB를 연결한 통합테스트
@SpringBootTest // 스프링 컨테이너와 함께 테스트를 실행한다. (자바 클래스내에서만 테스트를 하는게 아님.)
@Transactional //DB의 데이터를 Test가 끝나고 Rollback하는 Annotaion. 다른 테스트에 영향을 주지않기위해 사용.
class MemberServiceintegrationTest {
    @Autowired MemberService memberService;
   @Autowired MemberRepository MemberRepository;


    //Test는 given(~가 주어졌을때), when(~~한 상황에서) then(~~가 나와야한다.) 로 대게 이루어져 있다.
    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("Spring");

        //when
        Long saveId = memberService.join(member);
        //then
       Member findMember =  memberService.findOne(saveId).get();
       assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("Spring");

        Member member2 = new Member();
        member2.setName("Spring");
        //when
        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try {
//            memberService.join(member2);
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }

        //then
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}