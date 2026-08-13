# FreeApp - Plataforma de Serviços Freelance

O **FreeApp** é um aplicativo Android desenvolvido durante um programa de capacitação em desenvolvimento mobile, com foco na aplicação de boas práticas de arquitetura, experiência do usuário e construção de aplicações modernas utilizando Kotlin.

O sistema tem como objetivo conectar clientes e profissionais freelancers em uma plataforma intuitiva, permitindo a divulgação de serviços, contratação de profissionais e gerenciamento de oportunidades de trabalho.

---

## Objetivo do Projeto

> [!NOTE]
>
> O principal objetivo do **FreeApp** é simular um cenário real de desenvolvimento de software, aplicando conceitos de arquitetura mobile, design de interface e organização de código.
>
> O projeto contempla:
>
> * Cadastro e autenticação de usuários;
> * Recuperação de senha via SMS e E-mail;
> * Cadastro de profissionais freelancers;
> * Contratação de serviços;
> * Fluxo de pagamento;
> * Navegação intuitiva e experiência mobile;
> * Aplicação de arquitetura MVVM.



## Funcionalidades

> [!IMPORTANT]
>
> O aplicativo foi desenvolvido para facilitar a conexão entre clientes e freelancers.

Funcionalidades implementadas:

* Tela de Login;
* Cadastro de usuários;
* Aceite dos Termos de Privacidade;
* Recuperação de senha via SMS;
* Recuperação de senha via E-mail;
* Carrossel de apresentação do aplicativo;
* Navegação entre telas;
* Fluxo de contratação de serviços;
* Simulação de pagamento;
* Interface responsiva para dispositivos Android;
* Validação de formulários.

---

## Tecnologias Utilizadas

> [!NOTE]
>
> O projeto utiliza tecnologias modernas do ecossistema Android.

* **Kotlin** → Linguagem principal da aplicação;
* **Android Studio** → Ambiente de desenvolvimento;
* **Android Jetpack** → Biblioteca de componentes Android;
* **MVVM** → Arquitetura da aplicação;
* **Navigation Component** → Navegação entre telas;
* **View Binding** → Manipulação segura dos componentes visuais;
* **Material Design 3** → Componentes visuais modernos;
* **RecyclerView** → Construção de listas;
* **Coroutines** → Processamento assíncrono;
* **LiveData** → Atualização reativa da interface.

---

## Arquitetura do Projeto

> [!TIP]
>
> A aplicação foi estruturada utilizando o padrão MVVM para facilitar manutenção, escalabilidade e organização do código.

```plaintext
UI Layer
├── Activities
├── Fragments
├── Adapters
└── ViewModels

Domain Layer
├── Models
└── Business Rules

Data Layer
├── Repository
├── Local Data
└── Remote Data
```

### Fluxo de Dados

```plaintext
UI
 ↓
ViewModel
 ↓
Repository
 ↓
Data Source
```

---

## O Que Foi Implementado

> [!IMPORTANT]
>
> Nesta versão foram desenvolvidas as principais telas e fluxos da aplicação.

### Autenticação

* Tela de Login;
* Cadastro de Usuário;
* Validação dos campos;
* Recuperação de senha por SMS;
* Recuperação de senha por E-mail.

### Navegação

* Fluxo completo entre telas;
* Carrossel de apresentação;
* Navegação utilizando componentes Android.

### Interface

* Componentes Material Design;
* Layout responsivo;
* Experiência pensada para dispositivos móveis;
* Organização visual focada na usabilidade.

---

## Telas do Aplicativo

| Tela | Descrição |
|--------|-----------|
| **Splash Screen** | Tela inicial do aplicativo |
| **Carrossel** | Apresentação das funcionalidades principais |
| **Login** | Autenticação do usuário |
| **Cadastro** | Registro de nova conta |
| **Termos de Privacidade** | Exibição dos termos de uso |
| **Esqueci Minha Senha** | Recuperação por SMS e E-mail |
| **Pagamento** | Simulação do fluxo de contratação |

---

## Como Executar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/ThaisKheyla/FreeApp.git

cd FreeApp
```

### 2. Abrir no Android Studio

> [!TIP]
>
> Abra a pasta raiz do projeto e aguarde a sincronização do Gradle.

### 3. Executar o projeto

```bash
Run > app
```

ou clique no botão ▶ Run do Android Studio.

---

## Estrutura do Projeto

```plaintext
FreeApp/
│
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   ├── data/
│   │   │   │   ├── domain/
│   │   │   │   ├── ui/
│   │   │   │   └── utils/
│   │   │   └── res/
│   │
│   ├── build.gradle.kts
│
├── README.md
└── docs/
```

---

## Principais Conceitos Aplicados

> [!NOTE]

* Arquitetura MVVM;
* Organização por camadas;
* Navegação entre telas;
* Componentização;
* Boas práticas de UI/UX;
* Responsividade;
* Validação de formulários;
* Desenvolvimento Mobile com Kotlin.

---

## Próximos Passos

> [!TIP]

Algumas melhorias planejadas para versões futuras:

* Integração com banco de dados;
* Integração com API REST;
* Cadastro completo de freelancers;
* Sistema de avaliação;
* Favoritos;
* Chat entre clientes e profissionais;
* Histórico de serviços;
* Publicação na Play Store.

---

## Sobre o Projeto

O **FreeApp** foi desenvolvido como parte de um treinamento prático em desenvolvimento Android, simulando um ambiente real de projeto e permitindo aplicar conhecimentos técnicos em uma solução voltada ao mercado de serviços freelance.

O projeto busca unir aprendizado técnico, experiência do usuário e boas práticas de desenvolvimento mobile.

---

## Equipe

> [!NOTE]

**Desenvolvedoras**

Kheyla Thais Quispe Paucara

Marcela Bastos Vicente

**Orientação Técnica**

Rafael Kon Tein

---

## Créditos

Projeto desenvolvido durante programa de capacitação em desenvolvimento mobile Android.

**Projeto:** FreeApp  
**Finalidade:** Educacional e treinamento prático  
**Tecnologias:** Kotlin, Android Jetpack, MVVM e Material Design 3

---

## Licença

> [!WARNING]
>
> Projeto desenvolvido para fins educacionais e de aprendizagem.

Todos os direitos reservados aos respectivos autores.
