package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {
//클래스에서 테스트할 메서드를 한꺼번에 테스트할 수 있다.
    // 모든 테스트 순서는 내가 지정할 수 없다.
    MemoryMemberRepository repository = new MemoryMemberRepository();
    //현재 클래스를 테스트할 인터페이스에 담는다.

    @AfterEach //어떠한 메서드가 끝날때마다 동작해주는 콜백 함수. 메서드마다 테스트 용도로 객체를 만들기때문에 그 객체가 다른 메서드 테스트에 영향을 끼칠 수 있으므로 store를 전부 clear해준다.
    public void afterEach(){
        repository.clearStore();
        //테스트가 끝날때마다 멤버를 다 지움
    }

    @Test //save메서드를 테스트한다.
    public void save(){
        Member member = new Member();
        member.setName("Spring"); // 멤버의이름을 지정하고.

        repository.save(member); //저장소에 멤버를 저장한다.

        Member result =  repository.findById(member.getId()).get(); //리파지토리 메서드로 멤버의 ID를 get해 result에 담고
        assertThat(member).isEqualTo(result); //Spring이라는 멤버가 save된 result와 같다면 정상진행, 아니라면 오류발생.
        //Assertions를 option + enter 키 누르고 스태틱으로 만듬.
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("Spring_1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring_2");
        repository.save(member2);

        Member result =  repository.findByName("Spring_1").get();
        //member2가 들어오면 오류발생으로 정상 동작하는것을 알 수 있다.
        assertThat(member1).isEqualTo(result);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("Spring_1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Spring_2");
        repository.save(member2);

        List<Member> result =  repository.findAll();
        assertThat(result.size()).isEqualTo(2);
        //List에는 두명의 회원 정보가 담겼고 list의 크기로 테스트할 수 있다.
    }
}
