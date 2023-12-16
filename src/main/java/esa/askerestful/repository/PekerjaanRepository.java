package esa.askerestful.repository;

import esa.askerestful.entity.KredensialPekerjaan;
import esa.askerestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PekerjaanRepository extends JpaRepository<KredensialPekerjaan, String> {

    @Query("SELECT kp FROM KredensialPekerjaan kp WHERE kp.user = :user AND kp.idKredensialPekerjaan = :id")
    Optional<KredensialPekerjaan> findFirstByUserAndId(User user, String id);
}
