package com.eduardokp.handson.model.repositorios;

import com.eduardokp.handson.model.entidades.Companhia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanhiaRepository extends JpaRepository<Companhia, Long> {
}
