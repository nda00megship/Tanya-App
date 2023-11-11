package esa.askerestful.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import esa.askerestful.entity.Gambar;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.KomentarResponseL;
import esa.askerestful.model.LazyLoadingRequest;
import esa.askerestful.model.PertanyaanGambarResponse;
import esa.askerestful.model.PertanyaanResponse;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Selection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
        String sql = "SELECT s.id_gambar , p.id_pertanyaan FROM store_gambar s LEFT JOIN pertanyaan p ON s.id_pertanyaan = p.id_pertanyaan";

        Query query = entityManager.createNativeQuery(sql);
        List<Object[]> queryResultList = query.getResultList();

        return queryResultList;
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

//    @Transactional(readOnly = true)
//    public List<PertanyaanGambarResponse> getAllPertanyaanWithGambar() {
//        String sql = "SELECT p.id_pertanyaan, p.header, p.deskripsi , p.tanggal, " +
//                "GROUP_CONCAT(g.id_gambar) AS gambar , p.suka " +
//                "FROM pertanyaan p " +
//                "LEFT JOIN store_gambar g ON p.id_pertanyaan = g.id_pertanyaan " +
//                "GROUP BY p.id_pertanyaan, p.header, p.deskripsi, p.suka, p.tanggal " +
//                "ORDER BY p.suka desc";
//
//        Query query = entityManager.createNativeQuery(sql);
//
//        List<PertanyaanGambarResponse> pertanyaanResponses = new ArrayList<>();
//
//        @SuppressWarnings("unchecked")
//        List<Object[]> results = query.getResultList();
//
//        for (Object[] result : results) {
//            String id = (String) result[0];
//            String header = (String) result[1];
//            String deskripsi = (String) result[2];
//            Timestamp tanggal = (Timestamp) result[3];
//
//            List<String>gambar = result[4] != null ? Arrays.asList(((String) result[4]).split(",")) : Collections.emptyList();
//            Integer suka = (Integer) result[5];
//
//
//            PertanyaanGambarResponse response = new PertanyaanGambarResponse(id , header, deskripsi , tanggal , suka, gambar);
//            pertanyaanResponses.add(response);
//        }
//
//        return pertanyaanResponses;
//    }

    @Transactional(readOnly = true)
    public List<PertanyaanGambarResponse> getAllPertanyaanWithGambarAndKomentar() {
        String sql = "SELECT " +
                "p.id_pertanyaan, " +
                "p.header, " +
                "p.deskripsi, " +
                "p.tanggal, " +
                "p.suka, " +
                "GROUP_CONCAT(DISTINCT g.nama_gambar) AS gambar, " +
                "IFNULL(" +
                "    (" +
                "        SELECT CONCAT('[', GROUP_CONCAT(JSON_OBJECT('idKomentar', k.id_komentar, 'deskripsi', k.deskripsi)), ']')" +
                "        FROM komentar k" +
                "        WHERE p.id_pertanyaan = k.id_pertanyaan" +
                "    )," +
                "    '[]'" +
                ") AS komentar " +
                "FROM pertanyaan p " +
                "LEFT JOIN store_gambar g ON p.id_pertanyaan = g.id_pertanyaan " +
                "GROUP BY p.id_pertanyaan, p.header, p.deskripsi, p.tanggal, p.suka " +
                "ORDER BY p.suka DESC";

        Query query = entityManager.createNativeQuery(sql);

        List<PertanyaanGambarResponse> pertanyaanResponses = new ArrayList<>();

        @SuppressWarnings("unchecked")
        List<Object[]> results = query.getResultList();

        for (Object[] result : results) {
            String idPertanyaan = (String) result[0];
            String header = (String) result[1];
            String deskripsi = (String) result[2];
            Timestamp tanggal = (Timestamp) result[3];
            Integer suka = (Integer) result[4];
            List<String> gambar = result[5] != null ? Arrays.asList(((String) result[5]).split(",")) : Collections.emptyList();

            // Parsing komentar sebagai JSON Array menggunakan ObjectMapper (jackson-databind)
            ObjectMapper objectMapper = new ObjectMapper();
            List<KomentarResponseL> komentar = Collections.emptyList();
            try {
                String komentarJson = (String) result[6];
                if (komentarJson != null && !komentarJson.isEmpty()) {
                    komentar = objectMapper.readValue(komentarJson, new TypeReference<List<KomentarResponseL>>() {});
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            PertanyaanGambarResponse response = new PertanyaanGambarResponse(idPertanyaan, header, deskripsi, tanggal, suka, gambar, komentar);
            pertanyaanResponses.add(response);
        }

        return pertanyaanResponses;
    }

}
