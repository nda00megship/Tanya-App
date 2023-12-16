package esa.askerestful.repository;

import esa.askerestful.entity.KredensialPendidikan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PendidikanRepository extends JpaRepository<KredensialPendidikan, String> {

}
