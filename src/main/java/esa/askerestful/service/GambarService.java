package esa.askerestful.service;

import esa.askerestful.entity.Gambar;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreateGambarRequest;
import esa.askerestful.model.GambarResponse;
import esa.askerestful.repository.GambarRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.UnsupportedMediaTypeStatusException;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@Service
public class GambarService {

    @Autowired
    private ValidationService validationService;

    @Autowired
    private GambarRepository gambarRepository;

    @Autowired
    private MicroService microService;


    public GambarResponse uploadGambar(User user , Pertanyaan pertanyaan, CreateGambarRequest gambarRequest)throws Exception {
        validationService.validate(pertanyaan);

        if(gambarRepository.existByPath(gambarRequest.getPath())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "path sudah ada pakai yang lain");
        }
        Gambar gambar = new Gambar();
        gambar.setIdGambar(UUID.randomUUID().toString());
        gambar.setNamaGambar(gambarRequest.getNamaGambar());
        gambar.setPath(gambarRequest.getPath());
        gambar.setTanggal(microService.currentTimestamp);
        gambar.setUser(user);
        if(Objects.nonNull(pertanyaan)){
            gambar.setPertanyaan(pertanyaan);
        }
        gambarRepository.save(gambar);

        return GambarResponse.builder()
                .namaGambar(gambar.getNamaGambar())
                .tanggal(gambar.getTanggal())
                .build();
    }

    public GambarResponse getGambar(String namaGambar , User user){
        Gambar gambar = gambarRepository.findByName(namaGambar)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND ,
                        "gambar tidak ditemukan"
                ));

        return GambarResponse.builder()
                .namaGambar(gambar.getNamaGambar())
                .path(gambar.getPath())
                .tanggal(gambar.getTanggal())
                .build();
    }
}
