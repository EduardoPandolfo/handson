package com.eduardokp.handson.model.entidades.voo;

import com.eduardokp.handson.model.entidades.Aeroporto;
import com.eduardokp.handson.model.entidades.Companhia;
import com.eduardokp.handson.model.entidades.Veiculo;
import com.eduardokp.handson.model.enums.Classe;
import com.eduardokp.handson.model.enums.Situacao;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "voo")
public class Voo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companhia_id", nullable = false)
    private Companhia companhia;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "partida_id", referencedColumnName = "aeroporto_id", nullable = false)
    private Aeroporto partida;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "destino_id", referencedColumnName = "aeroporto_id", nullable = false)
    private Aeroporto destino;

    @Column(name = "saida", nullable = false)
    private LocalDateTime saida;

    @Column(name = "previsaochegada")
    private LocalDateTime previsaoChegada;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private Situacao situacao = Situacao.ATIVO;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "veiculo_id", nullable = false)
    private Veiculo veiculo;

    @OneToMany(mappedBy = "voo", fetch = FetchType.LAZY)
    private List<VooClasse> classes;
}
