package hello.hellospring.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemoryMemberRepository;

public class MemberServiceTest {
	
	MemberService memberService;
	MemoryMemberRepository memoryMemberRepository;
	
	//　実行前
	@BeforeEach
	public void beforEach() {
		//DI
		memoryMemberRepository = new MemoryMemberRepository();
		memberService = new MemberService(memoryMemberRepository);
	}
	
	@AfterEach
	public void clean() {
		memoryMemberRepository.clearStore();
	}
	
	
	@Test
	public void join() {
		//given -> get
		Member member = new Member();
		member.setName("spring");
		
		//when -> validation
		Long saveId = memberService.join(member);
		
		//then -> finally
		Member findMember = memberService.findOne(saveId).get();
		Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
		
	}
	
	@Test
	public void joindupli() {
		//given
		Member member1 = new Member();
		member1.setName("dup");
		Member member2 = new Member();
		member2.setName("dup");
			
		//when
		memberService.join(member1);
		//assertThrows => (1:期待している例外,2:値（この値によって1の例外が発生するとテストは成功）)
		IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> 
		{memberService.join(member2);
		});
		//=> message validation : get excepion.message
		Assertions.assertThat(e.getMessage()).isEqualTo("既に存在している会員の名前です。");
		/*
		 * try { memberService.join(member2); //=>
		 * member2が登録されるとエラーになるためAssertion.fail(""処理
		 * Assertions.fail("例外処理されなければなりません。"); } catch(IllegalStateException e) {
		 * Assertions.assertThat(e.getMessage()).isEqualTo("既に存在している会員の名前です。"); }
		 */
		
		//then
	}
	
	@Test
	public void findMembers() {
		
	}
	
	@Test
	public void findOne() {
		
	}

}
