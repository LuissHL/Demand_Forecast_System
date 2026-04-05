# 📊 Demand Forecast - Previsão de Vendas com IA

> Um sistema completo estilo SaaS para análise preditiva de vendas, unindo Angular, Spring Boot e Machine Learning.

![Status do Projeto](https://img.shields.io/badge/Status-Concluído-success)
![Angular](https://img.shields.io/badge/Angular-DD0031?style=flat&logo=angular&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=flat&logo=spring-boot&logoColor=white)
![FastAPI](https://img.shields.io/badge/FastAPI-009688?style=flat&logo=FastAPI&logoColor=white)

## Sobre o Projeto

O **Demand Forecast** é uma ferramenta corporativa desenvolvida para auxiliar gestores e analistas na tomada de decisão. O sistema permite o upload de históricos de vendas (arquivos CSV ou Excel) e utiliza um motor de Inteligência Artificial para prever as tendências de demanda futuras. 

O grande diferencial do projeto é sua **arquitetura em 3 camadas**, garantindo escalabilidade, processamento assíncrono e separação clara de responsabilidades entre interface, regras de negócio e processamento de dados pesados.

---

## Demonstração Visual

![Captura de Tela do Dashboard](/docs/assets/foto1.png)
![Captura de Tela do Dashboard](/docs/assets/foto2.png)



---

## Principais Funcionalidades

* **Upload Inteligente:** Processamento de planilhas de vendas com validação de dados.
* **Treinamento On-the-Fly:** A IA (Random Forest) é treinada instantaneamente com os dados específicos do usuário.
* **Previsão Dinâmica:** Ajuste do horizonte de previsão em tempo real (Curto prazo, Mensal, Padrão ou Longo Prazo).
* **Exportação de Relatórios:** Geração de documentos PDF corporativos com gráficos e tabelas das previsões geradas.
* **Dark Mode Global:** Interface adaptável ao tema claro ou escuro com persistência local.

---

## Tecnologias Utilizadas

O projeto foi construído com as seguintes tecnologias:

### Frontend (Interface e Interação)
* **Angular:** Framework principal (Arquitetura orientada a componentes).
* **TypeScript & CSS3:** Tipagem estática e estilização fluida (Flexbox/Grid).
* **Chart.js / HTML5 Canvas:** Renderização dos gráficos de previsão.
* **jsPDF & html2canvas:** Motor de exportação de relatórios em PDF.

### Backend (Regras de Negócio e Roteamento)
* **Java 17+:** Linguagem principal do servidor.
* **Spring Boot:** Framework para criação de APIs RESTful robustas.
* **Lombok:** Redução de boilerplate (DTOs, Getters/Setters).
* **Jackson:** Serialização avançada e manipulação dos pacotes JSON.

### Motor de IA (Processamento de Dados)
* **Python:** Linguagem base para o serviço de Machine Learning.
* **FastAPI:** Exposição de endpoints ultrarrápidos para o consumo do Java.
* **Pandas:** Manipulação, limpeza e feature engineering de DataFrames.
* **Scikit-Learn:** Criação do modelo preditivo usando *Random Forest Regressor*.

---

## Arquitetura do Sistema

O fluxo de dados ocorre na seguinte ordem:
1.  **Frontend (Angular):** Coleta o arquivo CSV, os parâmetros (ex: 30 dias de previsão) e envia um JSON estruturado.
2.  **Backend (Java/Spring):** Recebe os dados, aplica regras de validação através de DTOs mapeados e repassa o pacote de forma segura.
3.  **Motor (Python/FastAPI):** Recebe o histórico, extrai as *features* (feriados, promoções, descontos), treina o modelo de Machine Learning e retorna a previsão exata de volta pelas camadas até o gráfico do usuário.

---

## 📦 Como rodar este projeto na sua máquina

Para clonar e executar este aplicativo, você precisará do [Git](https://git-scm.com), [Node.js](https://nodejs.org/en/download/), [Java JDK](https://www.oracle.com/java/technologies/downloads/) e [Python](https://www.python.org/downloads/) instalados no seu computador.

### 1. Clonando o repositório
```bash
git clone [https://github.com/](https://github.com/)[seu-usuario]/[nome-do-repositorio].git
```

### 2. Rodando o Motor de IA (Python)
```bash
cd [pasta-do-python]
pip install fastapi uvicorn pandas scikit-learn
uvicorn app:app --reload --port 8000
```

### 3. Rodando o Backend (Java)
```bash
cd [pasta-do-java]
./mvnw spring-boot:run
```

### 4. Rodando o Frontend (Angular)
```bash
cd [pasta-do-angular]
npm install
ng serve
```
Acesse `http://localhost:4200` no seu navegador.

---

## Autor

Desenvolvido por **[Luis Henrique]**.

* LinkedIn: [Seu LinkedIn](https://www.linkedin.com/in/luis-henriquee/)