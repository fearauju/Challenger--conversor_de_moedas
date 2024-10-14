
![conversor de Moedas](https://github.com/user-attachments/assets/9449e375-1107-4273-8c24-36aa6002a3b2)

## Resumo do projeto

 Este é um projeto desenvolvido no Challenger - conversor de moedas proposto pela [Alura](https://www.alura.com.br/). O desafio é criar um conversor de moedas que se conecta com uma [API](https://www.exchangerate-api.com//) para oferecer taxas de câmbio atualizadas. O usuário tem a opção de testar conversões com algumas moedas suportadas pela API em uso.
 Além de conseguir visualizar o histórico com todas as conversões feitas durante o uso do programa.

 ## ✔️ Técnicas e tecnologias utilizadas

- ``Java``
- ``JDK 21.0.4``
- ``Intellij``

## :hammer: Funcionalidades adicionadas ao projeto
 
- `Funcionalidade 1:` O usuário pode escolher entre pares de moedas para realizar diferentes conversões.
 
- `Funcionalidade 2:` Tem a opção de alternar entre as moedas selecionadas para ver o valor da taxa de câmbio em relação a outra moeda.

-  `Funcionalidade 3:` Poderá acessar todo o histórico de conversões feitas durante o uso do programa.
  
-  `Funcionalidade 4:` Acessar o log de registros das atividades feitas.


## Trajetória durante o desenvolvimento

Apesar de ser um projeto simples, eu demorei mais tempo do que o esperado. Muitas das coisas aprendidas acabaram sendo colocadas nesse projeto, o que era o intuito, mas muitas foram sendo feitas durante as melhorias implementadas conforme tentava melhorar o código do projeto, principalmente refatoração, conceitos de 
encapsulamento, herança, polimorfismo, um exemplo de polimorfismo ocorre na classe ConversaoMoedas, há dois métodos que são chamados por diferentes instâncias e utilizam diferentes comportamentos baseados no contexto de execução. É feito muita coisa nestes processos, estabelecer conexão com a API através de uma requisição Http,
como uso da classe Record como deserializar o arquivo Json para conseguir obter os dados que necessitava em formato String, e para isso também foi feito uso da biblioteca externa Gson.

## 📁 Acesso ao projeto

Você pode acessar o site clicando [aqui]().
