
import './style.css'
import Delete from '../../assets/delete.svg'

function Home() {

 


  return (
    <div className='container'>
      <form>
        <h1>Cadastro de Usuario</h1>

        <label htmlFor="nome">Nome</label>
        <input type="text" name='nome' placeholder='Digite seu nome: ' required />

        <label htmlFor="email">Email</label>
        <input type="text" name='email' placeholder='Digite seu email: ' required />

        <label htmlFor="senha">Senha</label>
        <input type="password" name='senha' placeholder='********' required/>

        <label htmlFor="Role"></label>
        <select id="role" required>
          <option value="">ESCOLHA UMA OPÇÃO</option>
          <option value="ADMIN">ADMIN</option>
          <option value="EMPLOYEE">EMPLOYEE</option>
        </select>
        
        <button type='button'>Cadastrar</button>

      </form>
        <div>
          <div>
            <p>Nome: </p>
            <p>Email: </p>
            <p>Senha: </p>
            <p>Role: </p>
            <p>Data de Criação: </p>
          </div>
          <button>
            <img src={Delete} />
          </button>
        </div>
    </div>
  )
}

export default Home
