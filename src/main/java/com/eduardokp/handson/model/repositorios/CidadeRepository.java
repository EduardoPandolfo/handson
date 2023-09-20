package com.eduardokp.handson.model.repositorios;

import com.eduardokp.handson.model.entidades.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {

}
