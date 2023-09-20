package com.eduardokp.handson.model.servicos;

import com.eduardokp.handson.model.entidades.voo.Voo;
import com.eduardokp.handson.model.entidades.voo.VooClasse;
import com.eduardokp.handson.model.enums.Situacao;
import com.eduardokp.handson.model.repositorios.voo.VooClasseRepository;
import com.eduardokp.handson.model.repositorios.voo.VooRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class VooService {

    @Autowired
    private VooRepository vooRepository;

    @Autowired
    private VooClasseRepository vooClasseRepository;


    @Transactional
    public Voo persistir(Voo voo) {
        voo = vooRepository.save(voo);
        saveClasses(voo, voo.getClasses());
        return voo;
    }

    private void saveClasses(Voo voo, List<VooClasse> classes) {
        for (VooClasse vooClasse : ObjectUtils.defaultIfNull(classes, new ArrayList<VooClasse>())) {
            vooClasse.setVoo(voo);
            vooClasseRepository.save(vooClasse);
        }
    }

    public List<Voo> getAll() {
        return vooRepository.findAll();
    }

    public Voo getById(Long id) {
        return vooRepository.findById(id).orElse(null);
    }

    public void cancelarById(Long id) {
        Voo voo = getById(id);
        voo.setSituacao(Situacao.CANCELADO);
        vooRepository.save(voo);
    }
}
