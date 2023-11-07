package esa.askerestful.service;

import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.LazyLoadingRequest;
import esa.askerestful.model.PertanyaanResponse;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;



@Service
public class LazyLoadingService {

    @Autowired
    private MicroService microService;

    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<PertanyaanResponse> lazyLoading(User user , LazyLoadingRequest request){
        Specification<Pertanyaan> specification = (((root, query, criteriaBuilder) -> {

            query.orderBy(criteriaBuilder.desc(root.get("suka")));


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

    private PertanyaanResponse toPertanyaanResponse(Pertanyaan pertanyaan){
        return PertanyaanResponse.builder()
                .id(pertanyaan.getIdPertanyaan())
                .header(pertanyaan.getHeader())
                .deskripsi(pertanyaan.getDeskripsi())
                .suka(pertanyaan.getSuka())
                .build();
    }

}
