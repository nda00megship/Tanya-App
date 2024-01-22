package esa.askerestful.data;

import esa.askerestful.entity.Komentar;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.repository.KomentarRepository;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import esa.askerestful.service.MicroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class KomentarSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MicroService microService;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    public void seedDataWithComments() {
        List<Pertanyaan> pertanyaanList = pertanyaanRepository.findAll();


            for (int i = 0; i < 15; i++) {
                User user = userRepository.findById("user_" + i).orElseThrow();
                Pertanyaan pertanyaan = pertanyaanRepository.findById("pertanyaan_"+i).orElseThrow();

                Komentar komentar = new Komentar();
                komentar.setIdKomentar("komen_" + i);
                komentar.setTanggal(new Timestamp(System.currentTimeMillis()));
                komentar.setUser(user);
                komentar.setPertanyaan(pertanyaan);
                komentar.setDeskripsi("Ini adalah komentar #" + i + " untuk pertanyaan ini.");

                komentarRepository.save(komentar);

                for (int j = 1; j < 4; j++){

                    komentar.setIdKomentar("komen_" + i+j);
                    komentar.setTanggal(new Timestamp(System.currentTimeMillis()));
                    komentar.setUser(user);
                    komentar.setPertanyaan(pertanyaan);
                    komentar.setDeskripsi("Ini adalah komentar #" + i + " untuk pertanyaan ini.");
                    komentarRepository.save(komentar);
                }
            }

        }
    }

