package hello.hellospring.domain;

import javax.persistence.*;
import java.nio.channels.Pipe;
import java.security.PrivateKey;
import java.util.PrimitiveIterator;

@Entity // jpa가 관리하는 엔티티라는것을 알려주는 annotarion
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //db에 데이터 생성시 id가 자동으로 올라가는것.
    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
