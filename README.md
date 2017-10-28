# problema-reuniao-de-negocios

#Descrição

Considere um ambiente de reunião que chamaremos de Sala. A Sala tem capacidade para até 5 (cinco) pessoas. Isto é, quando a Sala atingir a capacidade máxima, uma Pessoa somente poderá entrar se alguem que esteja dentro da Sala sair.

As Pessoas, homens e mulheres, ao entrar na Sala irão fazer os contatos e trocar cartões de visita (todas as Pessoas tem cartão de visita). Ao atingir a meta de 3 (três) encontros (trocar 3 cartões de visita) a Pessoa pode ir embora e sair da Sala. Entre um encontro e outro a pessoa pode descansar e aproveitar da comida e bebida.

No entanto, pela política de igualdade de gênero na empresa, qualquer pessoa, homem ou mulher, deve obter, no mínimo, um cartão de cada sexo. Isto é, cada pessoa, independente de ser homem ou mulher, deve receber, pelo menos, um cartão de outro homem e um cartão de outra mulher.

#Implementação     

O trabalho prático consiste em escrever um programa em C, C++, Java ou Python para simular o problema. Não precisa fazer interface gráfica. 

O programa deve implementar a classe Pessoa() concorrente que tentará entrar na classe Sala() com capacidade de 5 (cinco) pessoas. Caso a Sala() esteja cheia ele vai descansar (wait()) esperando alguém sair.

Deverá haver uma classe que vai criar Pessoas de sexo aleatório a cada 2 (dois) segundos. Para o programa executar em tempo razoável (2 min) sugerimos criar 20 Pessoas. Ao final de 2 min o programa deve ser interrompido (podem ficar pessoas dentro da sala que não conseguiram sair).

A classe Passoa(), após entrar na Sala(), vai tentar fazer contato com uma outra Pessoa para trocar o cartão de visita (acordando qualquer outra pessoa). O encontro e a troca de cartão tem a duração de 2 (dois) segundos. Após trocar o cartão, a Pessoa() vai descansar (wait). Cada Pessoa vai tentar trocar cartões por 3 (três) vezes consecutivas, descansando definitivamente após tentar as três vezes (wait()). Lembre-se, a exclusão mútua é apenas para as duas pessoas que estão trocando cartão, vários pares poderão trocar cartão simultaneamente.

Se após essas três tentativas conseguir a combinação de cartões necessária, a Pessoa() pode sair da Sala() e permitir a entrada de outra Pessoa. Ao sair, a Pessoa avisa aos que querem entrar.

Caso não consiga todos os cartões necessários, a Pessoa() vai descansar (wait()), esperando ser acordado por alguem que esteja entrando e possua o cartão que está faltando.

Ao final da execução imprimir um relatório com as seguintes informações:
-Listar todas as Pessoas e mostrar os cartões que cada um conseguiu e o tempo que ficou na Sala. Por exemplo: Pessoa(4) [H] {20 seg} : M M H 
-Calcular o tempo médio na fila de entrada e o tempo médio dentro da Sala.
