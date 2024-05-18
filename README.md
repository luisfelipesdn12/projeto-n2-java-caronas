# 2024-01 PSI - Projeto N2

## CarONE-M: Viagens compartilhadas

## Descrição e contexto geral:

A mobilidade urbana tornou-se um tema relevante a sociedade, principalmente em grandes centros urbanos que padecem com um trânsito cada vez mais volumoso e congestionado. Uma iniciativa com potencial de colaborar na diminuição imediata desse problema é o desenvolvimento de um sistema para compartilhamento de viagens.

Compartilhar caronas otimiza o uso de veículos, reduz a quantidade de carros em circulação e ajuda na diminuição das emissões de carbono, contribuindo para um ambiente mais limpo e sustentável. Mostra-se ainda como alternativa econômica tanto para motoristas quanto passageiros, que podem ratear custos com combustível, estacionamento e pedágios.

Além dos benefícios práticos, as caronas também promovem a interação social. Pessoas que compartilham trajetos semelhantes podem se conectar, fortalecer laços sociais e criar uma comunidade de usuários comprometidos com a mobilidade colaborativa, além de tornar a experiência mais agradável. Grupos regulares podem escalonar quem será o condutor da vez, alternando os veículos e/ou os motoristas em cada viagem.

Uma plataforma que proporcione uma maneira centralizada de encontrar e agendar caronas, simplifica o processo e torna mais fácil para os usuários encontrem parceiros de viagem. Assim, pede-se neste projeto que você desenvolva uma aplicação de caronas que possa atender aos requisitos essenciais desse contexto.

## Requisitos funcionais

O sistema deve apresentar as seguintes funcionalidades:

- **Cadastro de Usuários**: Os usuários devem criar um perfil de utilizador na plataforma, a partir cadastramento de informações pessoais (nome, endereço, e-mail e telefone). O acesso ao sistema é feito apenas por usuários cadastrados, com login validado por uma senha.
- **Cadastrar uma viagem**: é feita por motoristas que desejam criar viagens e compartilhar com outros usuários. Nesse processo devem ser informados: a quantidade de lugares disponíveis no veículo (sem contar o motorista), o local de partida, o destino e o trajeto planejado. O trajeto é descrito por um conjunto de locais pelo qual o motorista pretende passar, inclusive que servem de pontos para o embarque e desembarque de outros passageiros.
- **Buscar caronas**: Os passageiros indicam o local de partida e de destino e o sistema retorna para o usuário um conjunto de viagens que passem por perto desses locais. Uma viagem pode ser compartilhada se houver lugar disponível no veículo e se os locais de origem e destino estiverem dentro do trajeto registrado, admitindo-se uma variação de até 2km de distância em relação aos pontos informados.
- **Confirmar a carona:** A partir de uma lista de viagens possíveis, o passageiro seleciona uma e solicita a confirmação da carona. Esse processo notifica o motorista e, caso ele concorde, o sistema registra o passageiro na viagem (ocupando um dos lugares no veículo), além de seu ponto de embarque e de desembarque ao longo do trajeto. Se o motorista recusar, o passageiro é informado e pode selecionar uma outra viagem da lista.
- **Avaliações**: as avaliações são dadas por notas que variam de 1 a 5, podendo ser complementada por um comentário breve (opcional). Apenas usuários que viajaram juntos que podem avaliar uma viagem.
- **Menu de opções**: apresenta ao usuário todas as opões de interação com a aplicação. Considerando que um mesmo usuário pode interagir de formas diferentes com o sistema, é importante que de início o usuário escolha o perfil de operação que deseja, selecionando entre *Motorista* ou *Passageiro*.
    1. Como motorista é possível cadastrar novas viagens, consultar quais são os passageiros de uma viagem a fazer, além de verificar a média de avaliações e comentários sobre viagens anteriores.
    2. Na condição de passageiro é possível buscar por viagens que poderiam lhe atender, pedir carona e avaliar uma viagem feita.
