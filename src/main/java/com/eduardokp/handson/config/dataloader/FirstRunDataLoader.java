package com.eduardokp.handson.config.dataloader;

import com.eduardokp.handson.model.entidades.Companhia;
import com.eduardokp.handson.model.entidades.Veiculo;
import com.eduardokp.handson.model.repositorios.CidadeRepository;
import com.eduardokp.handson.model.repositorios.CompanhiaRepository;
import com.eduardokp.handson.model.repositorios.PassageiroRepository;
import com.eduardokp.handson.model.repositorios.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class FirstRunDataLoader implements ApplicationRunner {

    @Autowired
    private CidadeRepository cidadeRepository;

    @Autowired
    private CompanhiaRepository companhiaRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private PassageiroRepository passageiroRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) {
        if (cidadeRepository.count() == 0) {
            jdbcTemplate.update(getCidadeSQL());
        }

        if (companhiaRepository.count() == 0) {
            jdbcTemplate.update(getCompanhiaSQL());
        }

        if (veiculoRepository.count() == 0) {
            for (Companhia companhia : companhiaRepository.findAll()) {
                Veiculo veiculo = Veiculo
                        .builder()
                        .modelo("Modelo único")
                        .companhia(companhia)
                        .nome("Aeronave 1")
                        .build();

                veiculoRepository.save(veiculo);
            }
        }

        if(passageiroRepository.count() == 0) {
            jdbcTemplate.update(getPassageirosSQL());
        }
    }

    /**
     * cadastro inicial para testes utilizando todas as capitais do brasil
     *
     * @return
     */
    private String getCidadeSQL() {
        return "INSERT INTO cidade (nome, uf) VALUES " +
                "('Rio Branco', 'AC'), " +
                "('Maceió', 'AL')," +
                "('Macapá', 'AP'), " +
                "('Manaus', 'AM'), " +
                "('Salvador', 'BA'), " +
                "('Fortaleza', 'CE'), " +
                "('Brasília', 'DF'), " +
                "('Vitória', 'ES'), " +
                "('Goiânia', 'GO'), " +
                "('São Luís', 'MA'), " +
                "('Cuiabá', 'MT'), " +
                "('Campo Grande', 'MS'), " +
                "('Belo Horizonte', 'MG'), " +
                "('Belém', 'PA'), " +
                "('João Pessoa', 'PB'), " +
                "('Curitiba', 'PR'), " +
                "('Recife', 'PE'), " +
                "('Teresina', 'PI'), " +
                "('Rio de Janeiro', 'RJ'), " +
                "('Natal', 'RN'), " +
                "('Porto Alegre', 'RS'), " +
                "('Porto Velho', 'RO'), " +
                "('Boa Vista', 'RR'), " +
                "('Florianópolis', 'SC'), " +
                "('São Paulo', 'SP'), " +
                "('Aracaju', 'SE'), " +
                "('Palmas', 'TO');";
    }

    private String getCompanhiaSQL() {
        return "INSERT INTO companhia (nome)\n" +
                "VALUES\n" +
                "    ('Companhia Aérea 1'),\n" +
                "    ('Companhia Aérea 2'),\n" +
                "    ('Companhia Aérea 3'),\n" +
                "    ('Companhia Aérea 4'),\n" +
                "    ('Companhia Aérea 5');";
    }

    private String getPassageirosSQL() {
        return
                "INSERT INTO passageiro (nome, datanascimento, email, cpf, situacao)\n" +
                        "VALUES\n" +
                        "    ('Nome 1', '1990-01-01', 'teste@teste.com', '12345678901', 'ATIVO'),\n" +
                        "    ('Nome 2', '1985-05-15', 'teste@teste.com', '98765432109', 'ATIVO'),\n" +
                        "    ('Nome 3', '1982-12-10', 'teste@teste.com', '45678912301', 'ATIVO'),\n" +
                        "    ('Nome 4', '1998-08-20', 'teste@teste.com', '78901234567', 'ATIVO'),\n" +
                        "    ('Nome 5', '1995-04-25', 'teste@teste.com', '23456789012', 'ATIVO');";
    }
}
