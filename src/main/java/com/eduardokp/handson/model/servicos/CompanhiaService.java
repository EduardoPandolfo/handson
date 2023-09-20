package com.eduardokp.handson.model.servicos;

import com.eduardokp.handson.model.entidades.Cidade;
import com.eduardokp.handson.model.entidades.Companhia;
import com.eduardokp.handson.model.repositorios.CidadeRepository;
import com.eduardokp.handson.model.repositorios.CompanhiaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanhiaService {

    @Autowired
    private CompanhiaRepository repository;

    /**
     * getAll cidades sorted by nome
     */
    public List<Companhia> getAll() {
        return repository.findAll().stream().sorted(Comparator.comparing(Companhia::getNome)).collect(Collectors.toList());
    }
}
