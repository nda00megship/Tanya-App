package esa.askerestful.controller;

import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKredPendidikanReq;
import esa.askerestful.model.WebResponse;
import esa.askerestful.service.LokasiService;
import esa.askerestful.service.PendidikanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PendidikanController {

    @Autowired
    public PendidikanService pendidikanService;

    @PostMapping(
            path = "/api/pendidikan",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> create(User user , @RequestBody CreateKredPendidikanReq req){
        pendidikanService.create(user, req);

        return WebResponse.<String>builder()
                .data("accept")
                .build();
    }
}
