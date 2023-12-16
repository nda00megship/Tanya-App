package esa.askerestful.repository;

import esa.askerestful.entity.KredensialLokasi;
import esa.askerestful.entity.KredensialPendidikan;
import esa.askerestful.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LokasiRepository extends JpaRepository<KredensialLokasi , String> {

    @Query("SELECT kl FROM KredensialLokasi kl WHERE kl.user = :user AND kl.idKredensialLokasi = :id")
    Optional<KredensialLokasi> findFirstByUserAndId(User user, String id);
}
