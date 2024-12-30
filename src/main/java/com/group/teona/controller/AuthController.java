package com.group.teona.controller;
import com.group.teona.repositories.PassRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.group.teona.dto.LoginRequest;
import com.group.teona.entities.Pass;


import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("auth")
public class AuthController {
 @Autowired
 private PassRepository passRepository;

    @GetMapping("test")
    public ResponseEntity tested (){
        return ResponseEntity.ok("it a test");


    }
	@PostMapping("test")
	public ResponseEntity tested2 (@RequestBody LoginRequest loginRequest) {
        loginRequest.setEmail(loginRequest.getEmail() + 2);
        return ResponseEntity.ok(loginRequest);
    }

//
        @GetMapping("/disp")
        public ResponseEntity displayImage() throws SQLException
        {

            List<Pass> pas1s=passRepository.findAll();
            System.out.println(pas1s);
if (false){

Pass pass=  pas1s.get(1);
    System.out.println(pass.getFirstName());
            byte [] imageBytes = null;
            imageBytes = pass.getImage().getBytes(1,(int) pass.getImage().length());
            return ResponseEntity.ok().contentType(MediaType.IMAGE_JPEG).body(imageBytes);
        }
        return ResponseEntity.ok().body("not find index");
        }


}
//
//    @Autowired
//	private WalletService walletService;
//
//    @Autowired
//    private WalletRepository walletRepository;
//
//
//    @PostMapping("add")
//    // @PreAuthorize("hasAuthority('User')")
//	public ResponseEntity addWallet(@RequestParam Long userId, @RequestBody WalletRequest walletRequest, Authentication authentication ) {
//		System.out.println("coucou");
//
//		Wallet wallet = walletRequest.getWallet();
//		Card card = walletRequest.getCard();
//		Pass pass = walletRequest.getPass();
//		walletService.addWallet(userId, wallet, card, pass);
//
//		return ResponseEntity.ok("Wallet ajouté avec succès");
//	}
//
//    @PostMapping("addCard")
//    public ResponseEntity addCard(@RequestParam Long userId, @RequestBody Card card) {
//    	walletService.addNewCard(userId, card);
//
//    	return ResponseEntity.ok("Carte ajoutée avec succès");
//    }
//
//    @PostMapping("addTest")
//    public ResponseEntity addWalletTest (@RequestBody Wallet wallet) {
//    	 walletRepository.save(wallet);
//    	 return  ResponseEntity.ok(wallet);
//    }
//
//}
//
