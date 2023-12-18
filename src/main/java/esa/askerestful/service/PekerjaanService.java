package esa.askerestful.service;

import esa.askerestful.entity.KredensialPekerjaan;
import esa.askerestful.entity.KredensialPendidikan;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKredPekerjaanReq;
import esa.askerestful.model.KredPekerjaanResp;
import esa.askerestful.model.UpdateKredPekerjaan;
import esa.askerestful.repository.PekerjaanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class PekerjaanService {

    @Autowired
    public MicroService microService;

    @Autowired
    public ValidationService validationService;

    @Autowired
    public PekerjaanRepository pekerjaanRepository;

    public KredPekerjaanResp create(User user, CreateKredPekerjaanReq req){
        validationService.validate(user);
        KredensialPekerjaan kredensialPekerjaan = new KredensialPekerjaan();

        Optional<KredensialPekerjaan> kp = pekerjaanRepository
                .findByUser(user);

        if(kp.isEmpty()){
            kredensialPekerjaan.setIdKredensialPekerjaan(UUID.randomUUID().toString());
            kredensialPekerjaan.setPosisi(req.getPosisi());
            kredensialPekerjaan.setPerusahaan(req.getPerusahaan());
            kredensialPekerjaan.setTahunMulai(req.getTahunMulai());
            kredensialPekerjaan.setTahunSelesai(req.getTahunSelesai());
            kredensialPekerjaan.setUser(user);
            pekerjaanRepository.save(kredensialPekerjaan);
        }else {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "kredensial sudah dibuat");
        }




        return resp(kredensialPekerjaan);
    }

    private KredPekerjaanResp resp(KredensialPekerjaan kred){
        return KredPekerjaanResp.builder()
                .idKredpekerjaan(kred.getIdKredensialPekerjaan())
                .perusahaan(kred.getPerusahaan())
                .posisi(kred.getPosisi())
                .tahunMulai(kred.getTahunMulai())
                .tahunSelesai(kred.getTahunSelesai())
                .build();
    }

    @Transactional(readOnly = true)
    public KredPekerjaanResp get(String username){

        KredensialPekerjaan kredensialPekerjaan = pekerjaanRepository
                .findByUsername(username)
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "kredensial pekerjaan tidak ditemukan"
                        ));

        return resp(kredensialPekerjaan);
    }

    @Transactional
    public KredPekerjaanResp update(User user, UpdateKredPekerjaan req, String idKredPekerjaan){
        validationService.validate(user);
        validationService.validate(idKredPekerjaan);

        KredensialPekerjaan kredensialPekerjaan = pekerjaanRepository
                .findFirstByUserAndId(user, idKredPekerjaan)
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "kredensial pekerjaan tidak ditemukan"
                ));

        kredensialPekerjaan.setPosisi(req.getPosisi());
        kredensialPekerjaan.setPerusahaan(req.getPerusahaan());
        kredensialPekerjaan.setTahunMulai(req.getTahunMulai());
        kredensialPekerjaan.setTahunSelesai(req.getTahunSelesai());

        return resp(kredensialPekerjaan);
    }

    @Transactional
    public void delete(User user, String idKredPekerjaan){
        validationService.validate(user);
        validationService.validate(idKredPekerjaan);

        KredensialPekerjaan kredensialPekerjaan = pekerjaanRepository
                .findFirstByUserAndId(user, idKredPekerjaan)
                .orElseThrow( () -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "kredensial pekerjaan tidak ditemukan"
                ));

        pekerjaanRepository.delete(kredensialPekerjaan);
    }
}
