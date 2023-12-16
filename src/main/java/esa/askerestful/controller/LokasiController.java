package esa.askerestful.controller;

import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKredLokasiReq;
import esa.askerestful.model.WebResponse;
import esa.askerestful.service.LokasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.print.attribute.standard.Media;

@RestController
public class LokasiController {

    @Autowired
    public LokasiService lokasiService;

    @PostMapping(
            path = "/api/lokasi",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> create(User user, @RequestBody CreateKredLokasiReq req){
        lokasiService.create(user , req);

        return WebResponse.<String>builder()
                .data("accept")
                .build();
    }
}
