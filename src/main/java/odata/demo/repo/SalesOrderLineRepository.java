package odata.demo.repo;

import odata.demo.entity.SalesOrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderLineRepository extends JpaRepository<SalesOrderLine, Long> {}

