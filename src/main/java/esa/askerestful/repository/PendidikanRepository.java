package esa.askerestful.repository;

import esa.askerestful.entity.KredensialPekerjaan;
import esa.askerestful.entity.KredensialPendidikan;
import esa.askerestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PendidikanRepository extends JpaRepository<KredensialPendidikan, String> {

    @Query("SELECT kp FROM KredensialPendidikan kp WHERE kp.user = :user AND kp.idKredensialPendidikan = :id")
    Optional<KredensialPendidikan> findFirstByUserAndId(User user, String id);

    @Query("SELECT kp FROM KredensialPendidikan kp WHERE kp.user = :user")
    Optional<KredensialPendidikan> findByUser(User user);

    @Query("SELECT kp FROM KredensialPendidikan kp JOIN FETCH kp.user u WHERE u.username = :username")
    Optional<KredensialPendidikan> findByUsername(@Param("username") String username);
}
