package esa.askerestful.data;

import esa.askerestful.entity.Komentar;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.repository.KomentarRepository;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class KomentarSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    public void seedDataWithComments() {
        List<Pertanyaan> pertanyaanList = pertanyaanRepository.findAll();

        Random random = new Random();

        for (Pertanyaan pertanyaan : pertanyaanList) {
            int numberOfComments = random.nextInt(4) + 2;

            for (int i = 0; i < numberOfComments; i++) {
                User user = userRepository.findByUsername("esa" + random.nextInt(3)).orElseThrow();
                Komentar komentar = new Komentar();
                komentar.setIdKomentar("komen_" + i);
                komentar.setUser(user);
                komentar.setPertanyaan(pertanyaan);
                komentar.setDeskripsi("Ini adalah komentar #" + i + " untuk pertanyaan ini.");

                komentarRepository.save(komentar);
            }
        }
        System.out.println("Komentar berhasil diinisialisasi.");
    }
}
