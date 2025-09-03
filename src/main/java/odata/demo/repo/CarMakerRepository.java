package odata.demo.repo;

import odata.demo.entity.CarMaker;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarMakerRepository extends JpaRepository<CarMaker, Long> {}
