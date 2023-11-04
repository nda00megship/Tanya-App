package esa.askerestful.data;

import esa.askerestful.controller.PertanyaanController;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import esa.askerestful.repository.GambarRepository;
import esa.askerestful.repository.KomentarRepository;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PertanyaanSeeder {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    public void seedPertanyaan(){
        for (int i = 0 ; i < 15 ; i++){
            User user = userRepository.findByUsername("esa0").orElseThrow();
            Pertanyaan pertanyaan = new Pertanyaan();
            pertanyaan.setIdPertanyaan("pertanyaan_" + i);
            pertanyaan.setHeader("Apa yang Anda pikirkan tentang masa depan?");
            pertanyaan.setDeskripsi("Berikan pandangan Anda tentang apa yang akan terjadi dalam 10 tahun ke depan Apakah anda tidak khawatir dengan masa yang akan datang seperti itu mungkin aku saja yang hanya mencoba tidak khwatir. dalam kondisi yang baik ini saya ingin meneruskan hidup saya.");
            pertanyaan.setSuka(i);
            pertanyaan.setUser(user);
            pertanyaanRepository.save(pertanyaan);
        }
        for (int i = 15 ; i < 30 ; i++){
            User user = userRepository.findByUsername("esa1").orElseThrow();
            Pertanyaan pertanyaan = new Pertanyaan();
            pertanyaan.setIdPertanyaan("pertanyaan_" + i);
            pertanyaan.setHeader("Bagaimana Anda menangani tantangan dalam pekerjaan?");
            pertanyaan.setDeskripsi("Ceritakan pengalaman Anda dalam menghadapi situasi sulit dan bagaimana Anda mengatasinya.");
            pertanyaan.setSuka(i);
            pertanyaan.setUser(user);
            pertanyaanRepository.save(pertanyaan);
        }
        for (int i = 30 ; i < 45 ; i++){
            User user = userRepository.findByUsername("esa2").orElseThrow();
            Pertanyaan pertanyaan = new Pertanyaan();
            pertanyaan.setIdPertanyaan("pertanyaan_" + i);
            pertanyaan.setHeader("Apa kebijakan terpenting dalam hidup Anda?");
            pertanyaan.setDeskripsi("Bagikan nilai-nilai dan prinsip yang menjadi landasan dalam hidup Anda.");
            pertanyaan.setSuka(i);
            pertanyaan.setUser(user);
            pertanyaanRepository.save(pertanyaan);
        }
    }
}
