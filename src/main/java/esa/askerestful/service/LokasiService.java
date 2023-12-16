package esa.askerestful.service;

import esa.askerestful.entity.KredensialLokasi;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKredLokasiReq;
import esa.askerestful.repository.LokasiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class LokasiService {

    @Autowired
    public MicroService microService;

    @Autowired
    public LokasiRepository lokasiRepository;

    @Autowired
    public ValidationService validationService;

    public String create(User user, CreateKredLokasiReq req){
        validationService.validate(user);

        try {
            KredensialLokasi kredensialLokasi = new KredensialLokasi();
            kredensialLokasi.setIdKredensialLokasi(UUID.randomUUID().toString());
            kredensialLokasi.setLokasi(req.getLokasi());
            kredensialLokasi.setTahunMulai(req.getTahunMulai());
            kredensialLokasi.setTahunSelesai(req.getTahunSelesai());
            kredensialLokasi.setUser(user);

            lokasiRepository.save(kredensialLokasi);
        }catch (Exception e){

        }

        return "accept";
    }
}
