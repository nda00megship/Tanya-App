package esa.askerestful.controller;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.*;
import esa.askerestful.service.LazyLoadingService;
import esa.askerestful.service.PertanyaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class LazyLoadController {
    @Autowired
    private LazyLoadingService lazyLoadingService;

    @GetMapping(
            path = "/api/beranda",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<PertanyaanResponse>> beranda(
            User user,
            @RequestParam(value = "page" , required = false , defaultValue = "0")Integer page,
            @RequestParam(value = "size" , required = false , defaultValue = "30")Integer size
    ){
        LazyLoadingRequest request = LazyLoadingRequest.builder()
                .page(page)
                .size(size)
                .build();

        Page<PertanyaanResponse> pertanyaanResponses = lazyLoadingService.lazyLoading(user , request);

        return WebResponse.<List<PertanyaanResponse>>builder()
                .data(pertanyaanResponses.getContent())
                .paging(PagingResponse.builder()
                        .totalPage(pertanyaanResponses.getTotalPages())
                        .size(pertanyaanResponses.getSize())
                        .build()
                )
                .build();
    }

}
