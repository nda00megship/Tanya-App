package esa.askerestful.service;

import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.LazyLoadingRequest;
import esa.askerestful.model.PertanyaanResponse;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.jpa.domain.Specification;


@Service
public class LazyLoadingService {

    @Autowired
    private MicroService microService;

    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<PertanyaanResponse> lazyLoad(User user, LazyLoadingRequest request) {
        Specification<Pertanyaan> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            query.orderBy(criteriaBuilder.desc(root.get("suka")));
            // Anda dapat menambahkan kriteria pencarian lainnya di sini jika diperlukan
            predicates.add(criteriaBuilder.equal(root.get("user") , user));
            return query.where(predicates.toArray(
                    new Predicate[]{}
            )).getRestriction();
        };

        // Membuat objek PageRequest untuk menentukan halaman dan ukuran halaman
        PageRequest pageRequest = PageRequest.of(request.getPage(), request.getSize());

        // Menggunakan repository dan specification untuk melakukan pencarian dengan paginasi
        Page<Pertanyaan> resultPage = pertanyaanRepository.findAll(specification, pageRequest);

        // Anda dapat mengonversi hasil pencarian ke bentuk yang sesuai dengan kebutuhan Anda, seperti PertanyaanResponse
        List<PertanyaanResponse> pertanyaanResponses = convertToPertanyaanResponse(resultPage.getContent());

        // Mengembalikan hasil dengan Page yang sesuai
        return new PageImpl<>(pertanyaanResponses, pageRequest, resultPage.getTotalElements());
    }

    // Fungsi untuk mengonversi dari Pertanyaan ke PertanyaanResponse
    private List<PertanyaanResponse> convertToPertanyaanResponse(List<Pertanyaan> pertanyaanList) {
        return pertanyaanList.stream()
                .map(pertanyaan -> {
                    PertanyaanResponse response = new PertanyaanResponse();
                    response.setHeader(pertanyaan.getHeader());
                    response.setDeskripsi(pertanyaan.getDeskripsi());
                    response.setSuka(pertanyaan.getSuka());
                    // Implementasikan konversi lainnya sesuai kebutuhan
                    return response;
                })
                .collect(Collectors.toList());
    }



}
