package esa.askerestful.repository;

import esa.askerestful.entity.Gambar;
import esa.askerestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface GambarRepository extends JpaRepository<Gambar ,String> {

    @Query("SELECT g FROM Gambar g WHERE g.namaGambar = :fileName ")
    Optional<Gambar> findByName(String fileName);

    @Query("SELECT CASE WHEN COUNT(g) > 0 THEN true ELSE false END FROM Gambar g WHERE g.path = :path")
    boolean existByPath(String path);
}
