package com.example.simpleBoard.qustion;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.simpleBoard.Question;
import com.example.simpleBoard.QuestionRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	private final QuestionRepository qusQuestionRepository;
	
	public List<Question> getList() {
		return this.qusQuestionRepository.findAll();
	}
	public Question getQuestion(Integer id) {
		Optional<Question> question = this.qusQuestionRepository.findById(id);
		if(question.isPresent()) {
			return question.get();
		} else {
			throw new DataNotFoundException("question not found");
		}
	}
	
	//create() => 질문을 저장하는 로직
	public void create(String subject, String content) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		qusQuestionRepository.save(question);
	}
}
