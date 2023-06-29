package hello.hellospring.domain;

import java.nio.channels.Pipe;
import java.security.PrivateKey;
import java.util.PrimitiveIterator;

public class Member {

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
