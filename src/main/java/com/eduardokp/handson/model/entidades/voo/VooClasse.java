package com.eduardokp.handson.model.entidades.voo;

import com.eduardokp.handson.model.enums.Classe;
import lombok.*;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "vooclasse")
public class VooClasse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vooclasse_id")
    private Long id;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "classe", nullable = false)
    private Classe classe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "voo_id", nullable = false)
    private Voo voo;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade;
}
