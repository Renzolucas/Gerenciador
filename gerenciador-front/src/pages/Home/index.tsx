import "./style.css";
import Delete from "../../assets/delete.svg";

function Home() {
  const users = [
    {
      id: "12123oi",
      name: "Lucas",
      email: "Lucas@gmail.com",
      senha: "123",
      role: "ADMIN",
      createdAtUser: "2025:05:01T11:00:00",
    },
    {
      id: "12123oi",
      name: "Lucas1",
      email: "Lucas1@gmail.com",
      senha: "123",
      role: "EMPLOYEE",
      createdAtUser: "2025:05:01T11:00:00",
    },
  ];

  return (
    <div className="container">
      <form>
        <h1>Cadastro de Usuario</h1>

        <label htmlFor="nome">Nome</label>
        <input
          type="text"
          name="nome"
          placeholder="Digite seu nome: "
          required
        />

        <label htmlFor="email">Email</label>
        <input
          type="text"
          name="email"
          placeholder="Digite seu email: "
          required
        />

        <label htmlFor="senha">Senha</label>
        <input type="password" name="senha" placeholder="********" required />

        <label htmlFor="Role">Role </label>
        <select id="role" required>
          <option value="">ESCOLHA UMA OPÇÃO</option>
          <option value="ADMIN">ADMIN</option>
          <option value="EMPLOYEE">EMPLOYEE</option>
        </select>

        <button type="button">Cadastrar</button>
      </form>

      {users.map((user) => (
        <div key={user.id} className="card">
          <div>
            <p>Nome: <span>{user.name}</span></p>

            <p>Email: <span>{user.email}</span></p>

            <p>Senha: <span>{user.senha}</span></p>

            <p>Role: <span>{user.role}</span></p>

            <p>Data de Criação: <span>{user.createdAtUser}</span></p>
          </div>

          <button id="lixeira">
            <img src={Delete} />
          </button>
        </div>
      ))}
    </div>
  );
}

export default Home;
