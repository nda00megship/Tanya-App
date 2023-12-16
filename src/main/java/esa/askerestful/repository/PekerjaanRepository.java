package esa.askerestful.repository;

import esa.askerestful.entity.KredensialPekerjaan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PekerjaanRepository extends JpaRepository<KredensialPekerjaan, String> {
}
