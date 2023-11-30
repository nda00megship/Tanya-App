package esa.askerestful.controller;

import esa.askerestful.entity.Gambar;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.*;
import esa.askerestful.service.GambarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class GambarController {

    @Autowired
    private GambarService gambarService;

    @PostMapping(
            path = "/api/gambar",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public FileResponse<String> uploadGambar(User user , Pertanyaan pertanyaan, @RequestBody CreateGambarRequest gambarRequest) {
        try {
            GambarResponse gambarResponse = gambarService.uploadGambar(user ,pertanyaan, gambarRequest);
            return FileResponse.<String>builder()
                    .data("berhasil upload")
                    .build();
        } catch (ResponseStatusException e) {
            throw e;
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Terjadi kesalahan dalam mengunggah gambar", e);
        }
    }

    @GetMapping(
            path = "/api/gambar/{namaGambar}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public FileResponse<GambarResponse> getGambar(User user , Pertanyaan pertanyaan, @PathVariable("namaGambar") String namaGambar){
        GambarResponse gambarResponse = gambarService.getGambar(namaGambar, user);
        return FileResponse.<GambarResponse>builder()
                .data(gambarResponse)
                .build();
    }


}




