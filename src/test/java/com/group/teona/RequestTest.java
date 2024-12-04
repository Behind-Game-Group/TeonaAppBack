package com.group.teona;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.group.teona.controller.UserController;
import com.group.teona.dto.LoginRequest;
import com.group.teona.entities.Card;
import com.group.teona.repositories.CardRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@WebMvcTest(AuthController.class)

//@WebMvcTest
@SpringBootTest
@AutoConfigureMockMvc

 class RequestTest {


    @MockBean
    private CardRepository cardRepository;

@Autowired
private UserController controller;

    @Autowired
    private MockMvc mockMvc;

    private String toJson(Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj) ;
    }

    @Test
     void testRegister() throws Exception {

        assert (true);






LoginRequest lo=new LoginRequest();
lo.setEmail("info");
lo.setPassword("11");
String json = toJson(lo);

        mockMvc.perform(post("/auth/test")
                        .contentType(MediaType.APPLICATION_JSON)
                    //    .header("authorization","bear/")
                        .content(json)

                )
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{'email':'info2', 'password':'11'}"));


    /*   // assertThat(authController.tested()).isNotNull(); avec jupiter
        /*this.mockMvc.perform(get("/auth/test")).andDo(print()).andExpect(status().isOk())
          when(authController).thenReturn(ResponseEntity.ok("it a test"));

        //assertThat(this.authController.getForObject.tested("http://localhost:" + "port" + "/",
           //     String.class)).contains("Hello, World"));
        assert (this.authController.tested()!=null);


         mvc.perform(MockMvcRequestBuilders
  			.get("/employees")
  			.accept(MediaType.APPLICATION_JSON))
      .andDo(print())
      .andExpect(status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$.employees").exists())
      .andExpect(MockMvcResultMatchers.jsonPath("$.employees[*].employeeId").isNotEmpty());

        */
        assert true;
    }



    @Test
    void testTested() throws Exception {
        mockMvc.perform(get("/auth/test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("it a test"));

        Map<String, Object> map=new HashMap<>();
        map.put("email","info");
        map.put("password","1m1");

    }
    @Test
    void testCard() throws Exception {

        LoginRequest lo=new LoginRequest();
        lo.setEmail("info");
        lo.setPassword("lk");

        Map<String, Object> map=new HashMap<>();
        map.put("email","1012");
        map.put("password","112");
            String json = toJson(map);
            try {

            MockHttpServletResponse l= mockMvc.perform(post("/api/user/login")
                            .contentType(MediaType.APPLICATION_JSON)
                            .content(json))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn().getResponse();

            System.out.println(l.getContentAsString());

        }catch (Exception  e){
            System.out.println(e);
        }




        when(cardRepository.findById(2)).thenReturn(Optional.of(new Card()));

    }
}
