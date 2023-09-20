package com.eduardokp.handson.model.entidades;

import com.eduardokp.handson.model.enums.Situacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "passageiro")
public class Passageiro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "passageiro_id")
    private Long id;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    @Column(name = "datanascimento", nullable = false)
    private LocalDate dataNascimento;

    @Email(message = "Email inválido")
    @Column(name = "email", nullable = false)
    private String email;

    @CPF(message = "CPF inválido")
    @Column(name = "cpf", nullable = false, unique = true)
    private String cpf;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "situacao", nullable = false)
    private Situacao situacao = Situacao.ATIVO;

}
