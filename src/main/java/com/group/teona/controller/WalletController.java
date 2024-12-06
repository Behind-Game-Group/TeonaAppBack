//package com.group.teona.controller;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import com.group.teona.dto.GetUserRequest;
//import com.group.teona.dto.WalletRequest;
//import com.group.teona.entities.Card;
//import com.group.teona.entities.Pass;
//import com.group.teona.entities.User;
//import com.group.teona.entities.Wallet;
//import com.group.teona.repositories.UserRepository;
//import com.group.teona.services.WalletService;
//
//@RestController
//@RequestMapping("auth")
//public class WalletController {
//	
//
//    @GetMapping("test")
//    public ResponseEntity tested (Authentication authentication){//il faut avoir un token pour que sa fonctionne
//        return ResponseEntity.ok("it a test : ");
//   
//        
//    }
//    
//    @Autowired
//	private WalletService walletService;
//
//	 
//    @Autowired
//	private UserRepository userRepository;
//
//    @PostMapping("add")
//	public ResponseEntity addWallet( @RequestBody WalletRequest walletRequest, Authentication authentication ) {
//
//	
//
//		Wallet wallet = walletRequest.getWallet();
//		Card card = walletRequest.getCard();
//		Pass pass = walletRequest.getPass();
//    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
//
//		walletService.addWallet(wallet, card, pass, userFind.get() );
//
//		return ResponseEntity.ok("Wallet ajouté avec succès");
//	}
//    
//    @PostMapping("addCard")
//    public ResponseEntity addCard( Authentication authentication) {
//    	
//    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
//    	
//    	walletService.addNewCard(userFind.get() );
//
//    	return ResponseEntity.ok("Carte ajoutée avec succès");
//    }
//    
//    @PostMapping("addPass")
//    public ResponseEntity addPass(@RequestBody Pass pass, Authentication authentication) {
//    	
//    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
//
//    	walletService.addNewPass(pass, userFind.get());
//
//    	return ResponseEntity.ok("Pass créé avec succès");
//    }
//    
//    @GetMapping("getUser")
//	public GetUserRequest getUserById (@RequestParam Long userId) {
//		
//		Optional<User> user = userRepository.findById(userId);
//		User userFind = user.get();
//		if (user.isPresent()) {
//			GetUserRequest userRequest = new GetUserRequest() ;
//			userRequest.setId(userFind.getId());
//			userRequest.setRole(userFind.getRole());
//			return userRequest;
//		}
//		
//		 throw new RuntimeException("Invalid user");
//	}
//    
//    }
//    
// 
//
//
//
