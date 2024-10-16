## <h1 align = center>Challenger: conversor de moedas </h1>
</br>

![conversor de Moedas](https://github.com/user-attachments/assets/9449e375-1107-4273-8c24-36aa6002a3b2)

## Resumo do projeto

 Este é um projeto desenvolvido no Challenger - conversor de moedas proposto pela [Alura](https://www.alura.com.br/) e pelo programa [Oracle One](https://www.oracle.com/br/education/oracle-next-education/), programa de educação com objetivo de capacitar as pessoas a darem os primeiros passos no mundo da tecnologia ao mercado de trabalho por meio de empresas parceiras. O desafio é criar um conversor de moedas que se conecta com uma [API](https://www.exchangerate-api.com//) para oferecer taxas de câmbio atualizadas. O usuário tem a opção de testar conversões com algumas moedas suportadas pela API em uso. Além de conseguir visualizar o histórico com todas as conversões feitas durante o uso do programa. 

 ## ✔️ Técnicas e tecnologias utilizadas

- ``Java`` 
- ``JDK 21.0.4`` 
- ``Intellij`` 

## :hammer: Funcionalidades adicionadas ao projeto
 
- `Funcionalidade 1:` O usuário pode escolher entre pares de moedas para realizar diferentes conversões.
 
- `Funcionalidade 2:` Tem a opção de alternar entre as moedas selecionadas para ver o valor da taxa de câmbio em relação a outra moeda.

-  `Funcionalidade 3:` Poderá acessar todo o histórico de conversões feitas durante o uso do programa.

-  `Funcionalidade 4:` Atualizar o valor da taxa de câmbio.
-  `Funcionalidade 5:` Visualizar o histórico das conversões feitas.
-  `Funcionalidade 6:` Registrar informações adicionais ao log (opcional).

## Instruções de uso e alguns detalhes do projeto

- É um projeto para iniciantes em Java, tudo é feito via console, isso significa que terá que ter instalado uma IDE como [Intellij](https://www.jetbrains.com/pt-br/idea/download/?section=windows) (versão Community edition), [Eclipse](https://www.eclipse.org/downloads/) ou outra que suporte a linguagem Java e de preferência a [JDK 21.04](https://www.oracle.com/br/java/technologies/downloads/#jdk23-windows)(LTS) usada no projeto.
- É feita uma conexão com a API que fornece dados em tempo real de algumas moedas, neste projeto utilizo apenas 10 moedas. Digite apenas as moedas que aparece disponíveis no console, outras não funcionarão.
- Ao escolher uma paridade será apresentado outras opções como atualizara taxa de câmbio, terá diferença caso a API retorne alguma alteração, mas não ocorre com frequência.
- Outra opção é inverter a moeda base, exemplo, se escolheu a paridade USD/BRL, que atualmente no momento que escrevo, cada 1 USD equivale a 5,69 BRL, ao alterar para BRL/USD verá que cada 1 BRL equivale a 0,18 centavos de dólar.
- Fique a vontade para digitar valores e ver a conversão entre as paridades escolhidas.
- A opção de visualizar histórico aparecerá apenas se tiver feita alguma conversão. Ao digitar historico no console será exibido as conversões realizadas recentemente e alguns dados adicionais.
- Ao digitar sair será apresentado a opção de adicionar ou não informações ao log. Ao digitar S será exibido alguns campos adicionais onde poderá escrever ações e detalhes adicionais. Se digitar N, o log será criado e o programa finalizado.
- Obs: Nesta etapa do log está capturando algumas informações da máquina do usuário, especificamente: O nome do usuário e o sistema operacional utilizado, funciona possivelmente apenas no Windows. Esse arquivo é reescrito cada vez que o programa é gerado e pode ser deletado na própria interface da IDE que está utilizando. É apenas uma simulação de log, usando java da forma pura.

## Trajetória durante o desenvolvimento

Apesar de ser um projeto simples, eu demorei mais tempo do que o esperado. Muitas das coisas aprendidas acabaram sendo colocadas nesse projeto, o que era o intuito, mas muitas foram sendo feitas durante as melhorias implementadas conforme tentava melhorar o código do projeto, principalmente refatoração, organização em pacotes, conceitos de encapsulamento, ArrayList, herança, polimorfismo, um exemplo de polimorfismo ocorre na classe ConversaoMoedas, há dois métodos que são chamados por diferentes instâncias e utilizam diferentes comportamentos baseados no contexto de execução. É feito muita coisa nestes processos, estabelecer conexão com a API através de uma requisição Http, como uso da classe Record para deserializar o arquivo Json para conseguir obter os dados que necessitava em formato String, com o auxílio da biblioteca externa Gson. Foi feito uso do Map para buscar através de chaves todos os dados que queria obter dos tipos especificados.
Comecei a usar nesse projeto o conceito de Enum, sendo uma forma mais organizada e legível de conseguir manter variáveis importantes, e também algumas ações como pode ser visto no projeto, e que por ventura facilite a manutenção ou alteração de algo de forma muita mais fácil. Por fim, foi feita a implementação da exibição temporária do histórico das conversões realizadas, e que poderá ser armazenada em arquivo após a finalização do programa, ao ser digitado a palavra sair.

Foram poucas coisas, mas passei a maior parte do tempo tentando consertar erros em várias etapas do desenvolvimento deste mini projeto do que realmente adcionando algo novo, foi certamente na partes que tive mais dificuldades, seja o problema com o scanner, principalmente quando precisasse limpar o buffer de entrada, quando simplesmente pulava o nextLine(), quando deveria travar até algo ser digitado, mas não era o que acontecia. Ou outros problemas que tive dificuldade. A maior parte foi tranquila, mais pro final do projeto, principalmente quando tentei adicionar coisas novas ou como a parte do log, acabei demorando mais do que o esperado para concluir, de um projeto que deveria ser simples e rápido.

