package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.services;
import org.springframework.stereotype.Service;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.Users;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.repository.UsersRepository;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.CreateUserBodyDTO;

@Service
public class UsersService {
    private final UsersRepository usersRepository;

    public UsersService(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }
    public Users createUser(CreateUserBodyDTO dto) {
        
        // 1. Criamos a "caixa vazia" da Entidade que vai para o banco
        Users novoUsuario = new Users();
        
        // 2. Passamos os dados do DTO (Formulário) para a Entidade
        novoUsuario.setName(dto.getName());
        novoUsuario.setEmail(dto.getEmail());
        novoUsuario.setPassword(dto.getPassword());
        novoUsuario.setRole(dto.getRole());
        
        // OBS: Veja que não colocamos o "setId()". O banco fará isso sozinho!

        // 3. Mandamos o repositório salvar no banco e retornamos o resultado
        return usersRepository.save(novoUsuario);
    }
    
}
