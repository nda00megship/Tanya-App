package esa.askerestful.config;

import esa.askerestful.data.KomentarSeeder;
import esa.askerestful.data.PertanyaanSeeder;
import esa.askerestful.data.UserSeeder;
import esa.askerestful.repository.GambarRepository;
import esa.askerestful.repository.KomentarRepository;
import esa.askerestful.repository.PertanyaanRepository;
import esa.askerestful.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeederConfig {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    @Autowired
    private GambarRepository gambarRepository;

    @Autowired
    private UserSeeder userSeeder;

    @Autowired
    private KomentarSeeder komentarSeeder;

    @Autowired
    private PertanyaanSeeder pertanyaanSeeder;

    @PostConstruct
    public void seedData() {
        pertanyaanRepository.deleteAll();
        komentarRepository.deleteAll();
        gambarRepository.deleteAll();
        userRepository.deleteAll();

        userSeeder.seedDataUser();
        pertanyaanSeeder.seedPertanyaan();
        komentarSeeder.seedDataWithComments();
    }
}