package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	//coment:����mapping�u/�v���錾����Ă�����D�悳���home.html��\��
	//coment:�Ȃ��ꍇ�Astatic resource��������
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
