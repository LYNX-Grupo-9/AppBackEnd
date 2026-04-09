# 🧠 Arquitetura

O projeto segue uma arquitetura em camadas:

```
Controller → UseCase → Domain → Gateway → Adapter → Repository → Banco
```

### 📦 Camadas

* **Domain** → regras de negócio (entidades puras)
* **UseCase** → fluxo da aplicação
* **Gateway** → contratos (interfaces)
* **Adapter** → implementação (JPA)
* **Controller** → entrada HTTP (API)

---

# 📁 Estrutura do Projeto

```
src/main/java/com/exemple/adapter/backapp

├── core
│   ├── domain
│   ├── application
│   │   ├── usecase
│   │   ├── dto
│   │   └── exception
│   └── adapter
│       └── gateway
│
├── infrastructure
│   ├── persistence
│   │   ├── entity
│   │   ├── repository
│   │   ├── mapper
│   │   └── adapter
│   │
│   ├── web
│   │   └── controller
│   │
│   ├── config
│   └── di (BeanConfig)
```

---

# ⚙️ Funcionalidades

## 👤 Cliente

* Cadastro
* Login (JWT)

## 📁 Caso

* Criar caso com análise de IA

## 💬 Conversa

* Criar conversa entre cliente e advogado

## 📩 Mensagem

* Enviar mensagens (chat)

## ⚖️ Advogado Interessado

* Advogado demonstra interesse
* Cliente escolhe advogado definitivo

---

# 🔐 Autenticação

O sistema utiliza **JWT** para autenticação.

### 📌 Endpoints

* `POST /api/clientes/login`
* `POST /api/advogados/login`

O token deve ser enviado no header:

```
Authorization: Bearer TOKEN
```

---

# 🧪 Como Rodar o Projeto

## 🥇 1. Clonar o projeto

```
git clone <repo>
```

---

## 🥈 2. Configurar variáveis

No `application.properties`:

```
spring.datasource.url=jdbc:mysql://localhost:3306/lynx
spring.datasource.username=SEU_USUARIO
spring.datasource.password=SUA_SENHA

jwt.secret=SUA_CHAVE_SECRETA
jwt.validity=3600000
```

---

## 🥉 3. Rodar o projeto

```
./mvnw spring-boot:run
```

ou pela IDE

---

## 🧾 4. Acessar Swagger

```
http://localhost:8081/swagger-ui.html
```

---

# 📡 Exemplos de Requisição

## 📁 Criar Caso

```json
POST /api/casos

{
  "areaDireito": "Trabalhista",
  "titulo": "Demissão",
  "descricao": "Fui demitido sem receber direitos",
  "analiseIa": "Possível caso trabalhista",
  "idCliente": "UUID"
}
```

---

## 💬 Criar Conversa

```json
POST /api/conversas

{
  "idCliente": "UUID",
  "idAdvogado": "UUID",
  "idCaso": "UUID"
}
```

---

## 📩 Enviar Mensagem

```json
POST /api/mensagens

{
  "idConversa": "UUID",
  "conteudo": "Olá!",
  "remetenteTipo": "CLIENTE",
  "remetenteId": "UUID"
}
```

---

# 🧠 Boas Práticas Utilizadas

* Clean Architecture
* DDD (Domain-Driven Design)
* Separação de responsabilidades
* Uso de UUID
* Desacoplamento entre serviços
* JWT para segurança

---

# 🚀 Próximos Passos

* 🔐 Pegar usuário via token (não via body)
* ⚡ Chat em tempo real (WebSocket)
* 📊 Listagens (casos, mensagens, conversas)
* ✅ Validações de negócio

---

# 👨‍💻 Time

Projeto desenvolvido por **Lynx** 🐺

---
