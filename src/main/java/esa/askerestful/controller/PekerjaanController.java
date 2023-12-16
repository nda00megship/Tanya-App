package esa.askerestful.controller;

import esa.askerestful.entity.KredensialPekerjaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKredPekerjaanReq;
import esa.askerestful.model.KredPekerjaanResp;
import esa.askerestful.model.WebResponse;
import esa.askerestful.service.PekerjaanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PekerjaanController {

    @Autowired
    public PekerjaanService pekerjaanService;

    @PostMapping(
            path = "/api/pekerjaan",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<KredPekerjaanResp> create(User user, @RequestBody CreateKredPekerjaanReq req){
        KredPekerjaanResp kredensialPekerjaan = pekerjaanService.create(user , req);

        return WebResponse.<KredPekerjaanResp>builder()
                .data(kredensialPekerjaan)
                .build();
    }


    @GetMapping(
            path = "/api/pekerjaan/{idKredPekerjaan}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<KredPekerjaanResp> get(User user, @PathVariable("idKredPekerjaan") String id){
        KredPekerjaanResp kredPekerjaanResp = pekerjaanService.get(user, id);

        return WebResponse.<KredPekerjaanResp>builder()
                .data(kredPekerjaanResp)
                .build();
    }
}
