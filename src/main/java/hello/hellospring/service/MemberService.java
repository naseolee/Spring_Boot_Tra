package hello.hellospring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

//Component Scan方式
//-> Service Spring 実行時、コンテナにServiceとして登録 
//@Service
@Transactional
public class MemberService {
	private final MemberRepository memberRepository;
	
	//-> constructorを生成し、外部から入れるように変更　：　DI
	public MemberService(MemberRepository memberRepository) {
		this.memberRepository = memberRepository;
	}
	
	/** 
	 * 会員登録
	 * @param member
	 * @return
	 */
	public Long join(Member member) {
		////同じ名前の重複会員は許可しない
		//Optional<Member> result = memberRepository.findByName(member.getName());
		////this same if(param == null){}
		////result.orElseGet(supplier)
		//result.ifPresent(m -> {
		//	throw new IllegalStateException("既に存在している会員の名前です。");
		//});
		////the other clean answer
		//memberRepository.findByName(member.getName()).ifPresent(m ->{
		//	throw new IllegalStateException("既に存在している会員の名前です。");
		//});
		
		// ====> write method under the page Alt + Shift + M
		validateDuplicationMember(member);
		memberRepository.save(member);
		return member.getId();
	}

	private void validateDuplicationMember(Member member) {
		memberRepository.findByName(member.getName()).ifPresent(m ->{
			throw new IllegalStateException("既に存在している会員の名前です。");
		});
	}
	
	
	
	/**
	 * すべての会員照会
	 */
	public List<Member> findMebers() {
		return memberRepository.findAll();
	}
	
	/**
	 * 一人の会員照会
	 * @param memberId
	 * @return One member
	 */
	public Optional<Member> findOne(Long memberId){
		return memberRepository.findById(memberId);
	}
	
}
