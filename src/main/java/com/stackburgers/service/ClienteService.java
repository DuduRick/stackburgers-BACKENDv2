package com.stackburgers.service;

import com.stackburgers.dto.ClienteDTO;
import com.stackburgers.model.Cliente;
import com.stackburgers.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

/**
 * Service responsável pelo cadastro e gestão de clientes.
 */
@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> buscarPorEmail(String email) {
        return clienteRepository.findByEmail(email);
    }

    /**
     * Cadastra novo cliente com validação de e-mail e CPF duplicado
     */
    public Cliente cadastrar(ClienteDTO.ClienteRequest request) {
        // Verifica se o e-mail já está em uso
        if (clienteRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("E-mail já cadastrado: " + request.getEmail());
        }

        // Verifica CPF duplicado (se informado)
        if (request.getCpf() != null && !request.getCpf().isBlank()) {
            if (clienteRepository.existsByCpf(request.getCpf())) {
                throw new IllegalArgumentException("CPF já cadastrado.");
            }
        }

        Cliente cliente = new Cliente();
        cliente.setNome(request.getNome());
        cliente.setEmail(request.getEmail());
        // Em produção, a senha deveria ser criptografada (ex: BCrypt)
        cliente.setSenha(request.getSenha());
        cliente.setCpf(request.getCpf());
        cliente.setTelefone(request.getTelefone());
        cliente.setEndereco(request.getEndereco());

        return clienteRepository.save(cliente);
    }

    /**
     * Atualiza dados do cliente
     */
    public Optional<Cliente> atualizar(Long id, ClienteDTO.ClienteRequest request) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(request.getNome());
            cliente.setTelefone(request.getTelefone());
            cliente.setEndereco(request.getEndereco());
            // E-mail não é atualizado aqui por segurança
            return clienteRepository.save(cliente);
        });
    }

    /**
     * Converte Cliente para ClienteResponse (sem a senha)
     */
    public ClienteDTO.ClienteResponse toResponse(Cliente cliente) {
        return new ClienteDTO.ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getEndereco(),
                cliente.getAtivo()
        );
    }

    public boolean desativar(Long id) {
        return clienteRepository.findById(id).map(c -> {
            c.setAtivo(false);
            clienteRepository.save(c);
            return true;
        }).orElse(false);
    }
}
