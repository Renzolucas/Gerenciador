package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.services;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.config.SecurityConfig;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.Users;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.CreateUsersBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.ResponseUsersDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.dtos.UpdateUsersDTO;
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

    public ResponseUsersDTO create(CreateUsersBodyDTO dto) {
        
        // 1. Criamos a "caixa vazia" da Entidade que vai para o banco
        Users novoUsuario = new Users();
         //VERIFICA SE JA EXISTE UM EMAIL IGUAL
        Optional<Users> userExist = usersRepository.findByEmail(dto.email());
        //SE SIM ->
        if (userExist.isPresent()) {
            throw new RuntimeException("Já existe usuário com email: " + dto.email());
        }
        // 2. Passamos os dados do DTO (Formulário) para a Entidade
        novoUsuario.setName(dto.name());
        novoUsuario.setEmail(dto.email());
        // empresa -> funcionario -> ação do fun ->  o que vai ser   
        String senhaCriptografada = passwordCripto.passwordEncoder().encode(dto.password());
        novoUsuario.setPassword(senhaCriptografada);
        novoUsuario.setRole(dto.role());
        // Mandamos o repositório salvar no banco e retornamos o resultado
        novoUsuario = usersRepository.save(novoUsuario);
        return new ResponseUsersDTO(
                novoUsuario.getId(),
                novoUsuario.getName(),
                novoUsuario.getEmail(),
                novoUsuario.getRole(),
                novoUsuario.getCreatedAtUser(),
                novoUsuario.getUpdateAtUser()
        );
    }

    // GET LISTAGEM PUXAR TODOS OS USUARIOS (AGORA PROTEGIDO COM DTO)
    public List<ResponseUsersDTO> listarTodosUsuarios(){
        //  Busca todo mundo do banco (com ID, senha, etc)
        List<Users> usuariosBrutos = usersRepository.findAll();

        //  Transforma (Mapeia) a lista bruta na nossa caixinha limpa (DTO)
        return usuariosBrutos.stream().map(usuario -> new ResponseUsersDTO(
                usuario.getId(),
                usuario.getName(),
                usuario.getEmail(),
                usuario.getRole(),
                usuario.getCreatedAtUser(),
                usuario.getUpdateAtUser()
        )).collect(Collectors.toList()); // Junta tudo de volta em uma Lista
    }

    //BUSCAR USUARIO POR ID
    public ResponseUsersDTO buscarUsuario(UUID id, String email){
        //VERIFICAMOS PRIMEIRO SE EXISTE O ID
        if(id != null && (email == null || email.isBlank())){
            Users buscarUsuarioBruto = usersRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ESSE ID NAO ESTA VINCULADO A NENHUM USUARIO"));
            //SE ELE EXISTIR, FAZEMOS A MESMA LOGICA DE CADA USUÁRIO,
            //CRIAR UM NOVO DTO PARA NAO APAGAR O OUTRO
            return new ResponseUsersDTO(
                buscarUsuarioBruto.getId(),
                buscarUsuarioBruto.getName(),
                buscarUsuarioBruto.getEmail(),
                buscarUsuarioBruto.getRole(),
                buscarUsuarioBruto.getCreatedAtUser(),
                buscarUsuarioBruto.getUpdateAtUser()
            );
        }
        //SE NAO, SE ID FOR NULO, ENTAO VERIFICAMOS SE EMAIL TAMBEM É NULO
        else if ((email != null && !email.isBlank())&& id == null) {
            //VERIFICAR SE EXISTE O EMAIL
        Users buscarUsuarioBruto = usersRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("ESSE EMAIL NAO ESTA VINCULADO A NENHUM USUARIO"));
        //SE ELE EXISTIR, CRIAMOS O DTO DE RESPOSTA
        return new ResponseUsersDTO(
            buscarUsuarioBruto.getId(),
            buscarUsuarioBruto.getName(),
            buscarUsuarioBruto.getEmail(),
            buscarUsuarioBruto.getRole(),
            buscarUsuarioBruto.getCreatedAtUser(),
            buscarUsuarioBruto.getUpdateAtUser()
        );
        //SE ELE FOR NULO, ENTAO O USUARIO NAO ESCREVEU NADA:
        } else {
            //MENSAGEM DE RETORNO
            throw new RuntimeException("Erro: Você precisa informar um ID ou um E-mail para realizar a busca.");
        }
    }
    

    //DELETAR UM USUARIO
    public void deletarUsuario(UUID id){
        //BUSCAMOS NO BANCO UM USUARIO COM ID MANDADO PELO POSTMAN
        Users buscarUsuarioBruto = usersRepository.findById(id)//se nao encontrado
            .orElseThrow(()-> new RuntimeException("ESSE ID NAO ESTA VINCULADO A NENHUM USUARIO"));
        //se sim
        usersRepository.delete(buscarUsuarioBruto);
    }
    
    //UPDATE DE UM USUARIO
    public ResponseUsersDTO atualizarUsuario(UUID id, UpdateUsersDTO dadosNovos){
        //PUXAR TODOS OS DADOS
        Users buscarUsuarioBruto = usersRepository.findById(id)
            .orElseThrow(()-> new RuntimeException("ESSE ID NAO ESTA VINCULADO A NENHUM USUARIO"));
        //AQUI ATUALIZAMOS OS VALORES NOVOS
        buscarUsuarioBruto.setName(dadosNovos.name());
        buscarUsuarioBruto.setEmail(dadosNovos.email());
        buscarUsuarioBruto.setRole(dadosNovos.role());
        // E AQUI SALVAMOS NO BANCO DE DADOS ATRAVES DO REPOSITORY
        Users usuarioAtualizado = usersRepository.save(buscarUsuarioBruto);
        //AQUI, COMO VAMOS ATUALIZAR SOMENTE 1 USUARIO POR VEZ, NAO HÁ NECESSIDADEO DO STREAM MAP
        return new ResponseUsersDTO(
                usuarioAtualizado.getId(),
                usuarioAtualizado.getName(),
                usuarioAtualizado.getEmail(),
                usuarioAtualizado.getRole(),
                usuarioAtualizado.getCreatedAtUser(), // DATA DE CRIAÇÃO
                usuarioAtualizado.getUpdateAtUser()   // DATA DE ATUALIÇÃO
        );
    }
}
