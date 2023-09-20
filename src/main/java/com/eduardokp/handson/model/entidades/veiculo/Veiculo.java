package com.eduardokp.handson.model.entidades.veiculo;

import com.eduardokp.handson.model.entidades.Companhia;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "veiculo")
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "veiculo_id")
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String name;

    @Column(name = "modelo", length = 30, nullable = false)
    private String modelo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "companhia_id", nullable = false)
    private Companhia companhia;

    @OneToMany(mappedBy = "veiculo", fetch = FetchType.LAZY)
    private List<VeiculoClasse> classes;
}