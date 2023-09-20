package com.eduardokp.handson.model.entidades;

import com.eduardokp.handson.model.enums.UF;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cidade_id")
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "uf", nullable = false)
    private UF uf;

    public Cidade(Long id) {
        this.id = id;
    }
}
