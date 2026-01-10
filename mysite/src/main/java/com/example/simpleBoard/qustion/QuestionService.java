package com.example.simpleBoard.qustion;

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
}
