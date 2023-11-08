package esa.askerestful.controller;

import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.*;
import esa.askerestful.service.PertanyaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PertanyaanController {

    @Autowired
    private PertanyaanService pertanyaanService;

    @PostMapping(
            path = "/api/pertanyaan",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PertanyaanResponse> create(User user , @RequestBody CreatePertanyaanrReq  req){
        PertanyaanResponse pertanyaanResponse = pertanyaanService.create(user , req);

        return WebResponse.<PertanyaanResponse>builder()
                .data(pertanyaanResponse)
                .build();
    }

    @GetMapping(
            path = "/api/pertanyaan/{idPertanyaan}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PertanyaanResponse> get(User user ,@PathVariable("idPertanyaan") String id){
        PertanyaanResponse pertanyaanResponse = pertanyaanService.get(user , id);

        return WebResponse.<PertanyaanResponse>builder()
                .data(pertanyaanResponse)
                .build();
    }

    @PatchMapping(
            path = "/api/pertanyaan/{idPertanyaan}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<PertanyaanResponse> update(User user ,
                                               @RequestBody UpdatePertanyaanReq request,
                                               @PathVariable("idPertanyaan") String idPertanyaan){
        PertanyaanResponse pertanyaanResponse = pertanyaanService.update(user, request ,idPertanyaan);
        return WebResponse.<PertanyaanResponse>builder()
                .data(pertanyaanResponse)
                .build();
    }

    @DeleteMapping(
            path = "/api/pertanyaan/{idPertanyaan}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user ,  @PathVariable("idPertanyaan") String idPertanyaan){
        pertanyaanService.remove(user , idPertanyaan);

        return WebResponse.<String>builder()
                .data("terhapus")
                .build();
    }

    @GetMapping(
            path = "/api/pertanyaans" ,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebPagingResponse<List<PertanyaanResponse>> search(
            User user,
            @RequestParam(value = "header" , required = false) String header ,
            @RequestParam(value = "deskripsi" , required = false) String deskripsi ,
            @RequestParam(value = "page" , required = false, defaultValue = "0") Integer page ,
            @RequestParam(value = "size" , required = false, defaultValue = "10") Integer size
    ){
        SearchPertanyaanRequest request = SearchPertanyaanRequest.builder()
                .page(page)
                .size(size)
                .header(header)
                .deskripsi(deskripsi)
                .build();

        Page<PertanyaanResponse> pertanyaanResponses = pertanyaanService.search(user , request);
        return WebPagingResponse.<List<PertanyaanResponse>>builder()
                .data(pertanyaanResponses.getContent())
                .paging(PagingResponse.builder()
                        .totalPage(pertanyaanResponses.getTotalPages())
                        .size(pertanyaanResponses.getSize())
                        .build())
                .build();
    }




}
