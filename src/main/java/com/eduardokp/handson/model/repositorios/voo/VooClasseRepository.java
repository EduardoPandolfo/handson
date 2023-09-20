package com.eduardokp.handson.model.repositorios.voo;

import com.eduardokp.handson.model.entidades.voo.VooClasse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VooClasseRepository extends JpaRepository<VooClasse, Long> {
}
