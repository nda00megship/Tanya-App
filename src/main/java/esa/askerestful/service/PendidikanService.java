package esa.askerestful.service;

import esa.askerestful.entity.KredensialPendidikan;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKredPendidikanReq;
import esa.askerestful.repository.PendidikanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PendidikanService {

    @Autowired
    public MicroService microService;

    @Autowired
    public ValidationService validationService;

    @Autowired
    public PendidikanRepository pendidikanRepository;

    public String create(User user , CreateKredPendidikanReq req){
        validationService.validate(user);

        try{
            KredensialPendidikan kredensialPendidikan = new KredensialPendidikan();
            kredensialPendidikan.setIdKredensialPendidikan(UUID.randomUUID().toString());
            kredensialPendidikan.setSekolah(req.getSekolah());
            kredensialPendidikan.setJurusan(req.getJurusan());
            kredensialPendidikan.setJenisGelar(req.getJenisGelar());
            kredensialPendidikan.setTahunKelulusan(req.getTahunLulus());
            kredensialPendidikan.setUser(user);
            pendidikanRepository.save(kredensialPendidikan);
        }catch (Exception e){
            throw e;
        }

        return "accept";
    }
}
