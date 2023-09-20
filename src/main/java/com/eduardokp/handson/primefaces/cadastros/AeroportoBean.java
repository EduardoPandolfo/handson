package com.eduardokp.handson.primefaces.cadastros;

import com.eduardokp.handson.model.entidades.Aeroporto;
import com.eduardokp.handson.model.entidades.Cidade;
import com.eduardokp.handson.model.servicos.AeroportoService;
import com.eduardokp.handson.model.servicos.CidadeService;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@SessionScoped
public class AeroportoBean implements Serializable {

    private static final long serialVersionUID = 2L;
    private final Logger logger = LoggerFactory.getLogger(AeroportoBean.class);

    @Autowired
    private AeroportoService aeroportoService;

    @Autowired
    private CidadeService cidadeService;

    private final List<Aeroporto> aeroportos = new ArrayList<>();
    private final List<Cidade> cidades = new ArrayList<>();
    private Aeroporto aeroporto;
    private boolean novo;
    private Long cidadeSelecionadaId;

    @PostConstruct
    public void init() {
        pesquisarAeroportos();
        pesquisarCidades();
    }

    public void pesquisarAeroportos() {
        aeroportos.clear();
        aeroportos.addAll(aeroportoService.getAll());
    }

    private void pesquisarCidades() {
        cidades.clear();
        cidades.addAll(cidadeService.getAll());
    }

    public void novo() {
        novo = true;
        aeroporto = new Aeroporto();
        cidadeSelecionadaId = null;
        pesquisarCidades();
    }

    public void editar(Long id) {

        novo = false;
        aeroporto = aeroportoService.getById(id);
        cidadeSelecionadaId = aeroporto.getCidade().getId();

        if (aeroporto == null) {
            aeroporto = new Aeroporto();
            logger.error("Não encontrado aeroporto com código: " + id);
        }

        pesquisarCidades();
    }

    public void excluir() {
        try {
            aeroportoService.deleteById(aeroporto.getId());
            PrimeFaces.current().executeScript("PF('excluirAeroporto').hide();");
        } catch (Exception e) {
            logger.error(e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(context.getExternalContext().getSessionId(false), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        } finally {
            pesquisarAeroportos();
        }


    }

    public void salvar() {
        try {
            aeroportoService.persistir(aeroporto);
            PrimeFaces.current().executeScript("PF('editarDialog').hide();");
        } catch (Exception e) {
            logger.error(e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(context.getExternalContext().getSessionId(false), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        } finally {
            pesquisarAeroportos();
        }
    }


    public List<Aeroporto> getAeroportos() {
        return aeroportos;
    }

    public Aeroporto getAeroporto() {
        return aeroporto;
    }

    public boolean isNovo() {
        return novo;
    }

    public List<Cidade> getCidades() {
        return cidades;
    }

    public Long getCidadeSelecionadaId() {
        return cidadeSelecionadaId;
    }

    public void setCidadeSelecionadaId(Long cidadeSelecionadaId) {
        this.cidadeSelecionadaId = cidadeSelecionadaId;
        if(cidadeSelecionadaId != null) {
            aeroporto.setCidade(new Cidade(cidadeSelecionadaId));
        } else {
            aeroporto.setCidade(null);
        }
    }

}

