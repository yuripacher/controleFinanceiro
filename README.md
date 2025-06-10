# 💰 Sistema de Controle Financeiro Doméstico

## 📋 Sobre o Projeto

Este projeto é um **trabalho final da disciplina de Programação Orientada a Objetos** da Universidade Regional de Blumenau (FURB), desenvolvido como uma aplicação completa que demonstra todos os conceitos fundamentais estudados durante o curso.

O sistema é uma ferramenta prática para **controle de receitas e despesas domésticas**, permitindo que usuários gerenciem suas finanças pessoais de forma organizada e intuitiva através de uma interface gráfica.

## 🎯 Objetivo Acadêmico

Este trabalho representa a **consolidação de todos os pilares da Programação Orientada a Objetos**:

- **🎭 Abstração**: Modelagem de conceitos do mundo real (receitas, despesas, categorias)
- **🔒 Encapsulamento**: Proteção e organização dos dados através de modificadores de acesso
- **🔄 Polimorfismo**: Comportamentos específicos para cada categoria de receita e despesa
- **👨‍👩‍👧‍👦 Herança**: Hierarquia de classes para diferentes tipos de transações financeiras
- **📁 Persistência de Dados**: Manipulação de arquivos CSV para armazenamento
- **🏗️ Arquitetura em Camadas**: Separação entre lógica de negócio e interface

## 🛠️ Tecnologias Utilizadas

- **☕ Java LTS**: Linguagem principal do desenvolvimento
- **🖼️ Java Swing**: Framework para criação da interface gráfica (GUI)
- **📊 Arquivos CSV**: Formato de persistência de dados
- **🧪 JUnit**: Framework para testes unitários
- **📐 UML**: Modelagem do diagrama de classes

> **Nota**: O projeto foi desenvolvido **sem bibliotecas externas**, utilizando apenas recursos nativos do Java, demonstrando domínio completo da linguagem.

## ✨ Funcionalidades

### 📈 Gestão de Receitas
- Cadastro de receitas categorizadas:
  - 💼 Salário
  - 🎁 Décimo Terceiro
  - 🏖️ Férias
  - 💡 Outras Receitas
- Controle de datas de recebimento (passadas ou futuras)

### 📉 Controle de Despesas
- Registro de despesas organizadas por categoria:
  - 🍽️ Alimentação
  - 🚗 Transporte
  - 🏠 Residência
  - 🏥 Saúde
  - 📚 Educação
  - 🎬 Entretenimento
  - 📝 Outras Despesas

### 📊 Relatórios e Consultas
- **💰 Saldo Atual**: Consulta do saldo disponível até a data presente
- **📈 Saldo Total**: Visualização do saldo geral (independente do período)
- **📋 Listagens Completas**: Exibição de todas as receitas e despesas
- **📑 Extrato Detalhado**: Histórico cronológico com impacto no saldo

## 🏗️ Arquitetura do Sistema

O projeto segue uma **arquitetura em duas camadas**:

```
📦 Camada de Apresentação (GUI)
├── 🖼️ Interfaces Swing
├── 🎛️ Controladores de Eventos
└── 📱 Componentes Visuais

📦 Camada de Negócio
├── 💼 Classes de Modelo
├── 🔄 Regras de Negócio
├── 💾 Persistência CSV
└── 🧮 Cálculos Financeiros
```

## 📁 Estrutura de Dados

Os dados são armazenados em **arquivos CSV**, garantindo:
- 💾 Persistência entre execuções
- 📊 Formato legível e editável
- 🔄 Facilidade de backup e migração

## 🧪 Qualidade e Testes

- **📋 Plano de Testes**: Documentação completa dos cenários de teste
- **🔬 Testes JUnit**: Cobertura de todos os métodos públicos das classes de negócio
- **📝 Documentação Javadoc**: Classes completamente documentadas

## 👥 Desenvolvimento

**Equipe**: 2 desenvolvedores - Martina Keunecke Beck e Yuri Ricardo Pacher Rabelo
**Professor**: Artur Ricardo Bizon
**Disciplina**: Programação Orientada a Objetos
**Instituição**: Universidade Regional de Blumenau (FURB) 2025-1

## 🎯 Valor Educacional

Este projeto não é apenas um sistema funcional, mas uma **demonstração prática** de como os conceitos teóricos da POO se aplicam na resolução de problemas reais. Cada linha de código reflete os princípios fundamentais da programação orientada a objetos, desde a modelagem inicial até a implementação final.

---

*💡 **Sistema de Controle Financeiro Doméstico** - Transformando conhecimento teórico em solução prática através da Programação Orientada a Objetos.*