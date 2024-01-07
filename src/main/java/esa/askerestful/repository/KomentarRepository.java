package esa.askerestful.repository;

import esa.askerestful.entity.Komentar;
import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface KomentarRepository extends JpaRepository<Komentar ,String> {

    @Query("SELECT k FROM Komentar k WHERE k.user = :user AND k.idKomentar = :idKomentar")
    Optional<Komentar> findFirstByUserAndId(User user , String idKomentar);

//    @Query("SELECT kp FROM KredensialPendidikan kp JOIN FETCH kp.user u WHERE u.username = :username")
    @Query("SELECT k FROM Komentar k JOIN FETCH k.user u WHERE u.username = :username")
    Optional<List<Komentar>> findKomentarByUsername(@Param("username") String username);

    @Query("SELECT k FROM Komentar k WHERE k.user = :user AND k.pertanyaan = :pertanyaan")
    Optional<Komentar> findFirstByUserAndPertanyaan(User user , Pertanyaan pertanyaan);
}