- **Custos e rateios**: não serão geridos pelo sistema, portanto assim esse requisito deixa de ser um aspecto de preocupação tanto para a modelagem quanto de implementação.

*Importante: Como não estamos nos ocupando com a persistência dos dados (seja na forma de arquivos ou por meio de um SGBD), os integrantes devem simular a existência de uma base de dados cadastrando de maneira previa, no início da execução do programa, alguns usuários, viagens feitas, suas avaliações e viagens a fazer.*

## Requisitos técnicos

O seu projeto deverá:

- ser implementado em Java.
- utilizar classes e objetos para modelar os elementos do sistema (usuários, viagens, trajetos, etc).
- usar *ArraysLists* para armazenar coleções de objetos, ao invés de fazer toda a gestão de *arrays*.
- implementar validações na entrada de dados impedindo que informações inválidas sejam gravadas.
- prover uma interface de usuário simples (console) para interação com o sistema.

## Sugestão de classes a serem criadas:

A modelagem do problema é parte do projeto, assim sugerimos a existência de algumas classes que são entendidas como essenciais, mas que não estão descritas em sua completude (sem menção aos métodos, por exemplo).

- Motorista
- Passageiro
- Viagem
- Local (equivalente a um ponto do trajeto)
- Avaliação

## Exemplo de interface do sistema

A interface com o usuário se dará por meio da console, através de menus de opções, mensagens informativas e pela leitura de dados. Um exemplo de menu é apresentado na [Figura 1](https://www.notion.so/8fbe76935fb940e39ae7fb68ae3a3ed6?pvs=21), em que o usuário poderá escolher umas das quatro opções ou ainda, selecionar a opção para sair o programa.

```bash
** CarONE-M **
1) Cadastrar um novo usuário
2) Cadastrar uma viagem
3) Buscar por carona
4) Avaliar uma viagem
5) Sair
Selecione uma opção:
```

*Figura 1 - Menu de opções do sistema.*

As mensagens e informações apresentadas podem variar, desde que as funcionalidades solicitadas estejam presentes e adequadamente implementadas.

## Observações importantes e considerações finais:

O programa deve ser implementado na linguagem Java, utilizando os fundamentos de orientação a objetos e os conceitos estudados na disciplina, ser modelado adequadamente e bem documentado. A entrega do trabalho deve ser feita pelo **Moodle**, na entrada especificada e observando-se a data limite para entrega.

O código entregue será avaliado de acordo com os seguintes critérios:

- Funcionamento do programa por meio de testes práticos que exercitam e exploram cada uma das características solicitadas;
- A fidelidade do programa quanto à descrição do enunciado;
- Aspectos relacionados a indentação, presença de comentários e legibilidade do código;
- Clareza na nomenclatura de variáveis;

Este trabalho deve ser desenvolvido em **duplas ou trios**, com os grupos sendo registrados previamente no sistema Moodle, observando durante o processo seguir as orientações contidas no documento “***[Orientações para Desenvolvimento de Trabalhos Práticos](https://www.notion.so/2024-01-PSI-Projeto-N2-f9af7f976ee84f7bb25e4f765718240a?pvs=21)***”. Todas as entregas serão analisadas em relação a plágios e o uso de ferramentas de inteligência artificial na sua elaboração.

A entrega deve conter os arquivos fontes com todas as classes do modelo e do programa principal, juntamente com quaisquer outros recursos que se fizerem necessários a compilação e execução do projeto. Por fim, um documento contendo os *prints* de casos de testes devidamente detalhados  deve ser encaminhado, completando assim os artefatos entregáveis do projeto. 

[OrientacoesParaDesenvolvimentoDeTrabalhosPraticos.pdf](https://prod-files-secure.s3.us-west-2.amazonaws.com/9764a85c-5c0f-47c1-a4e9-9f954283fb1a/eedba170-f51b-49e5-8df3-4a1f9545304c/OrientacoesParaDesenvolvimentoDeTrabalhosPraticos.pdf)
