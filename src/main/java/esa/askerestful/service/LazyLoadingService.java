package esa.askerestful.service;

import esa.askerestful.entity.Gambar;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.LazyLoadingRequest;
import esa.askerestful.model.PertanyaanResponse;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;



@Service
public class LazyLoadingService {
    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly = true)
    public Page<PertanyaanResponse> lazyLoading(User user , LazyLoadingRequest request){
        Specification<Pertanyaan> specification = (((root, query, criteriaBuilder) -> {
            query.orderBy(criteriaBuilder.desc(root.get("suka")));
            root.join("Gambar" , JoinType.LEFT);
            root.get("Gambar").get("idGambar");
            return criteriaBuilder.and();
        }));
        Pageable pageable = PageRequest.of(
                request.getPage() , request.getSize()
        );

        Page<Pertanyaan> pertanyaans = pertanyaanRepository.findAll(specification , pageable);

        List<PertanyaanResponse> pertanyaanResponses = pertanyaans.getContent()
                .stream().map(this::toPertanyaanResponse)
                .toList();

        return new PageImpl<>(
                pertanyaanResponses , pageable , pertanyaans.getTotalElements()
        );
    }


    public List<Object[]> viewPertanyaan() {
        String sql = "SELECT p.id_pertanyaan, p.header, p.deskripsi, p.suka, p.tanggal, g.id_gambar " +
                "FROM pertanyaan p " +
                "LEFT JOIN store_gambar g ON p.id_pertanyaan = g.id_pertanyaan";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> resultList = query.getResultList();

        return resultList;
    }


    public List<Object[]> viewGambarPertanyaan() {
        String sql = "SELECT s.id_gambar FROM store_gambar s LEFT JOIN pertanyaan p ON s.id_pertanyaan = p.id_pertanyaan";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> resultList = query.getResultList();

        return resultList;
    }



    private PertanyaanResponse toPertanyaanResponse(Pertanyaan pertanyaan ){
        return PertanyaanResponse.builder()
                .id(pertanyaan.getIdPertanyaan())
                .header(pertanyaan.getHeader())
                .deskripsi(pertanyaan.getDeskripsi())
                .tanggal(pertanyaan.getTanggal())
                .suka(pertanyaan.getSuka())
                .build();
    }

}
