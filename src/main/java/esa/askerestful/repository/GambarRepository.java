package esa.askerestful.repository;

import esa.askerestful.entity.Gambar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GambarRepository extends JpaRepository<Gambar ,String> {

    @Query("SELECT g FROM Gambar g WHERE g.namaGambar = :fileName ")
    Optional<Gambar> findByUsername(String fileName);
}
