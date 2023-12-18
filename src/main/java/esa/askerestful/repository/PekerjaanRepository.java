package esa.askerestful.repository;

import esa.askerestful.entity.KredensialPekerjaan;
import esa.askerestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PekerjaanRepository extends JpaRepository<KredensialPekerjaan, String> {

    @Query("SELECT kp FROM KredensialPekerjaan kp WHERE kp.user = :user AND kp.idKredensialPekerjaan = :id")
    Optional<KredensialPekerjaan> findFirstByUserAndId(User user, String id);

    @Query("SELECT kp FROM KredensialPekerjaan kp WHERE kp.user = :user")
    Optional<KredensialPekerjaan> findByUser(User user);

    @Query("SELECT kp FROM KredensialPekerjaan kp JOIN FETCH kp.user u WHERE u.username = :username")
    Optional<KredensialPekerjaan> findByUsername(@Param("username") String username);
}
