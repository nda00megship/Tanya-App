package esa.askerestful.service;

import esa.askerestful.entity.KredensialPekerjaan;
import esa.askerestful.entity.KredensialPendidikan;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKredPekerjaanReq;
import esa.askerestful.model.KredPekerjaanResp;
import esa.askerestful.repository.PekerjaanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PekerjaanService {

    @Autowired
    public MicroService microService;

    @Autowired
    public ValidationService validationService;

    @Autowired
    public PekerjaanRepository pekerjaanRepository;

    public String create(User user, CreateKredPekerjaanReq req){
        validationService.validate(user);

        try {
            KredensialPekerjaan kredensialPekerjaan = new KredensialPekerjaan();
            kredensialPekerjaan.setIdKredensial(UUID.randomUUID().toString());
            kredensialPekerjaan.setPosisi(req.getPosisi());
            kredensialPekerjaan.setPerusahaan(req.getPerusahaan());
            kredensialPekerjaan.setTahunMulai(req.getTahunMulai());
            kredensialPekerjaan.setTahunSelesai(req.getTahunSelesai());
            kredensialPekerjaan.setUser(user);
            pekerjaanRepository.save(kredensialPekerjaan);
        }catch (Exception e){

        }

        return "accept";
    }
}
