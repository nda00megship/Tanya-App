package esa.askerestful.controller;

import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKredPekerjaanReq;
import esa.askerestful.model.WebResponse;
import esa.askerestful.service.PekerjaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PekerjaanController {

    @Autowired
    public PekerjaanService pekerjaanService;

    @PostMapping(
            path = "/api/pekerjaan",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> create(User user, @RequestBody CreateKredPekerjaanReq req){
        pekerjaanService.create(user , req);

        return WebResponse.<String>builder()
                .data("accept")
                .build();
    }
}
