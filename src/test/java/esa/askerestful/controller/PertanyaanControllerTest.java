package esa.askerestful.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreatePertanyaanrReq;
import esa.askerestful.model.PertanyaanResponse;
import esa.askerestful.model.UpdatePertanyaanReq;
import esa.askerestful.model.WebResponse;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import esa.askerestful.security.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.core.type.TypeReference;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.MockMvcBuilder.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@SpringBootTest
@AutoConfigureMockMvc
class PertanyaanControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PertanyaanController pertanyaanController;

    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp(){
        userRepository.deleteAll();
        pertanyaanRepository.deleteAll();
        User user = new User();
        user.setIdUser("USER_1");
        user.setUsername("esa");
        user.setEmail("esa@gmail.com");
        user.setToken("token");
        user.setPassword(BCrypt.hashpw("esa123" , BCrypt.gensalt()));
        user.setTokenExpiredAt(System.currentTimeMillis() + 1000000000L);
        userRepository.save(user);
    }

    @Test
    void createPertanyaanBadRequest()throws Exception{
        CreatePertanyaanrReq request = new CreatePertanyaanrReq();
        request.setDeskripsi("");
        request.setHeader("salah");

        mockMvc.perform(
                post("/api/pertanyaan")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN" , "token")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });
            assertNotNull(response.getErrors());
        });
    }


    @Test
    void createPertanyaanSuccess()throws Exception{
        CreatePertanyaanrReq request = new CreatePertanyaanrReq();
        request.setHeader("ini adalah header");
        request.setDeskripsi("ini adalah deskripsi dengan kata yang seharusnyua banyakl");


         mockMvc.perform(
                post("/api/pertanyaan")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN" , "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<PertanyaanResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(request.getHeader() , response.getData().getHeader());
            assertEquals(request.getDeskripsi() , response.getData().getDeskripsi());

            assertTrue(pertanyaanRepository.existsById(response.getData().getId()));

        });
    }

    @Test
    void getPertanyaanNotFound()throws Exception{

        mockMvc.perform(
                get("/api/pertanyaan/1212")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN" , "token")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void getContactSuccess()throws Exception{
        User user = userRepository.findById("USER_1").orElseThrow();

        Pertanyaan pertanyaan = new Pertanyaan();
        pertanyaan.setIdPertanyaan("Pertanyaan_1");
        pertanyaan.setUser(user);
        pertanyaan.setHeader("ini adalah header");
        pertanyaan.setDeskripsi("ini adalah deskripsi");
        pertanyaan.setSuka(0);
        pertanyaanRepository.save(pertanyaan);


        mockMvc.perform(
                get("/api/pertanyaan/" + pertanyaan.getIdPertanyaan())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN" , "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<PertanyaanResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());

            assertEquals(pertanyaan.getIdPertanyaan() , response.getData().getId());
            assertEquals(pertanyaan.getHeader() , response.getData().getHeader());
            assertEquals(pertanyaan.getDeskripsi() , response.getData().getDeskripsi());
            assertEquals(pertanyaan.getSuka() , response.getData().getSuka());

        });
    }

    @Test
    void updatePertanyaanSuccess()throws Exception{
        User user = userRepository.findById("USER_1").orElseThrow();

        Pertanyaan pertanyaan = new Pertanyaan();
        pertanyaan.setIdPertanyaan("Pertanyaan_1");
        pertanyaan.setHeader("ini adalah header");
        pertanyaan.setDeskripsi("ini adalah deskripsi");
        pertanyaan.setSuka(0);
        pertanyaan.setUser(user);


        UpdatePertanyaanReq req = new UpdatePertanyaanReq();
        req.setHeader("ini adalah header");
        req.setDeskripsi("ini adalah deskripsi");


        mockMvc.perform(
                put("/api/pertanyaan/" + pertanyaan.getIdPertanyaan())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req))
                        .header("X-API-TOKEN" , "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<PertanyaanResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());
            assertEquals(req.getDeskripsi() , response.getData().getDeskripsi());
            assertEquals(req.getHeader() , response.getData().getHeader());


            assertTrue(pertanyaanRepository.existsById(response.getData().getId()));

        });
    }

    @Test
    void updateContactBadRequest()throws Exception{
        UpdatePertanyaanReq request = new UpdatePertanyaanReq();
        request.setDeskripsi("");
        request.setHeader("salah");

        mockMvc.perform(
                put("/api/pertanyaan/123")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
                        .header("X-API-TOKEN" , "token")
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<WebResponse<String>>() {
            });
            assertNotNull(response.getErrors());
        });
    }

    @Test
    void searchNotFound()throws Exception{

        mockMvc.perform(
                get("/api/pertanyaans" )
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN" , "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<PertanyaanResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());

            assertEquals(0 , response.getData().size());
            assertEquals(0 , response.getPaging().getTotalPage());
            assertEquals(0 , response.getPaging().getCurrentPage());
            assertEquals(10 , response.getPaging().getSize());
        });
    }

    @Test
    void searchUsingHeader()throws Exception{
        User user = userRepository.findByUsername("esa").orElseThrow();

        for (int i = 0 ; i< 100 ;  i++){
            Pertanyaan pertanyaan = new Pertanyaan();
            pertanyaan.setIdPertanyaan(UUID.randomUUID().toString());
            pertanyaan.setUser(user);
            pertanyaan.setHeader("ini header " + i);
            pertanyaan.setDeskripsi("deskripsi");
            pertanyaanRepository.save(pertanyaan);
        }
        mockMvc.perform(
                get("/api/pertanyaans" )
                        .queryParam("header" , "ini header")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN" , "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<PertanyaanResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());

            assertEquals(10 , response.getData().size());
            assertEquals(10 , response.getPaging().getTotalPage());
            assertEquals(0 , response.getPaging().getCurrentPage());
            assertEquals(10 , response.getPaging().getSize());
        });
    }

    @Test
    void searchSuccess()throws Exception{
        User user = userRepository.findByUsername("esa").orElseThrow();

        for (int i = 0 ; i< 100 ;  i++){
            Pertanyaan pertanyaan = new Pertanyaan();
            pertanyaan.setIdPertanyaan(UUID.randomUUID().toString());
            pertanyaan.setUser(user);
            pertanyaan.setHeader("ini header " + i);
            pertanyaan.setDeskripsi("ini adalah deskripsi " + i);
            pertanyaanRepository.save(pertanyaan);
        }
        mockMvc.perform(
                get("/api/pertanyaans" )
                        .queryParam("header" , "ini header ")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN" , "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<PertanyaanResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());


            assertEquals(10 , response.getData().size());
            assertEquals(10 , response.getPaging().getTotalPage());
            assertEquals(0 , response.getPaging().getCurrentPage());
            assertEquals(10 , response.getPaging().getSize());
        });

        mockMvc.perform(
                get("/api/pertanyaans" )
                        .queryParam("deskripsi" , "ini adalah deskripsi")
                        .queryParam("page" , "100")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN" , "token")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<PertanyaanResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
            });
            assertNull(response.getErrors());


            assertEquals(0 , response.getData().size());
            assertEquals(10 , response.getPaging().getTotalPage());
            assertEquals(100 , response.getPaging().getCurrentPage());
            assertEquals(10 , response.getPaging().getSize());
        });


    }
}