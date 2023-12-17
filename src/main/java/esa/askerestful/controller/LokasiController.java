package esa.askerestful.controller;

import esa.askerestful.entity.KredensialLokasi;
import esa.askerestful.entity.User;
import esa.askerestful.model.CreateKredLokasiReq;
import esa.askerestful.model.KredLokasiResp;
import esa.askerestful.model.WebResponse;
import esa.askerestful.service.LokasiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

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
    public WebResponse<KredLokasiResp> create(User user, @RequestBody CreateKredLokasiReq req){
        KredLokasiResp kredensialLokasi = lokasiService.create(user , req);

        return WebResponse.<KredLokasiResp>builder()
                .data(kredensialLokasi)
                .build();
    }

    @GetMapping(
            path = "/api/lokasi/{idKredLokasi}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<KredLokasiResp> get(User user, @PathVariable("idKredLokasi") String id){
        KredLokasiResp kredLokasiResp = lokasiService.get(user, id);

        return WebResponse.<KredLokasiResp>builder()
                .data(kredLokasiResp)
                .build();
    }

    @PutMapping(
            path = "/api/lokasi/{idKredLokasi}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<KredLokasiResp> update(User user, @RequestBody CreateKredLokasiReq req,
                                              @PathVariable("idKredLokasi") String idKredLokasi){
        KredLokasiResp kredLokasiResp = lokasiService.update(user, req, idKredLokasi);

        return WebResponse.<KredLokasiResp>builder()
                .data(kredLokasiResp)
                .build();
    }

    @DeleteMapping(
            path = "/api/lokasi/{idKredLokasi}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user, @PathVariable("idKredLokasi") String idKredLokasi ){
        lokasiService.delete(user, idKredLokasi);

        return WebResponse.<String>builder()
                .data("kredensial lokasi telah terhapus")
                .build();
    }
}
