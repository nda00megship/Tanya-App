package esa.askerestful.service;

import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreatePertanyaanrReq;
import esa.askerestful.model.PertanyaanResponse;
import esa.askerestful.model.UpdatePertanyaanReq;
import esa.askerestful.model.WebResponse;
import esa.askerestful.repository.PertanyaanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
public class PertanyaanService {

    @Autowired
    private PertanyaanRepository pertanyaanRepo;

    @Autowired
    private MicroService microService;

    @Autowired
    private ValidationService validationService;

    public PertanyaanResponse create(User user , CreatePertanyaanrReq req){
        validationService.validate(req);

        Pertanyaan pertanyaan = new Pertanyaan();
        pertanyaan.setIdPertanyaan(microService.idPertanyaanGenerator());
        pertanyaan.setHeader(req.getHeader());
        pertanyaan.setDeskripsi(req.getDeskripsi());
        pertanyaan.setSuka(0);
        pertanyaan.setUser(user);

        pertanyaanRepo.save(pertanyaan);

        return PertanyaanResponse.builder()
                .id(pertanyaan.getIdPertanyaan())
                .header(pertanyaan.getHeader())
                .deskripsi(pertanyaan.getDeskripsi())
                .suka(pertanyaan.getSuka())
                .build();
    }

    private PertanyaanResponse toPertanyaanResponse(Pertanyaan pertanyaan){
        return PertanyaanResponse.builder()
                .id(pertanyaan.getIdPertanyaan())
                .header(pertanyaan.getHeader())
                .deskripsi(pertanyaan.getDeskripsi())
                .suka(pertanyaan.getSuka())
                .build();
    }

    @Transactional(readOnly = true)
    public PertanyaanResponse get(User user ,String id){
        validationService.validate(user);

        Pertanyaan pertanyaan = pertanyaanRepo.findFirstByUserAndId(user , id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND ,
                        "pertanyaan not found"
                        ));
        return toPertanyaanResponse(pertanyaan);
    }

    public PertanyaanResponse update(User user , UpdatePertanyaanReq request){
        validationService.validate(request);

        Pertanyaan pertanyaan = pertanyaanRepo.findFirstByUserAndId(user, request.getIdPertanyaan())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Contact not Found"
                ));

        pertanyaan.setHeader(request.getHeader());
        pertanyaan.setDeskripsi(request.getDeskripsi());

        pertanyaanRepo.save(pertanyaan);

        return toPertanyaanResponse(pertanyaan);
    }

    @Transactional
    public void remove(User user , String idPertanyaan){
        Pertanyaan pertanyaan = pertanyaanRepo.findFirstByUserAndId(user , idPertanyaan)
                        .orElseThrow(()-> new ResponseStatusException(
                                HttpStatus.NOT_FOUND ,
                                "pertanyaan tidak ditemukan"
                                ));

        pertanyaanRepo.delete(pertanyaan);
    }


}
