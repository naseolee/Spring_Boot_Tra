package hello.hellospring;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.JpaMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.service.MemberService;

//Java CodeでSpring Beanコンテナ登録
@Configuration
public class SpringConfig {
	
	/*
	 * private DataSource dataSource;
	 * 
	 * @Autowired public SpringConfig(DataSource dataSource) { this.dataSource =
	 * dataSource; }
	 */
	
	//@PersistenceContext
	private EntityManager em;
	
	@Autowired
	public SpringConfig(EntityManager em) {
		this.em = em;
	}
	
	@Bean
	public MemberService memberService() {
		return new MemberService(memberRepository());
	}
	
	//DBの変更がある場合、DBMemberRepositoryに変更すると簡単に切り替え可能
	
	@Bean
	public MemberRepository memberRepository() {
		//return new MemoryMemberRepository();
		//return new JdbcMemberRepository(dataSource);
		//return new JdbcTemplateMemberRepository(dataSource);
		return new JpaMemberRepository(em);
	}
}
