package com.example.simpleBoard.answer;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AnswerForm {
	@NotEmpty(message = "내용을 필수적으로 채워야합니다.")
	private String content;
}
