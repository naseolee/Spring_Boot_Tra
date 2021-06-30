package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	//coment:下のmapping「/」が宣言されていたら優先されるhome.htmlを表示
	//coment:ない場合、static resourceをさがす
	@GetMapping("/")
	public String home() {
		return "home";
	}
}
