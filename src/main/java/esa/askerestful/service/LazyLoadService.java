package esa.askerestful.service;

import esa.askerestful.repository.PertanyaanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LazyLoadService {

    @Autowired
    private PertanyaanRepository pertanyaanRepo;

    @Autowired
    private MicroService microService;

    @Autowired
    private ValidationService validationService;


}
