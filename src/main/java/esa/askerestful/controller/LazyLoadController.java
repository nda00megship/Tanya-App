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
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


@RestController
public class LazyLoadController {
    @Autowired
    private LazyLoadingService lazyLoadingService;

    @GetMapping(
            path = "/api/beranda",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<PertanyaanGambarResponse>> getAllPertanyaanWithGambar(
            @RequestParam(defaultValue = "1" , required = false , value = "currentPage") int currentPage,
            @RequestParam(defaultValue = "10" , required = false, value = "itemPerPage") int itemsPerPage
    ) {
        List<PertanyaanGambarResponse> pertanyaanResponses = lazyLoadingService.getAllPertanyaanWithGambarAndKomentar(currentPage, itemsPerPage);
        if (pertanyaanResponses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(pertanyaanResponses, HttpStatus.OK);
    }

}
