package esa.askerestful.config;

import esa.askerestful.data.GambarSeeder;
import esa.askerestful.data.KomentarSeeder;
import esa.askerestful.data.PertanyaanSeeder;
import esa.askerestful.data.UserSeeder;
import esa.askerestful.repository.*;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataSeederConfig{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private KomentarRepository komentarRepository;

    @Autowired
    private PertanyaanRepository pertanyaanRepository;

    @Autowired
    private GambarRepository gambarRepository;

    @Autowired
    private PekerjaanRepository pekerjaanRepository;

    @Autowired
    private LokasiRepository lokasiRepository;

    @Autowired
    private PendidikanRepository pendidikanRepository;

    @Autowired
    private UserSeeder userSeeder;

    @Autowired
    private KomentarSeeder komentarSeeder;

    @Autowired
    private PertanyaanSeeder pertanyaanSeeder;

    @Autowired
    private GambarSeeder gambarSeeder;

    @PostConstruct
    public void seedData() {
        lokasiRepository.deleteAll();
        pekerjaanRepository.deleteAll();
        pendidikanRepository.deleteAll();
        komentarRepository.deleteAll();
        gambarRepository.deleteAll();
        pertanyaanRepository.deleteAll();
        userRepository.deleteAll();

        userSeeder.seedDataUser();
        pertanyaanSeeder.seedPertanyaan();
        komentarSeeder.seedDataWithComments();
        gambarSeeder.seedDataGambar();
    }
}