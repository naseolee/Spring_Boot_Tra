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
	
	//�@���s�O
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
		//assertThrows => (1:���҂��Ă����O,2:�l�i���̒l�ɂ����1�̗�O����������ƃe�X�g�͐����j)
		IllegalStateException e = org.junit.jupiter.api.Assertions.assertThrows(IllegalStateException.class, () -> 
		{memberService.join(member2);
		});
		//=> message validation : get excepion.message
		Assertions.assertThat(e.getMessage()).isEqualTo("���ɑ��݂��Ă������̖��O�ł��B");
		/*
		 * try { memberService.join(member2); //=>
		 * member2���o�^�����ƃG���[�ɂȂ邽��Assertion.fail(""����
		 * Assertions.fail("��O��������Ȃ���΂Ȃ�܂���B"); } catch(IllegalStateException e) {
		 * Assertions.assertThat(e.getMessage()).isEqualTo("���ɑ��݂��Ă������̖��O�ł��B"); }
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
