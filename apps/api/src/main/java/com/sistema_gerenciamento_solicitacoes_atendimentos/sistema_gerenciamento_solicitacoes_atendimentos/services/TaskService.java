package com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.Task;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.dtos.CreateTaskBodyDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.task.dtos.ResponseTaskDTO;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.domain.user.Users;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.exceptions.GerenciadorNotFoundExceptions;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.repositories.TaskRepository;
import com.sistema_gerenciamento_solicitacoes_atendimentos.sistema_gerenciamento_solicitacoes_atendimentos.repositories.UsersRepository;

import jakarta.transaction.Transactional;
@Service
public class TaskService {
    private final TaskRepository taskRepository;
    //PEGAMOS O USERSREPOSITORY PQ É LA QUE FICA O ID DO USUARIO
    private final UsersRepository usersRepository;
    //CONSTRUTOR
    public TaskService(TaskRepository taskRepository, UsersRepository usersRepository) {
        this.taskRepository = taskRepository;
        this.usersRepository = usersRepository;
    }
    //PARTE LOGICA DE CRIAR UMA TASK
    public ResponseTaskDTO createTask(CreateTaskBodyDTO dto){
        //CRIA UM ESPAÇO NA ENTIDADE PARA AS NOVAS INFORMAÇÕES NO BANCO
        Task novaTask = new Task();

        //BUSCAR NO BANDO DE DADOS SE EXISTE OS IDs PELA COMUNICAO USERSREPOSITY
        Users employee = usersRepository.findById(dto.usersEmployeeId())
            .orElseThrow(() -> new GerenciadorNotFoundExceptions());
        Users admin = usersRepository.findById(dto.usersAdminId())
            .orElseThrow(() -> new GerenciadorNotFoundExceptions("Admin não encontrado com o ID: " + dto.usersAdminId()));
        
        //DADOS DO DTO PARA A ENTIDADE
        novaTask.setTitulo(dto.titulo());
        novaTask.setDescricao(dto.descricao());
        novaTask.setUsersEmployee(employee);
        novaTask.setUsersAdmin(admin);
        novaTask.setDeadline(dto.deadline());
        novaTask.setStatus(dto.status());
        novaTask.setPriority(dto.priority());
        //ATUALIZANDO O BANCO
        novaTask = taskRepository.save(novaTask);

        //CRIAMOS UMA NOVA "CARTA"/DTO PARA OCUTAR AS INFORMAÇÕES DOS IDs INFORMADOS.
        return new ResponseTaskDTO(
        novaTask.getTitulo(),
        novaTask.getDescricao(),
        novaTask.getDeadline(),
        novaTask.getStatus(),
        novaTask.getPriority()
        );
    }
    //PARTE LOGICA DE LISTAR TUDO
    public List<Task> buscarTask(){
        //BUSCAMOS TUDO
        List<Task> taskBrutas = taskRepository.findAll();
        //JOGAMOS NA ESTEIRA NO STREAM, O MAP RECEBE TRANSFORMA OS DADOS
        return taskBrutas;
    }

    //PARTE LOGICA DO BUSCAR POR ID
    public ResponseTaskDTO buscarTaskPorId(UUID id){
        //BUSCA O ID DA TASK
        Task buscarTask = taskRepository.findById(id)
            .orElseThrow(()-> new GerenciadorNotFoundExceptions());
        //SE SIM, CHAMAMOS A ESTRUTURA DO JSON DE RETORNO
        return new ResponseTaskDTO(
            buscarTask.getTitulo(),
            buscarTask.getDescricao(),
            buscarTask.getDeadline(),
            buscarTask.getStatus(),
            buscarTask.getPriority()
        );
    }
    
    //PARTE LOGICA DO DELETE
    @Transactional
    public void deletarTask(UUID id){
        //BUSCA O ID DA TASK
        Task buscarTask = taskRepository.findById(id)
            .orElseThrow(()->  new GerenciadorNotFoundExceptions());
        //SE SIM, DELETAMOS O USUARIO
        taskRepository.delete(buscarTask);
    }

    //PARTE LOGICA DO PUT(ATUALIZAR DADOS)
    public Task atualizarTask(UUID id, ResponseTaskDTO dadosNovos){
        //PUXANDO TASK
        Task buscarTask = taskRepository.findById(id)
            .orElseThrow(()->  new GerenciadorNotFoundExceptions());
        //ATUALIZAMOS OS VALORES
        buscarTask.setTitulo(dadosNovos.titulo());
        buscarTask.setDescricao(dadosNovos.descricao());
        buscarTask.setDeadline(dadosNovos.deadline());
        buscarTask.setStatus(dadosNovos.status());
        buscarTask.setPriority(dadosNovos.priority());
        Task taskAtualizada = taskRepository.save(buscarTask);
        return taskAtualizada;
            
    }

    public List<Task> buscarTaskPorIdUsers(UUID id){
        //VERIFICA O ID DO USUARIO
        Users buscarIdUsers = usersRepository.findById(id)
            .orElseThrow(()-> new GerenciadorNotFoundExceptions());
        //AQUI EU VERIFICO SE EXISTE O ID DO USUARIO, SE SIM, ENTAO MANDO PRO CONTROLLER
        List<Task> buscarTask = taskRepository.findByUsersEmployeeOrUsersAdmin(buscarIdUsers, buscarIdUsers);
        if(buscarTask.isEmpty()){
            throw new GerenciadorNotFoundExceptions();
        }
        return buscarTask;
    }   
}
