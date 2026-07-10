package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.services;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.config.SecurityConfig;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.Users;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.CreateUserBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.ResponseListUsersDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.ResponseSearchEmailID;
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
        //                          empresa        funcionario    ação do fun  o que vai ser   
        String senhaCriptografada = passwordCripto.passwordEncoder().encode(dto.password());
        novoUsuario.setPassword(senhaCriptografada);
        novoUsuario.setRole(dto.role());
        
        //VERIFICA SE JA EXISTE UM EMAIL IGUAL
        Optional<Users> userExist = usersRepository.findByEmail(novoUsuario.getEmail());
        //SE SIM ->
        if (userExist.isPresent()) {
            throw new RuntimeException("Já existe usuário com email: " + novoUsuario.getEmail());
        }

        // Mandamos o repositório salvar no banco e retornamos o resultado
        return usersRepository.save(novoUsuario);
    }
    // GET LISTAGEM PUXAR TODOS OS USUARIOS (AGORA PROTEGIDO COM DTO)
    public List<ResponseListUsersDTO> listarTodosUsuarios(){
        //  Busca todo mundo do banco (com ID, senha, etc)
        List<Users> usuariosBrutos = usersRepository.findAll();

        //  Transforma (Mapeia) a lista bruta na nossa caixinha limpa (DTO)
        return usuariosBrutos.stream().map(usuario -> new ResponseListUsersDTO(
                usuario.getName(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.getCreatedAtUser()
        )).collect(Collectors.toList()); // Junta tudo de volta em uma Lista
    }

    public ResponseSearchEmailID buscarUsuarioPorId(UUID id){
        //VERIFICAMOS PRIMEIRO SE EXISTE O ID
        Users buscarUsuarioBruto = usersRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("ESSE ID NAO ESTA VINCULADO A NENHUM USUARIO"));
        //SE ELE EXISTIR, FAZEMOS A MESMA LOGICA DE CADA USUÁRIO, CRIAR UM NOVO DTO PARA NAO APAGAR O OUTRO
        return new ResponseSearchEmailID(
            buscarUsuarioBruto.getId(),
            buscarUsuarioBruto.getName(),
            buscarUsuarioBruto.getEmail(),
            buscarUsuarioBruto.getRole(),
            buscarUsuarioBruto.getCreatedAtUser()
        );
    }
    public ResponseSearchEmailID buscarUsuarioPorEmail(String email){
        //VERIFICAR SE EXISTE O EMAIL
        Users buscarUsuarioBruto = usersRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("ESSE EMAIL NAO ESTA VINCULADO A NENHUM USUARIO"));
        //SE ELE EXISTIR, CRIAMOS O DTO DE RESPOSTA
        return new ResponseSearchEmailID(
            buscarUsuarioBruto.getId(),
            buscarUsuarioBruto.getName(),
            buscarUsuarioBruto.getEmail(),
            buscarUsuarioBruto.getRole(),
            buscarUsuarioBruto.getCreatedAtUser()
        );
    }
    //DELETAR UM USUARIO
    public void deletarUsuario(UUID id){
        //BUSCAMOS NO BANCO UM USUARIO COM ID MANDADO PELO POSTMAN
        Users buscarUsuarioBruto = usersRepository.findById(id)//se nao encontrado
            .orElseThrow(()-> new RuntimeException("ESSE ID NAO ESTA VINCULADO A NENHUM USUARIO"));
        //se sim
        usersRepository.delete(buscarUsuarioBruto);
    }
}
