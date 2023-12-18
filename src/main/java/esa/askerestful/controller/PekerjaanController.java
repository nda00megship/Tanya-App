package esa.askerestful.controller;

import esa.askerestful.entity.KredensialPekerjaan;
import esa.askerestful.entity.User;
import esa.askerestful.model.*;
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
            path = "/api/pekerjaan/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<KredPekerjaanResp> get(@PathVariable("username") String user){
        KredPekerjaanResp kredPekerjaanResp = pekerjaanService.get(user);

        return WebResponse.<KredPekerjaanResp>builder()
                .data(kredPekerjaanResp)
                .build();
    }

    @PutMapping(
            path = "/api/pekerjaan/{idKredPekerjaan}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<KredPekerjaanResp> update(User user, @RequestBody UpdateKredPekerjaan req
            , @PathVariable("idKredPekerjaan") String id){

        KredPekerjaanResp kredPendidikanResp = pekerjaanService.update(user, req,id);

        return WebResponse.<KredPekerjaanResp>builder()
                .data(kredPendidikanResp)
                .build();
    }

    @DeleteMapping(
            path = "/api/pekerjaan/{idKredPekerjaan}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user, @PathVariable("idKredPekerjaan") String id){
        pekerjaanService.delete(user, id);

        return WebResponse.<String>builder()
                .data("kredensial pekerjaan telah terhapus")
                .build();
    }
}
