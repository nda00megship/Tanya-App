package esa.askerestful.repository;

import esa.askerestful.entity.Komentar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KomentarRepository extends JpaRepository<Komentar ,String> {
}
