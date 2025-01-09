package com.group.teona.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class FormTopUp {
	Long cardId;
	ChoiceTopUp choiceTopUp;

}
