package odata.demo.repo;

import odata.demo.entity.LineAllocation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LineAllocationRepository extends JpaRepository<LineAllocation, Long> {}

