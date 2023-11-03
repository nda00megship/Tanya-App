package esa.askerestful.service;

import esa.askerestful.entity.Gambar;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreateGambarRequest;
import esa.askerestful.model.GambarResponse;
import esa.askerestful.repository.GambarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.util.Optional;

@Service
public class GambarService {
    private final String storageDirectory = "C:\\Users\\Esa\\Documents\\Spring project\\aske-restful\\storage";


    @Autowired
    private ValidationService validationService;

    @Autowired
    private GambarRepository gambarRepository;

    @Autowired MicroService microService;


    public GambarResponse create(User user , MultipartFile file)throws Exception{
        validationService.validate(file);

        Gambar gambar = new Gambar();
        gambar.setIdGambar(microService.idGambarGenerator());
        gambar.setNamaGambar(microService.idGambarGenerator());
        gambar.setPath(storageDirectory);
        gambar.setExt(file.getContentType());
        gambar.setTanggal(microService.currentTimestamp);
        gambar.setUser(user);

        gambarRepository.save(gambar);
        String filePath =  gambar.getNamaGambar() + gambar.getExt();
        file.transferTo(new File(filePath));

        return GambarResponse.builder()
                .namaGambar(gambar.getNamaGambar())
                .path(gambar.getPath())
                .ext(gambar.getExt())
                .tanggal(gambar.getTanggal())
                .build();
    }

    public byte[] getGambar(String fileName)throws  Exception{
        Optional<Gambar> gambar = gambarRepository.findById(fileName);

        String fileData = gambar.get().getPath();
        byte[] tempGambar = Files.readAllBytes(new File(fileData).toPath());

        return tempGambar;
    }
}
