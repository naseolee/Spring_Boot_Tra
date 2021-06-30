package hello.hellospring.repository;

import hello.hellospring.domain.Member;


import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MemoryMemberRepositoryTest {
	
	MemoryMemberRepository repository = new MemoryMemberRepository();
	
	//Testが終了したらクリアする必要がある。（複数のテストを同時に実行した場合ｋ、前のテストで使ったテストケースが次のテストの時に残りっぱなしになり、影響を与えられる。）
	//method 終了ごとに実行されるアノテーション
	@AfterEach
	public void afterEach() {
		repository.clearStore();
	}
	
	
	@Test
	public void save() {
		Member member = new Member();
		member.setName("spring");
		
		repository.save(member);
		Member result = repository.findById(member.getId()).get();
		
		//alt + shift + r
		//System.out.println("result = " + (result == member));
		//1.
		//Assertions.assertEquals(member, null);
		//2.
		org.assertj.core.api.Assertions.assertThat(result).isEqualTo(member);
	
	}
	
	@Test
	public void findByName() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		Member result1 = repository.findByName("spring1").get();
		
		org.assertj.core.api.Assertions.assertThat(result1).isEqualTo(member1);
	}
	
	@Test
	public void findByAll() {
		Member member1 = new Member();
		member1.setName("spring1");
		repository.save(member1);
		
		Member member2 = new Member();
		member2.setName("spring2");
		repository.save(member2);
		
		List<Member> result = repository.findAll();
		
		org.assertj.core.api.Assertions.assertThat(result.size()).isEqualTo(2);
		

	}
}
