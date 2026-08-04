const status = document.getElementById("status");
const btn = document.getElementById("btn-testar");

// Ajuste a URL/porta conforme a sua API Spring Boot (apps/api)
const API_URL = "http://localhost:8080/users/health";

btn.addEventListener("click", async () => {
    status.textContent = "Testando...";

    try {
        const response = await fetch(API_URL);
        if (!response.ok) throw new Error("Falha na resposta da API");

        status.textContent = "✅ API respondeu com sucesso!";
    } catch (error) {
        status.textContent = "❌ Não foi possível conectar com a API. Ela está rodando?";
        console.error(error);
    }
});