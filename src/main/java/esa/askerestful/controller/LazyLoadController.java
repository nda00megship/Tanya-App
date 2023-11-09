package esa.askerestful.controller;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.*;
import esa.askerestful.service.LazyLoadingService;
import esa.askerestful.service.PertanyaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LazyLoadController {
    @Autowired
    private LazyLoadingService lazyLoadingService;

    @GetMapping(
            path = "/api/beranda",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebPagingResponse<List<PertanyaanResponse>> beranda(
            User user,
            @RequestParam(value = "page" , required = false , defaultValue = "0")Integer page,
            @RequestParam(value = "size" , required = false , defaultValue = "30")Integer size
    ){
        LazyLoadingRequest request = LazyLoadingRequest.builder()
                .page(page)
                .size(size)
                .build();

        Page<PertanyaanResponse> pertanyaanResponses = lazyLoadingService.lazyLoading(user , request);

        return WebPagingResponse.<List<PertanyaanResponse>>builder()
                .data(pertanyaanResponses.getContent())
                .paging(PagingResponse.builder()
                        .totalPage(pertanyaanResponses.getTotalPages())
                        .size(pertanyaanResponses.getSize())
                        .build()
                )
                .build();
    }

    @GetMapping(
            path = "/api/beranda-gambar",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<PertanyaanResponse>> getPertanyaanGambar() {
        List<Object[]> pertanyaan = lazyLoadingService.viewPertanyaan();
        List<PertanyaanResponse> pertanyaanResponses = new ArrayList<>();

        for (Object[] row : pertanyaan) {
            String idPertanyaan = (String) row[0];
            String header = (String) row[1];
            String deskripsi = (String) row[2];
            Integer suka = (Integer) row[3];
            Timestamp tanggal = (Timestamp) row[4];
//            for (Object[] rowGambar : gambar){
//                String idGambar = (String) rowGambar[0];
//                List<String> temp = new ArrayList<>();
//                temp.add(idGambar);
//                PertanyaanGambarResponse response =
//                        new PertanyaanGambarResponse(idPertanyaan, header, deskripsi, suka, tanggal, temp);
//                pertanyaanGambarResponses.add(response);
//            }

            PertanyaanResponse response = new PertanyaanResponse(idPertanyaan , header, deskripsi , suka , tanggal);
            pertanyaanResponses.add(response);
        }

        return new ResponseEntity<>(pertanyaanResponses, HttpStatus.OK);
    }

    @GetMapping(
            path = "/api/test",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<GambarObjectResponse>> test()throws Exception{
        List<Object[]> gambar = lazyLoadingService.viewGambarPertanyaan();
        List<GambarObjectResponse> responses = new ArrayList<>();

        for (Object[] row : gambar){
            String idGambar = (String) row[0];
            GambarObjectResponse response = new GambarObjectResponse(idGambar);
            responses.add(response);
        }
        return new ResponseEntity<>(responses , HttpStatus.OK);
    }

}
