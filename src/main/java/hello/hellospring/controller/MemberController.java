package hello.hellospring.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;

@Controller
public class MemberController {
	// spring �R���e�i�[�𐶐����A����ĊǗ�����BSpringBean
	// Object ���R���e�i�ɓo�^���Ďg�� ����new���Z�q�g�p�~

	/* @Autowired �t�B�[���h����->�ς�����@�����B�ǂ��Ȃ� */
	private final MemberService memberService;

	
	 //DI//�R���X�g���N�^����
	 
	 @Autowired public MemberController(MemberService memberService) {
	 this.memberService = memberService; }
	
	 /*
	 * 
	 * // setter���� �H �ǂ��Ȃ��@�N�ł��ύX�ł���悤�ɌĂяo�����Ƃ��ł��邽��
	 * 
	 * @Autowired public void setMemberService(MemberService memberService) {
	 * this.memberService = memberService; }
	 */
	 
	 @GetMapping("/members/new")
	 public String createForm() {
		 return "members/createMemberForm";
	 }
	 
	 @PostMapping("/members/new")
	 public String create(MemberForm form) {
		 Member member = new Member();
		 member.setName(form.getName());
		 
		 memberService.join(member);
		 
		 return "redirect:/";
	 }
	 
	 @GetMapping("/members")
	 public String list(Model model) {
		 List<Member> members = memberService.findMebers();
		 model.addAttribute("members",members);
		 return "members/memberList";
	 }
	
}
