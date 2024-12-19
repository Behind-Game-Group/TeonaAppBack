package com.group.teona.services;


import com.group.teona.dto.FormTeonaCard;
import com.group.teona.dto.FormTopUp;
import com.group.teona.entities.Card;
import com.group.teona.entities.User;

public interface CardService {
	

	void saveFormCardWithUser(FormTeonaCard formRequest, User user);

	void saveFormCardWithoutUser(FormTeonaCard formRequest);

	Card addTopUp(Long cardId, FormTopUp formTopUp);



}
