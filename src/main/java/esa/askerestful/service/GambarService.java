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
import java.util.Optional;
import java.util.UUID;

@Service
public class GambarService {
    private final String storageDirectory = "C:\\Users\\Esa\\Documents\\Spring project\\aske-restful\\storage";


    @Autowired
    private ValidationService validationService;

    @Autowired
    private GambarRepository gambarRepository;

    @Autowired MicroService microService;


    public GambarResponse uploadGambar(User user , Pertanyaan pertanyaan , MultipartFile file)throws Exception {
        validateImageType(file);

        Gambar gambar = new Gambar();
        gambar.setIdGambar(UUID.randomUUID().toString());
        gambar.setNamaGambar(microService.idGambarGenerator());
        gambar.setPath(storageDirectory);
        gambar.setExt(getFileExtension(file.getOriginalFilename()));
        gambar.setTanggal(microService.currentTimestamp);
        gambar.setUser(user);
        gambar.setPertanyaan(pertanyaan);
        gambarRepository.save(gambar);

        String filePath = storageDirectory + File.separator + gambar.getNamaGambar() + gambar.getExt();
        try {
            file.transferTo(new File(filePath));
        } catch (IOException e) {
             throw new ResponseStatusException(HttpStatus.CONFLICT , "Gagal mengunggah gambar", e);
        }

        return GambarResponse.builder()
                .namaGambar(gambar.getNamaGambar())
                .path(gambar.getPath())
                .ext(gambar.getExt())
                .tanggal(gambar.getTanggal())
                .build();
    }
    private String getFileExtension(String fileName) {
        if (fileName != null) {
            int lastDotIndex = fileName.lastIndexOf(".");
            if (lastDotIndex != -1) {
                return fileName.substring(lastDotIndex);
            }
        }
        return "";
    }

    private void validateImageType(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType == null ||
                !(contentType.equals("image/png") || contentType.equals("image/jpeg") || contentType.equals("image/jpg"))) {
            throw new ResponseStatusException(HttpStatus.UNSUPPORTED_MEDIA_TYPE , "format image harus sesuai");
        }
    }


    public byte[] getGambar(User user ,String fileName)throws  Exception {
        Optional<Gambar> gambar = gambarRepository.findByNameAndUser(user  , fileName);
        String filePath = gambar.get().getPath() + "\\" + gambar.get().getNamaGambar() + gambar.get().getExt();
        byte[] images =  Files.readAllBytes(new File(filePath).toPath());
        return images;
    }

    public void deleteGambar(User user , String fileName)throws Exception{
        Optional<Gambar> gambar = gambarRepository.findByNameAndUser(user , fileName);
        if (gambar.isPresent()){
            String filePath = gambar.get().getPath() + "\\" + gambar.get().getNamaGambar() + gambar.get().getExt();
            File file = new File(filePath);

            if (file.exists()){
                if (file.delete()){
                    gambarRepository.delete(gambar.get());
                }else {
                    throw new ResponseStatusException(HttpStatus.UNAUTHORIZED , "gagal terhapus");
                }
            }else {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND , "gambar not found");
            }
        }

    }
}
