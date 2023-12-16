package esa.askerestful.repository;

import esa.askerestful.entity.KredensialPekerjaan;
import esa.askerestful.entity.KredensialPendidikan;
import esa.askerestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PendidikanRepository extends JpaRepository<KredensialPendidikan, String> {

    @Query("SELECT kp FROM KredensialPendidikan kp WHERE kp.user = :user AND kp.idKredensialPendidikan = :id")
    Optional<KredensialPendidikan> findFirstByUserAndId(User user, String id);
}
