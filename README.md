<div align="center">
<img src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java"/>
<img src="https://img.shields.io/badge/Design%20Patterns-SOLID-blue?style=for-the-badge" alt="SOLID"/>
<img src="https://img.shields.io/badge/Refactoring-✔%20Concluído-success?style=for-the-badge" alt="Status"/>
<img src="https://img.shields.io/badge/Semestre-4º%20CC-orange?style=for-the-badge" alt="Semestre"/><br/><br/>

# 🛒 Feira Livre — Refatoração SOLID
Transformando um sistema monolítico e frágil em uma arquitetura limpa, extensível e orientada a princípios.

</div>

## 👥 Integrantes do Projeto
| Função | Nome |
| :--- | :--- |
|  Desenvolvedor | Caio Alves |
|  Desenvolvedor | Antônio Leanderson |

## 🎓 Turma
**T200-26** — Ciência da Computação · 4º Semestre

## 📋 Sobre o Projeto
Este projeto é uma refatoração completa do sistema Feira Livre, originalmente implementado com diversas violações de boas práticas de design de software. O objetivo foi identificar e corrigir cada problema, aplicando os 5 princípios SOLID de forma rigorosa e justificada.

A tabela abaixo apresenta um panorama das mudanças realizadas:

| Princípio | Problema Original | Solução Aplicada |
| :--- | :--- | :--- |
| **SRP** | Classe "faz-tudo" com múltiplas responsabilidades. | Separação em classes e interfaces especializadas. |
| **OCP** | Blocos `if/else` rígidos para desconto e pagamento. | Padrão *Strategy* com interfaces extensíveis. |
| **LSP** | Subclasses com comportamentos inesperados e *crashes*. | Contrato uniforme de cálculo de prazos de entrega. |
| **ISP** | Interface monolítica `PagamentoGateway` forçando erros. | Interfaces coesas e focadas por método de pagamento. |
| **DIP** | Instâncias concretas com `new` dentro do serviço orquestrador. | Injeção de dependência via construtor com abstrações. |

## 🔧 Descrição das Melhorias

### 1 · SRP — Princípio da Responsabilidade Única
*"Uma classe deve ter apenas um motivo para mudar."*
* **Problema identificado:** A classe orquestradora original acumulava responsabilidades completamente distintas: finalizava pedidos, imprimia cupons, enviava notificações, gerenciava dados e exportava relatórios.
* **Solução aplicada:** Cada responsabilidade foi extraída para sua própria classe especializada: `ImpressoraTermica`, `NotificadorWhatsApp`, `PedidoRepositoryMemoria` e `ExportadorCsvPedido`.

### 2 · OCP — Princípio Aberto/Fechado
*"Entidades de software devem ser abertas para extensão, mas fechadas para modificação."*
* **Problema identificado:** O sistema utilizava estruturas `if/else` encadeadas para determinar o desconto e o pagamento. Adicionar uma nova regra exigia alterar diretamente o código do orquestrador.
* **Solução aplicada:** Foi implementado o padrão *Strategy*, introduzindo as interfaces `PoliticaDesconto` e `ProcessadorPagamento`. Novas regras podem ser adicionadas criando novas classes sem modificar o orquestrador principal.

### 3 · LSP — Princípio da Substituição de Liskov
*"Subtipos devem ser substituíveis por seus tipos base sem alterar a corretude do programa."*
* **Problema identificado:** A classe `EntregaExpressa` lançava exceções inesperadas para distâncias maiores que 20km, quebrando o contrato do tipo base.
* **Solução aplicada:** Definimos o contrato `CalculadoraPrazoEntrega`. As implementações `EntregaNormal` e `EntregaExpressa` foram ajustadas para respeitar plenamente o contrato matemático, garantindo que possam ser substituídas sem efeitos colaterais.

### 4 · ISP — Princípio da Segregação de Interface
*"Nenhum cliente deve ser forçado a depender de métodos que não utiliza."*
* **Problema identificado:** A interface `PagamentoGateway` agrupava métodos de todos os pagamentos, obrigando o PIX, por exemplo, a lançar exceções para métodos irrelevantes como emitir nota.
* **Solução aplicada:** A interface monolítica foi desmembrada em um contrato coeso (`ProcessadorPagamento`). Cada implementação agora depende exclusivamente do método de processamento que realmente utiliza.

### 5 · DIP — Princípio da Inversão de Dependência
*"Módulos de alto nível não devem depender de módulos de baixo nível. Ambos devem depender de abstrações."*
* **Problema identificado:** O serviço `FinalizadorPedidoService` instanciava diretamente suas dependências concretas usando o operador `new`, criando um acoplamento rígido.
* **Solução aplicada:** O serviço foi refatorado para depender apenas de abstrações. As dependências concretas são injetadas via construtor a partir da classe `SolucaoMain` (*Composition Root*).

## 🏗️ Estrutura Arquitetural Aplicada

```text
src/feira/solucao/
├── SolucaoMain.java              ← Ponto de entrada e Injeção de Dependências
├── service/
│   └── FinalizadorPedidoService.java ← Orquestrador (depende apenas de abstrações)
├── domain/                       ← Classes de negócio base (Pedido, Produto)
├── desconto/                     ← Contratos e regras de Desconto (OCP)
├── pagamento/                    ← Contratos e gateways de Pagamento (ISP/OCP)
├── entrega/                      ← Estratégias de Prazo de Entrega (LSP)
├── cupom/                        ← Lógica de Impressão (SRP)
├── notificacao/                  ← Envio de Mensagens (SRP)
├── relatorio/                    ← Exportação de Dados (SRP)
└── repository/                   ← Persistência em Memória (SRP)

======================================================================================================================

## Como Executar o Projeto

Para compilar e executar a solução refatorada, utilize os comandos abaixo no PowerShell, a partir da pasta raiz do projeto:

```powershell
# 1. Compilar os ficheiros Java
javac -d out (Get-ChildItem -Path src -Recurse -Filter *.java | ForEach-Object { $_.FullName })

# 2. Executar a classe principal da solução
java -cp out feira.solucao.SolucaoMain