package esa.askerestful.controller;

import esa.askerestful.entity.KredensialPendidikan;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKredPendidikanReq;
import esa.askerestful.model.KredPekerjaanResp;
import esa.askerestful.model.KredPendidikanResp;
import esa.askerestful.model.WebResponse;
import esa.askerestful.service.LokasiService;
import esa.askerestful.service.PendidikanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
public class PendidikanController {

    @Autowired
    public PendidikanService pendidikanService;

    @PostMapping(
            path = "/api/pendidikan",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<KredPendidikanResp> create(User user , @RequestBody CreateKredPendidikanReq req){
        KredPendidikanResp kredPendidikanResp = pendidikanService.create(user, req);

        return WebResponse.<KredPendidikanResp>builder()
                .data(kredPendidikanResp)
                .build();
    }

    @GetMapping(
            path = "/api/pendidikan/{idKredPendidikan}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<KredPendidikanResp> get(User user, @PathVariable("idKredPendidikan") String id){
        KredPendidikanResp resp = pendidikanService.get(user, id);

        return WebResponse.<KredPendidikanResp>builder()
                .data(resp)
                .build();
    }

    @PutMapping(
            path = "/api/pendidikan/{idKredPendidikan}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<KredPendidikanResp> update(User user, @RequestBody CreateKredPendidikanReq req
                                                  ,@PathVariable("idKredPendidikan") String idKredPendidikan){
        KredPendidikanResp resp = pendidikanService.update(user, req, idKredPendidikan);

        return WebResponse.<KredPendidikanResp>builder()
                .data(resp)
                .build();
    }

    @DeleteMapping(
            path = "/api/pendidikan/{idKredPendidikan}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user, @PathVariable("idKredPendidikan") String idKredPendidikan){
        pendidikanService.delete(user, idKredPendidikan);

        return WebResponse.<String>builder()
                .data("data telah terhapus")
                .build();
    }
}
