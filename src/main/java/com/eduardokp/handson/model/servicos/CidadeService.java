package com.eduardokp.handson.model.servicos;

import com.eduardokp.handson.model.entidades.Cidade;
import com.eduardokp.handson.model.repositorios.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository repository;


    /**
        getALl cidades sorted by nome
     */
    public List<Cidade> getAll() {
        return repository.findAll().stream().sorted(Comparator.comparing(Cidade::getNome)).collect(Collectors.toList());
    }
}
