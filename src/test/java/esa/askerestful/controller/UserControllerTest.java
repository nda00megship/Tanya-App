package esa.askerestful.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import esa.askerestful.entity.User;
import esa.askerestful.model.*;
import esa.askerestful.repository.GambarRepository;
import esa.askerestful.repository.KomentarRepository;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import esa.askerestful.security.BCrypt;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private GambarRepository gambarRepository;

    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    @BeforeEach
    void setUp(){

        komentarRepository.deleteAll();
        pertanyaanRepository.deleteAll();
        gambarRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void testRegisterSuccess() throws Exception{
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("esa12");
        request.setEmail("esa12@example.com");
        request.setPassword("esa123");

        mockMvc.perform(
                post( "/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {

            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertEquals("accept"  , response.getData());
        });
    }

    @Test
    void testRegisterUsernameDouble()throws Exception{
        User user = new User();
        user.setIdUser("1234");
        user.setUsername("esa12");
        user.setEmail("esa12@example.com");
        user.setPassword("esa123");
        userRepository.save(user);

        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("esa12");
        request.setEmail("esa12@example.com");
        request.setPassword("esa123");



        mockMvc.perform(
                post( "/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {

            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull( response.getErrors());
        });
    }

    @Test
    void testRegisterBadRequest () throws Exception{
        RegisterUserRequest request = new RegisterUserRequest();
        request.setUsername("");
        request.setPassword("");
        request.setEmail("");

        mockMvc.perform(
                post("/api/users")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull( response.getErrors());
        });
    }

    @Test
    void getUserSuccess()throws Exception{
        User user = new User();
        user.setIdUser("User_123");
        user.setUsername("esa12");
        user.setEmail("esa@example.com");
        user.setPassword(BCrypt.hashpw("esa123" , BCrypt.gensalt()));
        user.setToken("token");
        user.setTokenExpiredAt(System.currentTimeMillis() + 1000000000L);
        userRepository.save(user);


        mockMvc.perform(
                get("/api/user/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN" , user.getToken())
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<UserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull( response.getErrors());
            assertEquals(user.getUsername() ,response.getData().getUsername() );
            assertEquals(user.getEmail() ,response.getData().getEmail() );
        });
    }

    @Test
    void getUserUnauthorized()throws Exception{
        mockMvc.perform(
                get("/api/user/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN" , "not found")
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo( result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void getUserUnauthorizedTokenNotSend()throws Exception{
        mockMvc.perform(
                get("/api/user/current")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo( result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void updateUserUnauthorized()throws Exception{
        UpdateUserRequest request = new UpdateUserRequest();

        mockMvc.perform(
                patch("/api/user/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo( result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void updateUserSuccess()throws Exception{
        User user = new User();
        user.setIdUser("User_1");
        user.setUsername("esa");
        user.setEmail("esa@gmail.com");
        user.setToken("token");
        user.setPassword(BCrypt.hashpw("esa123" , BCrypt.gensalt()));
        user.setTokenExpiredAt(System.currentTimeMillis() + 1000000000L);
        userRepository.save(user);

        UpdateUserRequest request = new UpdateUserRequest();
        request.setUsername("justesa");
        request.setPassword("esa123");
        request.setEmail("Maulana@gmail.com");
        mockMvc.perform(
                patch("/api/user/current")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN" , user.getToken())
        ).andExpectAll(
                status().isOk()
        ).andDo( result -> {
            WebResponse<UserResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(request.getEmail() , response.getData().getEmail());
            assertEquals(request.getUsername() , response.getData().getUsername());
            log.info("log : " + request.getUsername());
            log.info("log : " +request.getPassword());
            User userDb = userRepository.findById(user.getIdUser()).orElse(null);
            assertNotNull(userDb);

            assertTrue(BCrypt.checkpw(request.getPassword() , userDb.getPassword()));
        });
    }

}