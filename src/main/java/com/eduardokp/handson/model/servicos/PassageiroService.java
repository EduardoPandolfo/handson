package com.eduardokp.handson.model.servicos;

import com.eduardokp.handson.exceptions.AeroportoIataDuplicadoException;
import com.eduardokp.handson.exceptions.PassageiroCPFDuplicadoException;
import com.eduardokp.handson.model.entidades.Aeroporto;
import com.eduardokp.handson.model.entidades.Passageiro;
import com.eduardokp.handson.model.enums.Situacao;
import com.eduardokp.handson.model.repositorios.PassageiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PassageiroService {

    @Autowired
    private PassageiroRepository repository;

    @Transactional
    public Passageiro persistir(Passageiro passageiro) {
        Passageiro duplicado = repository.getPassageiroByCpf(passageiro.getCpf());

        if(duplicado != null && !duplicado.getId().equals(passageiro.getId())) {
            throw new PassageiroCPFDuplicadoException();
        }

        return repository.save(passageiro);
    }

    public List<Passageiro> getAll() {
        return repository.findAll();
    }

    public Passageiro getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void inativarById(Long id) {
        Passageiro passageiro = getById(id);

        if(passageiro != null) {
            passageiro.setSituacao(Situacao.INATIVO);
            repository.save(passageiro);
        }
    }
}
