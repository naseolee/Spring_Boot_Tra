package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

//Component Scan����
//-> Service Spring ���s���A�R���e�i��Service�Ƃ��ēo�^ 
//@Service
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;
	
	//-> constructor�𐶐����A�O����������悤�ɕύX�@�F�@DI
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	/** 
	 * ����o�^
	 * @param member
	 * @return
	 */
	public Long join(Member member) {
		////�������O�̏d������͋����Ȃ�
		//Optional<Member> result = memberRepository.findByName(member.getName());
		////this same if(param == null){}
		////result.orElseGet(supplier)
		//result.ifPresent(m -> {
		//	throw new IllegalStateException("���ɑ��݂��Ă������̖��O�ł��B");
		//});
		////the other clean answer
		//memberRepository.findByName(member.getName()).ifPresent(m ->{
		//	throw new IllegalStateException("���ɑ��݂��Ă������̖��O�ł��B");
		//});
		
		// ====> write method under the page Alt + Shift + M
		validateDuplicationMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicationMember(Member member) {
		memberRepository.findByName(member.getName()).ifPresent(m ->{
			throw new IllegalStateException("���ɑ��݂��Ă������̖��O�ł��B");
		});
	}
	
	
	
	/**
	 * ���ׂẲ���Ɖ�
	 */
	public List<Member> findMebers() {
		return memberRepository.findAll();
	}
	
	/**
	 * ��l�̉���Ɖ�
	 * @param memberId
	 * @return One member
	 */
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
}
