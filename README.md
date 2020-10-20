## Resumo

Este artigo descreve uma alternativa de solução para o segundo problema proposto na disciplina de Algoritmos e Estruturas de Dados II do 3º semestre, que trata de um mecanismo de conexões entre tubos, no qual devemos descobrir, através do caminho mais suscetível, por onde sai mais bolinhas ao jogarmos várias delas no início dos tubos. É apresentado a lógica por trás da máquina e o funcionamento da solução que realiza o caminhamento entre as conexões. Em seguida, são expostos os resultados para oito casos analisados e é avaliada a eficiência do algoritmo.

## Introdução ao Problema

A Fantástica Máquina de Tubos e Bolinhas do enigmático senhor Sibério é a grande atração deste ano na feira anual da vila de Fragoletto. A máquina é construída de longos tubos de metal por onde escorregam bolinhas e de vez em quando há um desvio para que a bolinha vá para outro tubo. Assim, a bolinha pode ir de um tubo para o outro várias vezes e sair em um tubo inesperado.

O senhor Sibério ganha dinheiro através de apostas com o público, onde as pessoas colocam uma bolinha em um tubo e depois tentam adivinhar por qual tubo ela sairá. Sibério reconecta sua máquina todas as noites alterando os desvios entre os tubos, para que cada novo dia traga novas oportunidades para ganhar dinheiro. 

Sua namorada trabalha na barraca ao lado do senhor Sibério e ela descobre que pode enxergar pela janela o projeto com a programação da máquina, que consiste em uma longa lista de números (*Imagem 1*) que usaremos como exemplo.

Para interpretarmos a lista devemos seguir três princípios:

* O primeiro número indica o número de tubos existentes;
* O segundo número informa o comprimento dos tubos;
* Em seguida, vem uma lista de grupos de 4 números para representar as conexões, que como demonstração usaremos: **a b c d**.
Isso significa que o tubo **a** tem uma ligação na altura **b**, que leva até o tubo **c** na altura **d**.

Você vê a lista e desenha os tubos com todas suas conexões (*Imagem 2*). Agora você quer ganhar dinheiro vendendo para os apostadores os seus conhecimentos sobre a máquina, mas constata que é complicado descobrir por onde a bolinha vai sair quando é colocada em um tubo qualquer.

<p align="center">
    <img src="assets/Example representation.png">
<p align="center">

Conclui-se que o desenho é complicado de interpretar, logo você decide elaborar um programa para resolver o problema. O programa deve ser capaz de ler a longa lista de números que consistem no esquema da máquina e descobrir:

1. Qual o tubo por onde saem mais bolinhas? (considerando que jogaremos uma bolinha em cada tubo)
2. Quantas bolinhas saem por esse tubo?

No caso do exemplo acima, as informações encontradas devem ser sucessivamente:
1. Tubo 0
2. 5 bolinhas

Para resolver o desafio analisaremos o problema proposto, e em seguida, uma possível solução funcional, bem como suas características e dificuldades encontradas. Em seguida os resultados obtidos dos casos serão apresentados, bem como as conclusões obtidas no decorrer do trabalho.

## Solução

Depois de considerar o desafio, podemos supor que a lista de números que organiza a estrutura da máquina poderia ser consumida e analisada ao mesmo tempo, porém como as conexões dos tubos estão em ordem aleatória na lista, esta solução é inviável já que não queremos ler o arquivo mais de uma vez. Com isso podemos concluir que devemos consumir a lista e armazenar as informações das conexões em alguma estrutura de dados. Uma opção seria usar grafos para criar os caminhos completos dos tubos a partir de cada conexão. Já outra possibilidade seria utilizar uma matriz, visto que temos o comprimento e a quantidade dos tubos, somente para guardar a referência dos desvios nos tubos.

Foi decidido utilizar a matriz para a organização dos dados, mas como as referências das conexões dos tubos dependem de dois valores para serem representados, teremos que criar um objeto caminho, que consiste em um atributo linha e um atributo coluna, para então declarar uma matriz de caminhos.

Foi usado o *Scanner* do Java para ler um caso no arquivo de texto, usando os dois primeiros números para instanciar a matriz, sendo a quantidade de tubos as colunas e o comprimento dos tubos as linhas, levando em consideração que o comprimento começa em zero, logo devemos adicionar mais uma linha. Após isso, o *Scanner* lê os números de quatro em quatro, que constituem os dados de um único caminho, armazenando no índice das coordenadas onde começa a conexão a referência para onde ela vai.

Uma vez que temos constituídas na matriz todas as conexões dos tubos, o próximo passo é simular o jogo colocando uma bolinha em cada tubo, para isso iremos percorrer cada coluna da matriz de cima para baixo, para ver em qual tubo paramos quando chegarmos no final do comprimento. Quando um índice de uma linha estiver nulo, acessamos a linha de baixo pois não há nenhum desvio, já ao contrário, quando um índice não for nulo ele terá a referência para o final desvio para continuar o caminhamento. Foi criado um arranjo com o mesmo tamanho da quantidade de tubos para armazenar o incremento das bolinhas que saíram por cada tubo.

Este algoritmo que simula jogar uma bolinha em cada tubo pode funcionar da seguinte forma:

```
PROGRAM PlayGame()

  numberOfChildren = list[0]
  numberOfElements = list[1]

  linha = 0

  FOR coluna = 0; coluna < qtdColunas
    
    ENQUANTO linha != qtdLinhas FAÇA

      SE matriz[linha][coluna] == null

        linha += 1

      SE NÃO

        linhaAux = matriz[linha][coluna].linhaRef
        coluna = matriz[linha][coluna].colunaRef
        linha = linhaAux
    FIM

    bolasTotal[coluna] += 1
    linha = 0
  FIM
FIM
```

Além do método de análise principal, foi desenvolvido também um simples menu para que o usuário escolha qual caso queira testar. Esse menu chama outro método chamado caseTest, (passando o nome do arquivo com o caso escolhido) que por sua vez, administra o teste, calculando o tempo de execução do método que consome o arquivo de texto para criar os caminhos e do outro método que simula o jogo.

## Resultados

Depois de implementar o algoritmo acima na linguagem Java, os casos foram rodados cinco vezes para tirar a média do tempo de execução. Segue abaixo os resultados:

Caso|Tamanho do Caso (colunas * linhas)|Tubo que sai mais bolinhas|Quantidade de bolinhas do tubo|Tempo de execução (milissegundos)
|---|---|---|---|---|
caso-1|800|10|5|1.2
caso-2|5000|36|18|3.6
caso-3|50000|72|85|23.6
caso-4|50000|199|82|92.4
caso-5|1000000|180|139|212.8
caso-6|5000000|885|250|465.1
caso-7|20000000|680|546|2248.3
caso-8|100000000|2195|1565|14635.1

## Conclusões

As primeiras indagações a respeito do desafio proposto, mesmo não oferecendo um resultado, contribuíram bastante para o entendimento do problema e abriu caminho para uma possível solução funcional. A partir disso, concluímos que geralmente levantar hipóteses para a solução de um problema e tentar achar equívocos nelas pode ser muito mais vantajoso do que simplesmente começar a implementando.

A forma apresentada de armazenar os desvios nos tubos se mostrou bastante simples, embora eu gostaria testar e discutir outras estruturas de dados para representar estes desvios, como por exemplo um dicionário. Contudo, a solução adotada possui uma notação **O(n * m)**, onde *n* é referente a quantidade de tubos e *m* ao comprimento dos tubos, pois no pior dos casos todos os tubos possuem desvios em todos os níveis dos seus comprimentos, logo o tempo de execução cresce proporcional a essas duas variáveis.