package com.example.simpleBoard.mainController;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
	
	@GetMapping("/")
	public String root() {
		return "redirect:/question/list";
	}
	@GetMapping("/index")
	public String index(Model model) {
		boolean ifa = false;
		String text = "<b>예시</b>";
		String userRole = "ADMIN";
		String[] fruits = new String[5];
		fruits[0]="사과";
		fruits[1]="수박";
		fruits[2]="딸기";
		fruits[3]="자몽";
		fruits[4]="복숭아";
		
		model.addAttribute("ifTest", ifa);
		model.addAttribute("fruits",fruits);
		model.addAttribute("text",text);
		model.addAttribute("userRole", userRole);
		return "index";
	}
	
}
