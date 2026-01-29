package com.example.simpleBoard.mainController;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.simpleBoard.user.SiteUser;
import com.example.simpleBoard.user.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MainController {
	
	private final UserService userService;
	
	@GetMapping("/")
	public String root(@AuthenticationPrincipal UserDetails userDetails, Model model) {
		if(userDetails != null) {
			SiteUser user = userService.getUser(userDetails.getUsername());
			model.addAttribute("profileImage", user.getImageUrl());
		}
		return "index";
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
		return "index2";
	}
	
	@GetMapping("boxoffice")
	public String movie() {
		return "movie_boxoffice";
	}
	
}
