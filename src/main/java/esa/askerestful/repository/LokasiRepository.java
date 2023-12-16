package esa.askerestful.repository;

import esa.askerestful.entity.KredensialLokasi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LokasiRepository extends JpaRepository<KredensialLokasi , String> {


}
