package esa.askerestful.service;

import esa.askerestful.entity.KredensialPendidikan;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKredPendidikanReq;
import esa.askerestful.model.KredPekerjaanResp;
import esa.askerestful.model.KredPendidikanResp;
import esa.askerestful.repository.PendidikanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class PendidikanService {

    @Autowired
    public MicroService microService;

    @Autowired
    public ValidationService validationService;

    @Autowired
    public PendidikanRepository pendidikanRepository;

    public KredPendidikanResp create(User user , CreateKredPendidikanReq req){
        validationService.validate(user);

            KredensialPendidikan kredensialPendidikan = new KredensialPendidikan();
            kredensialPendidikan.setIdKredensialPendidikan(UUID.randomUUID().toString());
            kredensialPendidikan.setSekolah(req.getSekolah());
            kredensialPendidikan.setJurusan(req.getJurusan());
            kredensialPendidikan.setJenisGelar(req.getJenisGelar());
            kredensialPendidikan.setTahunKelulusan(req.getTahunLulus());
            kredensialPendidikan.setUser(user);
            pendidikanRepository.save(kredensialPendidikan);

        return response(kredensialPendidikan);
    }

    private KredPendidikanResp response(KredensialPendidikan req){
        return KredPendidikanResp.builder()
                .idKredPendidikan(req.getIdKredensialPendidikan())
                .sekolah(req.getSekolah())
                .jurusan(req.getJurusan())
                .jenisGelar(req.getJenisGelar())
                .tahunLulus(req.getTahunKelulusan())
                .build();
    }

    @Transactional(readOnly = true)
    public KredPendidikanResp get(User user, String idKredPendidikan){
        validationService.validate(user);

        KredensialPendidikan kredensialPendidikan = pendidikanRepository
                .findFirstByUserAndId(user, idKredPendidikan)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "kredensial pendidikan tidak ditemukan"
                        ));

        return response(kredensialPendidikan);
    }

    @Transactional
    public KredPendidikanResp update(User user, CreateKredPendidikanReq req, String idKredPendidikan){
        validationService.validate(user);
        validationService.validate(idKredPendidikan);

        KredensialPendidikan kredensialPendidikan = pendidikanRepository
                .findFirstByUserAndId(user, idKredPendidikan)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "kredensial pendidikan tidak ditemukan"
                ));

        try {
            kredensialPendidikan.setSekolah(req.getSekolah());
            kredensialPendidikan.setJurusan(req.getJurusan());
            kredensialPendidikan.setJenisGelar(req.getJenisGelar());
            kredensialPendidikan.setTahunKelulusan(req.getTahunLulus());
        }catch (Exception e){
            e.printStackTrace();
        }

        return response(kredensialPendidikan);
    }
}
