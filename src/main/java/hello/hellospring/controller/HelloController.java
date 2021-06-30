package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller //=> Controller 
public class HelloController {
	
	@GetMapping("hello")
	public String hello(Model model) {
		model.addAttribute("data","hello!");
		return "hello"; //=>(viewResolver -> resouces:/templates/+{ViewName}.html) search hello.html
		// spring-boot-devtools library auto restart
		// project folder > mac -> ./gradlew build // window gradlew.bat build
		// other command => clean build
	}
	
	//外部でパラメータを取得する
	@GetMapping("hello-mvc")
	public String helloMvc(@RequestParam(value = "name",required = false) String name, Model model) {
		model.addAttribute("name", name);
		return "hello-template";
	}
	
	//API (1) show return value
	@GetMapping("hello-string")
	@ResponseBody
	public String helloString(@RequestParam("name") String name) {
		return "hello" + name;
	}
	
	//API (2)important
	@GetMapping("hello-api")
	@ResponseBody
	public Hello helloApi(@RequestParam("name") String name) {
		Hello hello = new Hello();
		hello.setName(name);
		return hello;
	}
	
	static class Hello {
		private String name;
		
		public String getName(){
			return name;
		}
		
		public void setName(String name) {
			this.name = name;
		}
	}
	
	static class Test{
		private String test;

		public String getTest() {
			return test;
		}

		public void setTest(String test) {
			this.test = test;
		}
		
	}
}
