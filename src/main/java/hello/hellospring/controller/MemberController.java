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
	// spring コンテナーを生成し、入れて管理する。SpringBean
	// Object をコンテナに登録して使う ＝＞new演算子使用×

	/* @Autowired フィールド注入->変える方法無し。良くない */
	private final MemberService memberService;

	
	 //DI//コンストラクタ注入
	 
	 @Autowired public MemberController(MemberService memberService) {
	 this.memberService = memberService; }
	
	 /*
	 * 
	 * // setter注入 ？ 良くない　誰でも変更できるように呼び出すことができるため
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
