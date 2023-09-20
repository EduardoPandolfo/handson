package com.eduardokp.handson.model.entidades.voo;

import com.eduardokp.handson.model.entidades.Passageiro;
import com.eduardokp.handson.model.entidades.Passagem;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "voobagagem")
public class VooBagagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voobagagem_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passagem_id", nullable = false)
    private Passagem passagem;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voo_id", nullable = false)
    private Voo voo;

    @Column(name = "peso", precision = 4, scale = 2, nullable = false)
    private BigDecimal peso;
}

