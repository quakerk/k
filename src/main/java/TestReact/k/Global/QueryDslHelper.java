package TestReact.k.Global;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Configuration
public class QueryDslHelper
{
    @PersistenceContext
    private EntityManager   EttMng;

    @Bean
    public JPAQueryFactory  JpaQueryFactory()
    {
        return new JPAQueryFactory(EttMng);
    }
}
