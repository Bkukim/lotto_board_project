package org.example.boardbackend.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * packageName : org.example.boardbackend.config
 * fileName : QuerydslConfig
 * author : KimDJ
 * date : 2024-06-10
 * description :
 * 요약 :
 *
 * <p>
 * ===========================================================
 * DATE            AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2024-06-10         KimDJ          최초 생성
 */
@Configuration
//현재경로 입력
@EnableJpaRepositories(basePackages = "org.example.boardbackend")
@EntityScan(basePackages = "org.example.boardbackend")
public class QuerydslConfig {
    @PersistenceContext
    private EntityManager entityManager;

//    @Bean
//    Bean 으로 등록해서 프로젝트 전역에서 사용할 수 있도록 한다
    @Bean
    public JPAQueryFactory jpaQueryFactory(EntityManager entityManager) {
        return new JPAQueryFactory(entityManager);
    }
}
