package esa.askerestful.repository;

import esa.askerestful.entity.Komentar;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface KomentarRepository extends JpaRepository<Komentar ,String> {

    @Query("SELECT k FROM Komentar k WHERE k.user = :user AND k.idKomentar = :idKomentar")
    Optional<Komentar> findFirstByUserAndId(User user , String idKomentar);
}
