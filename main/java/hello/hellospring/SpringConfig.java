package hello.hellospring;

import hello.hellospring.aop.TimeTraceAop;
import hello.hellospring.repository.*;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    @Autowired
    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em){
//        this.em = em;
//    }
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository);
    }



//    @Bean
//    public MemberRepository memberRepository()  {
////        return new JdbcMemberRepository(dataSource); 순수 JDBC
////        return  new JdbcTemplateMemberRepository(dataSource); JDBC Template
////          return new JpaMemberRepository(em); JPA
//
//
//    };


}
