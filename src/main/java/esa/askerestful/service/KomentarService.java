package esa.askerestful.service;

import esa.askerestful.entity.Komentar;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKomentarRequest;
import esa.askerestful.model.KomentarResponse;
import esa.askerestful.repository.KomentarRepository;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class KomentarService {


    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    @Autowired
    private MicroService microService;

    @Autowired
    private ValidationService validationService;

    public KomentarResponse create(User user , CreateKomentarRequest request){
        validationService.validate(request);

        Pertanyaan pertanyaan = pertanyaanRepository.findFirstByUserAndId(user , request.getIdPertanyaan())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND ,
                        "pertanyaan not found"
                ));

        Komentar komentar = new Komentar();
        komentar.setIdKomentar(microService.idKomentarGenerator());
        komentar.setDeskripsi(request.getDeskripsi());
        komentar.setTanggal(microService.currentTimestamp);
        komentar.setUser(user);
        komentar.setPertanyaan(pertanyaan);

        komentarRepository.save(komentar);

        return KomentarResponse.builder()
                .id_komentar(komentar.getIdKomentar())
                .deskripsi(komentar.getDeskripsi())
                .tanggal(komentar.getTanggal())
                .id_pertanyaan(pertanyaan.getIdPertanyaan())
                .build();
    }

    @Transactional
    public void delete(User user , String idKomentar){
        Komentar komentar = komentarRepository.findFirstByUserAndId(user , idKomentar)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND ,
                        "komentar not found"
                ));

        komentarRepository.delete(komentar);
    }
}
