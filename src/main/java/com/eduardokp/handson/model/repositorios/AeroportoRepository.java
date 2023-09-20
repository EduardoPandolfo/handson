package com.eduardokp.handson.model.repositorios;

import com.eduardokp.handson.model.entidades.Aeroporto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AeroportoRepository extends JpaRepository<Aeroporto, Long> {

    @Query("select a from Aeroporto a where a.iata = :iata")
    Aeroporto findUserByIata(@Param("iata") String iata);
}
