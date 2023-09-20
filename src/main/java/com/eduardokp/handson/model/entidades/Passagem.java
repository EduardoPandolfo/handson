package com.eduardokp.handson.model.entidades;

import com.eduardokp.handson.model.entidades.voo.Voo;
import com.eduardokp.handson.model.enums.Classe;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "passagem")
public class Passagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passagem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voo_id", nullable = false)
    private Voo voo;

    @Column(name = "datahoracompra", nullable = false)
    private LocalDateTime dataHoraCompra;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "classe", nullable = false)
    private Classe classe;

    @Column(name = "poltrona", nullable = false)
    private String poltrona;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passageiro_id", nullable = false)
    private Passageiro passageiro;

    @Column(name = "valortotal", precision = 5, scale = 2, nullable = false)
    private BigDecimal valorTotal;

}

