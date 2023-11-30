package esa.askerestful.data;

import esa.askerestful.entity.Gambar;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.repository.GambarRepository;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import esa.askerestful.service.MicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@Service
public class GambarSeeder {

    private final String path = "storage/";
    @Autowired
    private GambarRepository gambarRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MicroService microService;

    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    public void seedDataGambar(){
        Random random = new Random();

        for (int i = 0; i < 15; i++){
            User user = userRepository.findById("user_" + i).orElseThrow();
            Pertanyaan pertanyaan = pertanyaanRepository.findById("pertanyaan_"+i).orElseThrow();
            Gambar gambar = new Gambar();
            gambar.setIdGambar("id_gambar_"+i);
            gambar.setNamaGambar("gambar_" + i);
            gambar.setPath(path + "gambar_" + (i) + ".png");
            gambar.setTanggal(microService.currentTimestamp);
            gambar.setUser(user);
            gambar.setPertanyaan(pertanyaan);
            gambarRepository.save(gambar);

        }
        for (int i = 0; i < 10; i++){
            User user = userRepository.findById("user_" + i).orElseThrow();
            Pertanyaan pertanyaan = pertanyaanRepository.findById("pertanyaan_"+(i+10)).orElseThrow();
            Gambar gambar = new Gambar();
            gambar.setIdGambar("id_gambar_"+i +10);
            gambar.setNamaGambar("gambar_" + i + 10);
            gambar.setPath(path + "gambar_" + (i + 10) + ".png");
            gambar.setTanggal(microService.currentTimestamp);
            gambar.setUser(user);
            gambar.setPertanyaan(pertanyaan);
            gambarRepository.save(gambar);

        }
    }
}
