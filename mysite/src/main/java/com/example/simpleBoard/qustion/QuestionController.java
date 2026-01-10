package com.example.simpleBoard.qustion;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.simpleBoard.Answer;
import com.example.simpleBoard.Question;
import com.example.simpleBoard.QuestionRepository;
import com.example.simpleBoard.answer.AnswerService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
@RequestMapping("/question")
public class QuestionController {
	
	private final QuestionService questionService;
	private final AnswerService answerService;

	@GetMapping("/list")
	public String list(Model model) {
		List<Question> questions = this.questionService.getList();
		model.addAttribute("questionList", questions);
		return "question_list";
	}
	
	@GetMapping("/detail/{id}")
	public String detail(Model model, @PathVariable("id") Integer id) {
		Question question = this.questionService.getQuestion(id);
		model.addAttribute(question);
		return "question_detail";
	}
	
	@GetMapping("/create")
	public String questionCreate() {
		
		return "question_form";
	}
	
	// 목록 페이지로 이동
	@PostMapping("/create")
	public String questionCreate() {
		// 질문 저장
	}
}
