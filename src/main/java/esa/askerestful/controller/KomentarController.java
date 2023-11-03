package esa.askerestful.controller;

import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKomentarRequest;
import esa.askerestful.model.KomentarResponse;
import esa.askerestful.model.WebResponse;
import esa.askerestful.service.KomentarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class KomentarController {

    @Autowired
    private KomentarService komentarService;

    @PostMapping(
            path = "/api/komentar",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<KomentarResponse> create (User user  , @RequestBody CreateKomentarRequest request)
    {
    KomentarResponse komentarResponse =  komentarService.create(user , request );

        return WebResponse.<KomentarResponse>builder()
                .data(komentarResponse)
                .build();
    }

    @DeleteMapping(
            path = "/api/komentar/{idKomentar}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user , @PathVariable("idKomentar") String id){
        komentarService.delete(user , id);

        return WebResponse.<String>builder()
                .data("komentar telah terhapus")
                .build();
    }
}
