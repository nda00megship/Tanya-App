package esa.askerestful.repository;

import esa.askerestful.entity.Pertanyaan;
import esa.askerestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PertanyaanRepository extends JpaRepository<Pertanyaan , String> , JpaSpecificationExecutor<Pertanyaan> {

    @Query("SELECT p FROM Pertanyaan p WHERE p.user = :user AND p.idPertanyaan = :idPertanyaan")
    Optional<Pertanyaan> findFirstByUserAndId(User user , String idPertanyaan);

//    @Query("SELECT kp FROM KredensialPendidikan kp JOIN FETCH kp.user u WHERE u.username = :username")
    @Query("SELECT p FROM Pertanyaan p WHERE p.idPertanyaan = :idPertanyaan")
    Optional<Pertanyaan> findPertanyaanById(String idPertanyaan);

    @Query("SELECT p FROM Pertanyaan p JOIN FETCH p.user u WHERE u.username = :username")
    List<Pertanyaan> getPertanyaanByUsername(@Param("username") String username);
}
