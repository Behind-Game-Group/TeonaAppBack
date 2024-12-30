package com.group.teona.dto;



public class VerifyRequest {
	   private String email;
	    private String code;
	    
	    public VerifyRequest() {
	    }

	    public VerifyRequest(String email, String code) {
	        this.email = email;
	        this.code = code;
	    }

	  
	    public String getEmail() {
	        return email;
	    }

	  
	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public String getCode() {
	        return code;
	    }

	    public void setCode(String code) {
	        this.code = code;
	    }

	    @Override
	    public String toString() {
	        return "VerifyRequest{" +
	                "email='" + email + '\'' +
	                ", code='" + code + '\'' +
	                '}';
	    }

}
