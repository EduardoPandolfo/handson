package com.eduardokp.handson.entidades;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "aeroporto")
public class Aeroporto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aeroporto_id")
    private Long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "iata", nullable = false, unique = true)
    private String iata;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cidade_id", nullable = false)
    private Cidade cidade;
}
