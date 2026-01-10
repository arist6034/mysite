package com.example.simpleBoard;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.simpleBoard.Answer;
import com.example.simpleBoard.AnswerRepository;
import com.example.simpleBoard.Question;
import com.example.simpleBoard.QuestionRepository;

@SpringBootTest
class MysiteApplicationTests {

	@Autowired
	private QuestionRepository questionRepository;
	@Autowired
	private AnswerRepository answerRepository;	
	@Test
	void contextLoads() {
//		Question q1 = new Question();
//		q1.setSubject("궁금하지 않지만 물어봅니다.");
//		q1.setContent("저녁 메뉴 뭐 먹어야하죠죠?");
//		q1.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q1);
//		
//		Question q2 = new Question();
//		q2.setSubject("궁금하지 않지만 물어봅니다.");
//		q2.setContent("아침은요?");
//		q2.setCreateDate(LocalDateTime.now());
//		this.questionRepository.save(q2);
//		List<Question> all = this.questionRepository.findAll();
//		assertEquals(4, all.size());
//		Question q = all.get(0);
//		assertEquals("궁금하지 않지만 물어봅니다.", q.getSubject());
		
//		Optional<Question> op = this.questionRepository.findById(11);
//		if(op.isPresent()) {
//			Question q = op.get();
//			assertEquals("저녁 메뉴 뭐 먹어야하죠?", q.getContent());
//			System.out.println(q.getContent());
//		}
//		Question q =  this.questionRepository.findByContent("저녁 메뉴 뭐 먹어야하죠죠?");
//
//		assertEquals(15, q.getId());
//		Question q =  this.questionRepository.findBySubjectAndContent("궁금하지 않지만 물어봅니다.","저녁 메뉴 뭐 먹어야하죠죠?");
//		
//		assertEquals(15, q.getId());
//		List<Question> qList = this.questionRepository.findBySubjectLike("%니다.%");
//		Question q = qList.get(0);
//		System.out.println(q.getSubject());
/*		Optional<Question> q = this.questionRepository.findById(15);
		assertTrue(q.isPresent());
		Question question = q.get();
		question.setSubject("수정된 제목");
		this.questionRepository.save(question);
		*/
		for(int i =0 ;i<50; i++) {
			Question q1 = new Question();
			q1.setSubject("궁금하지 않지만 물어봅니다.");
			q1.setContent("저녁 메뉴 뭐 먹어야하죠?["+i+"]");
			q1.setCreateDate(LocalDateTime.now());
			this.questionRepository.save(q1);
		}
		/*
		Optional<Question> q = this.questionRepository.findById(15);
		assertTrue(q.isPresent());
		Question question = q.get();
		Answer a = new Answer();
		a.setQuestion(question);
		a.setContent("부대찌개 드십시오3");
		a.setCreateDate(LocalDateTime.now());
		this.answerRepository.save(a);
		*/
		Optional<Question> op = this.questionRepository.findById(15);
		assertTrue(op.isPresent());
		Question question = op.get();
		List<Answer> aList = this.answerRepository.findByQuestion_id(question.getId());
		for(Answer a : aList) {
			System.out.println("id="+a.getId()+", Content="+a.getContent()+", Date="+a.getCreateDate()+"question_id="+a.getQuestion().getId());
		}
	}

}
