package esa.askerestful.service;

import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.repository.PertanyaanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LazyLoadService {

    @Autowired
    private PertanyaanRepository pertanyaanRepo;

    @Autowired
    private MicroService microService;

    @Autowired
    private ValidationService validationService;

//    public List<Pertanyaan> getPertannyaan(){
//
//    }
}
