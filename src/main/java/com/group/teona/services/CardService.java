package com.group.teona.services;


import com.group.teona.dto.FormTeonaCard;
import com.group.teona.dto.CardRequestDto;
import com.group.teona.dto.ChoiceTopUp;
import com.group.teona.entities.Card;
import com.group.teona.entities.User;

public interface CardService {
	

	void saveFormCardWithUser(CardRequestDto passRequest,  User user,Long adressId, String paymentIntentId, String paymentMethodId);

//	Long saveFormCardWithoutUser(FormTeonaCard formRequest);

//	Card addTopUp(Long cardId, ChoiceTopUp formTopUp);




}
