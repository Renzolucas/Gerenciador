package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.services;
import java.util.Optional;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.config.SecurityConfig;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.Users;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.CreateUserBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.repositories.UsersRepository;

@Service
public class UsersService {
    @Autowired
    private final UsersRepository usersRepository;
    private final SecurityConfig passwordCripto;
    public UsersService(UsersRepository usersRepository, SecurityConfig passwordCripto) {
        this.usersRepository = usersRepository;
        this.passwordCripto = passwordCripto;
    }
    public Users create(CreateUserBodyDTO dto) {
        
        // 1. Criamos a "caixa vazia" da Entidade que vai para o banco
        Users novoUsuario = new Users();
        
        // 2. Passamos os dados do DTO (Formulário) para a Entidade
        novoUsuario.setName(dto.name());
        novoUsuario.setEmail(dto.email());
        String senhaCriptografada = passwordCripto.passwordEncoder().encode(dto.password());
        novoUsuario.setPassword(senhaCriptografada);
        novoUsuario.setRole(dto.role());
        
        Optional<Users> userExist = usersRepository.findByEmail(novoUsuario.getEmail());

        if (userExist.isPresent()) {
            throw new RuntimeException("Já existe usuário com email: " + novoUsuario.getEmail());
        }
        // OBS: Veja que não colocamos o "setId()". O banco fará isso sozinho!

        // 3. Mandamos o repositório salvar no banco e retornamos o resultado
        return usersRepository.save(novoUsuario);
    }
    
    //PUXAR TODOS OS USUARIO
    public List<Users> listarTodosUsuarios(){
        return usersRepository.findAll();
    } 
}
