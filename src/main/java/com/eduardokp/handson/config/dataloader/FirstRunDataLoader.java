package com.eduardokp.handson.config.dataloader;

import com.eduardokp.handson.model.repositorios.CidadeRepository;
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
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(ApplicationArguments args) {
        if (cidadeRepository.count() == 0) {
            jdbcTemplate.update(getCidadeSQL());
        }
    }

    /**
     * cadastro inicial para testes utilizando todas as capitais do brasil
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
}
