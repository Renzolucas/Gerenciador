package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.services;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.Users;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.CreateUserBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.repositories.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public Users create(CreateUserBodyDTO dto) {
        
        // 1. Criamos a "caixa vazia" da Entidade que vai para o banco
        Users novoUsuario = new Users();
        
        // 2. Passamos os dados do DTO (Formulário) para a Entidade
        novoUsuario.setName(dto.name());
        novoUsuario.setEmail(dto.email());
        novoUsuario.setPassword(dto.password());
        novoUsuario.setRole(dto.role());
        
        Optional<Users> userExist = usersRepository.findByEmail(novoUsuario.getEmail());

        if (userExist.isPresent()) {
            throw new RuntimeException("Já existe usuário com email: " + novoUsuario.getEmail());
        }
        // OBS: Veja que não colocamos o "setId()". O banco fará isso sozinho!

        // 3. Mandamos o repositório salvar no banco e retornamos o resultado
        return usersRepository.save(novoUsuario);
    }
    
}
