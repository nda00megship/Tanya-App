package esa.askerestful.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import esa.askerestful.entity.User;
import esa.askerestful.model.LoginUserRequest;
import esa.askerestful.model.TokenResponse;
import esa.askerestful.model.WebResponse;
import esa.askerestful.repository.GambarRepository;
import esa.askerestful.repository.KomentarRepository;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import esa.askerestful.security.BCrypt;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private GambarRepository gambarRepository;

    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    @BeforeEach
    void setUp(){
        komentarRepository.deleteAll();
        gambarRepository.deleteAll();
        pertanyaanRepository.deleteAll();
        userRepository.deleteAll();

    }

    @Test
    void testLoginBadRequest()throws Exception{
        LoginUserRequest request = new LoginUserRequest();
        request.setUsername("user salah");
        request.setPassword("salah");

        mockMvc.perform(
                post("/api/auth/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            Assertions.assertNotNull( response.getErrors());
        });
    }

    @Test
    void loginFailedWrongPassword()throws Exception{
        User user = new User();
        user.setIdUser("User_123");
        user.setUsername("esa");
        user.setEmail("esa@example.com");
        user.setPassword(BCrypt.hashpw("esa123" , BCrypt.gensalt()));
        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest();
        request.setUsername("esa");
        request.setPassword("esa112");

        mockMvc.perform(
                post("/api/auth/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            Assertions.assertNotNull(response.getErrors());
        });
    }

    @Test
    void loginSuccess()throws Exception{
        User user = new User();
        user.setIdUser("User_123");
        user.setUsername("esa");
        user.setEmail("esa@example.com");
        user.setPassword(BCrypt.hashpw("esa123" , BCrypt.gensalt()));
        userRepository.save(user);

        LoginUserRequest request = new LoginUserRequest();
        request.setUsername("esa");
        request.setPassword("esa123");

        mockMvc.perform(
                post("/api/auth/user")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<TokenResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertNotNull(response.getData().getToken());
            assertNotNull(response.getData().getExpiredAt());

            User userDb = userRepository.findById(user.getIdUser()).orElse(null);
            assertNotNull(userDb);
            assertEquals(userDb.getToken() , response.getData().getToken());
            assertEquals(userDb.getTokenExpiredAt() , response.getData().getExpiredAt());

        });
    }



    @Test
    void logoutFailed()throws Exception{
        mockMvc.perform(
                delete("/api/auth/logout")
                        .accept(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isUnauthorized()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void logoutSuccess()throws Exception{
        User user = new User();
        user.setIdUser("User_1");
        user.setUsername("esa");
        user.setEmail("Maulana@gmail.com");
        user.setPassword(BCrypt.hashpw("esa123" , BCrypt.gensalt()));
        user.setToken("token");
        user.setTokenExpiredAt(System.currentTimeMillis() + 100000000L);

        userRepository.save(user);

        mockMvc.perform(
                delete("/api/auth/logout")
                        .accept(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN" , user.getToken())
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });

            assertNull(response.getErrors());
            assertEquals("logout" , response.getData());

            User userDb = new User();
            assertNotNull(userDb);
            assertNull(userDb.getToken());
        });
    }
}