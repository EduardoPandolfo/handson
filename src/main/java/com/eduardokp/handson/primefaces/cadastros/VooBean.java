package com.eduardokp.handson.primefaces.cadastros;

import com.eduardokp.handson.model.entidades.Aeroporto;
import com.eduardokp.handson.model.entidades.Cidade;
import com.eduardokp.handson.model.entidades.Companhia;
import com.eduardokp.handson.model.entidades.Veiculo;
import com.eduardokp.handson.model.entidades.voo.Voo;
import com.eduardokp.handson.model.servicos.*;
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
public class VooBean implements Serializable {

    private static final long serialVersionUID = 3L;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private VooService vooService;

    @Autowired
    private AeroportoService aeroportoService;

    @Autowired
    private CompanhiaService companhiaService;

    @Autowired
    private VeiculoService veiculoService;

    private final List<Voo> voos = new ArrayList<>();
    private final List<Aeroporto> aeroportos = new ArrayList<>();
    private final List<Companhia> companhias = new ArrayList<>();
    private final List<Veiculo> veiculos = new ArrayList<>();

    private Voo voo;
    private boolean novo;

    private Long aeroportoPartidaId;
    private Long aeroportoDestinoId;
    private Long companhiaId;
    private Long veiculoId;

    @PostConstruct
    public void init() {
        pesquisarVoos();
        pesquisarListasCadastro();
    }

    public void pesquisarVoos() {
        voos.clear();
        voos.addAll(vooService.getAll());
    }

    public void pesquisarAeroportos() {
        aeroportos.clear();
        aeroportos.addAll(aeroportoService.getAll());
    }

    public void pesquisarCompanhias() {
        companhias.clear();
        companhias.addAll(companhiaService.getAll());
    }

    public void pesquisarVeiculos() {
        veiculos.clear();
        if (companhiaId != null) {
            veiculos.addAll(veiculoService.getByCompanhiaId(companhiaId));
        }
    }

    public void pesquisarListasCadastro() {
        pesquisarAeroportos();
        pesquisarCompanhias();
        pesquisarVeiculos();
    }

    public void novo() {
        novo = true;
        voo = new Voo();
        aeroportoPartidaId = aeroportoDestinoId = companhiaId = veiculoId = null; // 👀
        pesquisarListasCadastro();
    }

    public void editar(Long id) {

        novo = false;
        voo = vooService.getById(id);

        if (voo == null) {
            voo = new Voo();
            logger.error("Não encontrado aeroporto com código: " + id);
        } else {
            aeroportoPartidaId = voo.getPartida().getId();
            aeroportoDestinoId = voo.getDestino().getId();
            companhiaId = voo.getCompanhia().getId();
            veiculoId = voo.getVeiculo().getId();
        }

        pesquisarListasCadastro();
    }

    public void cancelar() {
        try {
            vooService.cancelarById(voo.getId());
            PrimeFaces.current().executeScript("PF('cancelarVoo').hide();");
        } catch (Exception e) {
            logger.error(e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(context.getExternalContext().getSessionId(false), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        } finally {
            pesquisarVoos();
        }

    }

    public void salvar() {
        try {
            vooService.persistir(voo);
            PrimeFaces.current().executeScript("PF('editarDialog').hide();");
        } catch (Exception e) {
            logger.error(e.getMessage());
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(context.getExternalContext().getSessionId(false), new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
        } finally {
            pesquisarVoos();
        }
    }

    public List<Voo> getVoos() {
        return voos;
    }

    public List<Aeroporto> getAeroportos() {
        return aeroportos;
    }

    public List<Companhia> getCompanhias() {
        return companhias;
    }

    public List<Veiculo> getVeiculos() {
        return veiculos;
    }

    public Voo getVoo() {
        return voo;
    }

    public boolean isNovo() {
        return novo;
    }

    public Long getAeroportoPartidaId() {
        return aeroportoPartidaId;
    }

    public Long getAeroportoDestinoId() {
        return aeroportoDestinoId;
    }

    public Long getCompanhiaId() {
        return companhiaId;
    }

    public Long getVeiculoId() {
        return veiculoId;
    }

    public void setAeroportoPartidaId(Long aeroportoPartidaId) {
        this.aeroportoPartidaId = aeroportoPartidaId;
        if (aeroportoPartidaId != null) {
            voo.setPartida(new Aeroporto(aeroportoPartidaId));
        } else {
            voo.setPartida(new Aeroporto(null));
        }
    }

    public void setAeroportoDestinoId(Long aeroportoDestinoId) {
        this.aeroportoDestinoId = aeroportoDestinoId;
        if (aeroportoDestinoId != null) {
            voo.setDestino(new Aeroporto(aeroportoDestinoId));
        } else {
            voo.setDestino(new Aeroporto(null));
        }
    }

    public void setCompanhiaId(Long companhiaId) {
        this.companhiaId = companhiaId;
        if (companhiaId != null) {
            voo.setCompanhia(new Companhia(companhiaId));
        } else {
            voo.setCompanhia(new Companhia(null));
        }
        veiculoId = null;
        pesquisarVeiculos();
    }

    public void setVeiculoId(Long veiculoId) {
        this.veiculoId = veiculoId;
        if (veiculoId != null) {
            voo.setVeiculo(new Veiculo(veiculoId));
        } else {
            voo.setVeiculo(new Veiculo(null));
        }
    }

}

