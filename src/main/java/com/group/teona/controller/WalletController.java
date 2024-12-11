//package com.group.teona.controller;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.Authentication;
//import org.springframework.web.bind.annotation.*;
//
//import com.group.teona.dto.AddWalletRequest;
//import com.group.teona.dto.FormAdress;
//import com.group.teona.dto.FormTeonaPass;
//import com.group.teona.dto.GetUserRequest;
//import com.group.teona.dto.WalletRequest;
//import com.group.teona.entities.Adess;
//import com.group.teona.entities.Pass;
//import com.group.teona.entities._Pass;
//import com.group.teona.entities.User;
//import com.group.teona.entities.Wallet;
//import com.group.teona.repositories.UserRepository;
//import com.group.teona.services.AdressService;
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
//    
//    @Autowired
//	private WalletService walletService;
//    
//
//	 
//    @Autowired
//	private UserRepository userRepository;
//
//    @PostMapping("addWallet")
//	public ResponseEntity addWallet( @RequestBody AddWalletRequest walletRequest ) {
//		
//
//		walletService.addWallet(walletRequest.getPhoneNumber());
//
//		return ResponseEntity.ok("Wallet ajouté avec succès");
//	}
//    
//    @PostMapping("addWalletAuth")
//	public ResponseEntity addWalletForUser( Authentication authentication ) {
//		
//    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
//
//		walletService.addWalletForUser(userFind.get());
//
//		return ResponseEntity.ok("Wallet ajouté avec succès " + userFind.get().getFirstName());
//	}
//    
//    @PostMapping("addCardAuth")
//    public ResponseEntity addCard( Authentication authentication) {
//    	
//    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
//    	
//    	walletService.addCard(userFind.get() );
//
//    	return ResponseEntity.ok("Carte ajoutée avec succès " + userFind.get().getFirstName());
//    }
//    
//   
//
//    
//    @PostMapping("addPassAuth")
//    public ResponseEntity addPass( Authentication authentication, @RequestBody  FormTeonaPass formRequest) {
//    	
//    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
//
//    	walletService.addPass(userFind.get(), formRequest);
//
//    	return ResponseEntity.ok("Pass créé avec succès" + userFind.get().getFirstName());
//    }
//    /*
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
//    */
//    
//    @PutMapping("topUp")
//    public ResponseEntity addPersoTopUp ( @RequestParam Long cardId, @RequestParam Integer topUp, Authentication authentication) {
//    	
//    	walletService.addTopUp(cardId, topUp);
//    	
//    	return ResponseEntity.ok(topUp + " topUp ajoutés");
//
//    }
//    
//    @PutMapping("topUp5")
//    public ResponseEntity add5TopUp ( @RequestParam Long cardId, Authentication authentication) {
//    	
//    	walletService.addTopUp(cardId, 5);
//    	
//    	return ResponseEntity.ok("5 topUp ajoutés");
//
//    }
//    
//    @PutMapping("topUp10")
//    public ResponseEntity add10TopUp ( @RequestParam Long cardId, Authentication authentication) {
//    	
//    	walletService.addTopUp(cardId, 10);
//    	
//    	return ResponseEntity.ok("10 topUp ajoutés");
//
//    }
//    
//    @PutMapping("topUp15")
//    public ResponseEntity add15TopUp ( @RequestParam Long cardId, Authentication authentication) {
//    	
//    	walletService.addTopUp(cardId, 15);
//    	
//    	return ResponseEntity.ok("15 topUp ajoutés");
//    }
//    	
//    	 @PutMapping("topUp20")
//    	    public ResponseEntity add20TopUp ( @RequestParam Long cardId, Authentication authentication) {
//    	    	
//    	    	walletService.addTopUp(cardId, 20);
//    	    	
//    	    	return ResponseEntity.ok("20 topUp ajoutés");
//
//    }
//    
//    
//    }
//    
// 
//
//
//
////package com.group.teona.controller;
////import java.util.Optional;
////
////import org.springframework.beans.factory.annotation.Autowired;
////import org.springframework.http.ResponseEntity;
////import org.springframework.security.core.Authentication;
////import org.springframework.web.bind.annotation.*;
////
////import com.group.teona.dto.GetUserRequest;
////import com.group.teona.dto.WalletRequest;
////import com.group.teona.entities.Card;
////import com.group.teona.entities.Pass;
////import com.group.teona.entities.User;
////import com.group.teona.entities.Wallet;
////import com.group.teona.repositories.UserRepository;
////import com.group.teona.services.WalletService;
////
////@RestController
////@RequestMapping("auth")
////public class WalletController {
////	
////
////    @GetMapping("test")
////    public ResponseEntity tested (Authentication authentication){//il faut avoir un token pour que sa fonctionne
////        return ResponseEntity.ok("it a test : ");
////   
////        
////    }
////    
////    @Autowired
////	private WalletService walletService;
////
////	 
////    @Autowired
////	private UserRepository userRepository;
////
////    @PostMapping("add")
////	public ResponseEntity addWallet( @RequestBody WalletRequest walletRequest, Authentication authentication ) {
////
////	
////
////		Wallet wallet = walletRequest.getWallet();
////		Card card = walletRequest.getCard();
////		Pass pass = walletRequest.getPass();
////    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
////
////		walletService.addWallet(wallet, card, pass, userFind.get() );
////
////		return ResponseEntity.ok("Wallet ajouté avec succès");
////	}
////    
////    @PostMapping("addCard")
////    public ResponseEntity addCard( Authentication authentication) {
////    	
////    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
////    	
////    	walletService.addNewCard(userFind.get() );
////
////    	return ResponseEntity.ok("Carte ajoutée avec succès");
////    }
////    
////    @PostMapping("addPass")
////    public ResponseEntity addPass(@RequestBody Pass pass, Authentication authentication) {
////    	
////    	Optional<User> userFind = userRepository.findByEmail(authentication.getName());
////
////    	walletService.addNewPass(pass, userFind.get());
////
////    	return ResponseEntity.ok("Pass créé avec succès");
////    }
////    
////    @GetMapping("getUser")
////	public GetUserRequest getUserById (@RequestParam Long userId) {
////		
////		Optional<User> user = userRepository.findById(userId);
////		User userFind = user.get();
////		if (user.isPresent()) {
////			GetUserRequest userRequest = new GetUserRequest() ;
////			userRequest.setId(userFind.getId());
////			userRequest.setRole(userFind.getRole());
////			return userRequest;
////		}
////		
////		 throw new RuntimeException("Invalid user");
////	}
////    
////    }
////    
//// 
////
////
////
