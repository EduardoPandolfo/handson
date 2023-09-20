package com.eduardokp.handson.entidades;

import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "companhia")
public class Companhia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "companhia_id")
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;
}
