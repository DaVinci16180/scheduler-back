package com.br.scheduler.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.br.scheduler.model.Process;

public interface ProcessRepository extends JpaRepository<Process, Long> {

}