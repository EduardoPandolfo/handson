package com.eduardokp.handson.model.servicos;

import com.eduardokp.handson.model.entidades.Veiculo;
import com.eduardokp.handson.model.repositorios.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository repository;

   public List<Veiculo> getByCompanhiaId(Long companhiaId) {
       return repository.findVeiculosByCompanhiaId(companhiaId);
   }
}
