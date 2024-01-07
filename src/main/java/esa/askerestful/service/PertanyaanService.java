package esa.askerestful.service;

import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.*;
import esa.askerestful.repository.PertanyaanRepository;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;


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
        pertanyaan.setTanggal(microService.currentTimestamp);

        pertanyaanRepo.save(pertanyaan);

        return toPertanyaanResponse(pertanyaan);
    }

    private List<PertanyaanResponse> toPertanyaanResponseList(List<Pertanyaan> pertanyaanList) {
        return pertanyaanList.stream()
                .map(pertanyaan -> PertanyaanResponse.builder()
                        .id(pertanyaan.getIdPertanyaan())
                        .header(pertanyaan.getHeader())
                        .deskripsi(pertanyaan.getDeskripsi())
                        .suka(pertanyaan.getSuka())
                        .tanggal(pertanyaan.getTanggal())
                        .build())
                .collect(Collectors.toList());
    }


    private PertanyaanResponse toPertanyaanResponse(Pertanyaan pertanyaan){
        return PertanyaanResponse.builder()
                .id(pertanyaan.getIdPertanyaan())
                .header(pertanyaan.getHeader())
                .deskripsi(pertanyaan.getDeskripsi())
                .suka(pertanyaan.getSuka())
                .tanggal(pertanyaan.getTanggal())
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

    public List<PertanyaanResponse> getPertanyaanByUsername(String username){
        validationService.validate(username);

        List<Pertanyaan> pertanyaanList = pertanyaanRepo.getPertanyaanByUsername(username);

        if (pertanyaanList.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Pertanyaan not found"
            );
        }

        return toPertanyaanResponseList(pertanyaanList);
    }

    @Transactional
    public PertanyaanResponse update(User user ,UpdatePertanyaanReq req,  String idPertanyaan){
        validationService.validate(idPertanyaan);
        validationService.validate(req);
        Pertanyaan pertanyaan = pertanyaanRepo.findFirstByUserAndId(user , idPertanyaan)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND ,
                        "pertanyaan not found"
                ));
        if(Objects.nonNull(req.getHeader())){
            pertanyaan.setHeader(req.getHeader());
        }
        if(Objects.nonNull(req.getDeskripsi())){
            pertanyaan.setDeskripsi(req.getDeskripsi());
        }

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

    @Transactional(readOnly = true)
    public Page<PertanyaanResponse> search(User user, SearchPertanyaanRequest request) {
        Specification<Pertanyaan> specification = ((root, query, builder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (Objects.nonNull(request.getHeader())) {
                predicates.add(builder.like(root.get("header"), "%" + request.getHeader() + "%"));
            }

            if (Objects.nonNull(request.getDeskripsi())) {
                predicates.add(builder.like(root.get("deskripsi"), "%" + request.getDeskripsi() + "%"));
            }

            return builder.or(predicates.toArray(new Predicate[]{}));
        });

        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Pertanyaan> pertanyaans = pertanyaanRepo.findAll(specification, pageable);

        List<PertanyaanResponse> pertanyaanResponses = pertanyaans.getContent()
                .stream()
                .map(this::toPertanyaanResponse)
                .toList();

        return new PageImpl<>(pertanyaanResponses, pageable, pertanyaans.getTotalElements());
    }



}
