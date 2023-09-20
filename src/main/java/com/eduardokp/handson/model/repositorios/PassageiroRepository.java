package com.eduardokp.handson.model.repositorios;

import com.eduardokp.handson.model.entidades.Passageiro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassageiroRepository extends JpaRepository<Passageiro, Long> {

    Passageiro getPassageiroByCpf(String cpf);

}
