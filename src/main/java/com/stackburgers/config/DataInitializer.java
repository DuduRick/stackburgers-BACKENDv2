package com.stackburgers.config;

import com.stackburgers.model.*;
import com.stackburgers.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Popula o banco com dados de exemplo ao iniciar a aplicação.
 * Útil para testes e demonstrações.
 * CommandLineRunner executa após o Spring Boot inicializar.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired private HamburgerRepository hamburgerRepository;
    @Autowired private BebidaRepository bebidaRepository;
    @Autowired private ClienteRepository clienteRepository;

    @Override
    public void run(String... args) throws Exception {
        if (hamburgerRepository.count() == 0) carregarHamburgeres();
        if (bebidaRepository.count() == 0)    carregarBebidas();
        if (clienteRepository.count() == 0)   carregarClienteDemo();
        System.out.println(">>> Dados de exemplo carregados com sucesso!");
    }

    private void carregarHamburgeres() {
        Hamburger h1 = new Hamburger();
        h1.setNome("Stack Classic");
        h1.setDescricao("O clássico da casa com blend de Angus");
        h1.setPreco(28.90);
        h1.setTipoCarne("Angus");
        h1.setIngredientes("Pão brioche, blend 180g, queijo cheddar, alface, tomate, maionese artesanal");
        h1.setCalorias(650);
        h1.setVegetariano(false);
        hamburgerRepository.save(h1);

        Hamburger h2 = new Hamburger();
        h2.setNome("Stack Veggie");
        h2.setDescricao("Opção vegetariana com blend de grão-de-bico");
        h2.setPreco(26.90);
        h2.setTipoCarne("Vegano");
        h2.setIngredientes("Pão integral, blend vegano, queijo vegano, rúcula, tomate seco, homus");
        h2.setCalorias(480);
        h2.setVegetariano(true);
        hamburgerRepository.save(h2);

        Hamburger h3 = new Hamburger();
        h3.setNome("Stack Frango Crocante");
        h3.setDescricao("Peito de frango empanado crocante com molho especial");
        h3.setPreco(24.90);
        h3.setTipoCarne("Frango");
        h3.setIngredientes("Pão australiano, frango empanado, queijo prato, coleslaw, molho honey mustard");
        h3.setCalorias(590);
        h3.setVegetariano(false);
        hamburgerRepository.save(h3);

        Hamburger h4 = new Hamburger();
        h4.setNome("Stack Double Bacon");
        h4.setDescricao("Duplo blend com bacon caramelizado");
        h4.setPreco(38.90);
        h4.setTipoCarne("Angus");
        h4.setIngredientes("Pão brioche, 2x blend 180g, queijo gouda, bacon caramelizado, cebola roxa, BBQ");
        h4.setCalorias(920);
        h4.setVegetariano(false);
        hamburgerRepository.save(h4);
    }

    private void carregarBebidas() {
        Bebida b1 = new Bebida();
        b1.setNome("Coca-Cola");
        b1.setDescricao("Refrigerante gelado");
        b1.setPreco(7.90);
        b1.setTipo("Refrigerante");
        b1.setVolumeMl(350);
        b1.setAlcolica(false);
        bebidaRepository.save(b1);

        Bebida b2 = new Bebida();
        b2.setNome("Suco de Laranja");
        b2.setDescricao("Suco natural espremido na hora");
        b2.setPreco(9.90);
        b2.setTipo("Suco");
        b2.setVolumeMl(400);
        b2.setAlcolica(false);
        bebidaRepository.save(b2);

        Bebida b3 = new Bebida();
        b3.setNome("Cerveja Artesanal IPA");
        b3.setDescricao("Cerveja artesanal local gelada");
        b3.setPreco(16.90);
        b3.setTipo("Cerveja");
        b3.setVolumeMl(500);
        b3.setAlcolica(true);
        bebidaRepository.save(b3);

        Bebida b4 = new Bebida();
        b4.setNome("Água Mineral");
        b4.setDescricao("Água mineral sem gás");
        b4.setPreco(4.00);
        b4.setTipo("Água");
        b4.setVolumeMl(500);
        b4.setAlcolica(false);
        bebidaRepository.save(b4);
    }

    private void carregarClienteDemo() {
        Cliente cliente = new Cliente();
        cliente.setNome("Cliente Demo");
        cliente.setEmail("cliente@demo.com");
        cliente.setSenha("cliente123");
        cliente.setCpf("000.000.000-00");
        cliente.setTelefone("(11) 99999-9999");
        clienteRepository.save(cliente);
        System.out.println(">>> Cliente demo criado: cliente@demo.com / cliente123");
    }
}
