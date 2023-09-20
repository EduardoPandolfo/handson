package com.eduardokp.handson.model.servicos;

import com.eduardokp.handson.exceptions.AeroportoIataDuplicadoException;
import com.eduardokp.handson.model.entidades.Aeroporto;
import com.eduardokp.handson.model.repositorios.AeroportoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AeroportoService {

    @Autowired
    private AeroportoRepository repository;


    @Transactional
    public Aeroporto persistir(Aeroporto aeroporto) {
        Aeroporto duplicado = repository.findUserByIata(aeroporto.getIata());

        if(duplicado != null && !duplicado.getId().equals(aeroporto.getId())) {
            throw new AeroportoIataDuplicadoException(duplicado.getId());
        }

        return repository.save(aeroporto);
    }

    public List<Aeroporto> getAll() {
        return repository.findAll();
    }

    public Aeroporto getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
