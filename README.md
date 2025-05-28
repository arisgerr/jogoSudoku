# 🔢 Sudoku Console Game 🧩

Este é um projeto simples em java do jogo de Sudoku, permite iniciar um tabuleiro, inserir números, remover números, visualizar o estado atual do jogo, verificar o status e limpar ou finalizar a partida. 

# [DIO](www.dio.me) - Trilha Java Básico

## Autores

- [José Luiz Abreu Cardoso Junior](https://github.com/juniorjrjl)

# Tecnologias utilizadas

- ![Java](https://img.shields.io/badge/Java-%23ED8B00?style=for-the-badge&logo=java)

- ![Orientação a Objetos](https://img.shields.io/badge/Orientacao_a_Objetos-Verde?style=flat&logo=java)

## Funcionalidades Principais 🧩

- Iniciar um novo jogo: Comece seu desafio com posições já definidas 🎮

- Inserir número: Coloque um número em qualquer célula vazia (ou altere) 

- Remover número: Tirar um número colocado, só não mexa nos fixos!

- Visualizar jogo: Veja o tabuleiro atualizado a qualquer momento

- Verificar status: Descubra se o seu jogo está correto, com erros ou completo

- Limpar jogo: Apague seu progresso e comece do zero (só com confirmação, calma 😅)

- Finalizar jogo: Termine e celebre a vitória com a tela do seu sucesso! 🎉

# 🧠 Orientação a Objetos? Tem!

Jogo estruturado usando os fundamentos da Programação Orientada a Objetos para deixar tudo organizado.

- Board: A estrela do show, o tabuleiro que guarda os espaços e controla tudo

- Space: Cada célula do tabuleiro, que sabe seu valor atual, se é fixa ou editável

- GameStatusEnum: Um enum que te conta o status do jogo (em andamento, concluído, com erros…)

# Estrutura do projeto

JogoSudoku/

└── src/

    ├── model/
    │   ├── Board.java
    │   ├── GameStatusEnum.java
    │   └── Space.java
    ├── util/
    │   └── BoardTemplate.java
    └── Main.java

# IDE

- ![Eclipse](https://img.shields.io/badge/Eclipse-2C2255?style=for-the-badge&logo=eclipse&logoColor=white)




