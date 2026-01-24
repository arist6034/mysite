package com.example.simpleBoard.qustion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.example.simpleBoard.Answer;
import com.example.simpleBoard.user.SiteUser;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionService {
	private final QuestionRepository qusQuestionRepository;
	
	public Page<Question> getList(int page, String kw) {
		List<Sort.Order> sorts = new ArrayList<>();
		sorts.add(Sort.Order.desc("createDate"));
		Pageable pageable = PageRequest.of(page,10, Sort.by(sorts));
		Specification<Question> spec = search(kw);
		return this.qusQuestionRepository.findAll(spec, pageable);
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
	public void create(String subject, String content, SiteUser siteuser) {
		Question question = new Question();
		question.setSubject(subject);
		question.setContent(content);
		question.setCreateDate(LocalDateTime.now());
		question.setAuthor(siteuser);
		qusQuestionRepository.save(question);
	}
	
	public void modify(Question question, String subject, String content) {
		question.setSubject(subject);
		question.setContent(content);
		question.setModifyDate(LocalDateTime.now());
		this.qusQuestionRepository.save(question);
	}
	
	public void delete(Question question) {
		this.qusQuestionRepository.delete(question);
	}
	
	public void vote(Question question, SiteUser siteUser) {
		if(question.getVoter().contains(siteUser)) {
			question.getVoter().remove(siteUser);
		} else {
			question.getVoter().add(siteUser);
		}
		
		this.qusQuestionRepository.save(question);
	}
	
	private Specification<Question> search(String kw){
		return new Specification<>(){
			private static final long serialVersionUID = 1L;
			
			@Override
			public Predicate toPredicate(Root<Question> q, CriteriaQuery<?> query, CriteriaBuilder cb) {
				query.distinct(true);
				Join<Question, SiteUser> u1 = q.join("author", JoinType.LEFT);
				Join<Question, Answer> a = q.join("answerList", JoinType.LEFT);
				Join<Answer, SiteUser> u2 = a.join("author", JoinType.LEFT);
				
				return cb.or(cb.like(q.get("subject"), "%"+kw+"%"),
						cb.like(q.get("content"), "%"+kw+"%"),
						cb.like(u1.get("username"), "%"+kw+"%"),
						cb.like(a.get("content"), "%"+kw+"%"),
						cb.like(u2.get("username"), "%"+kw+"%"));
			}
		};
	}
}
