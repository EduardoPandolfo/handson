package com.eduardokp.handson.model.repositorios;

import com.eduardokp.handson.model.entidades.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    @Query("SELECT v FROM Veiculo v WHERE v.companhia.id = :companhiaId")
    List<Veiculo> findVeiculosByCompanhiaId(@Param("companhiaId") Long companhiaId);
}
