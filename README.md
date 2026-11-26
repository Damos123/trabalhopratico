# TrabalhoPratico.java explicado linha por linha

Este README explica o programa Java **TrabalhoPratico.java**. O programa lê os nomes das pessoas, os nomes das redes sociais e o tempo diário gasto em cada rede.

## Como executar

```powershell
javac TrabalhoPratico.java
java TrabalhoPratico
```

Insira primeiro os nomes das pessoas separados por vírgulas, depois as redes sociais separadas por vírgulas e, por fim, uma linha de números para cada pessoa.

Exemplo:

```text
Ana, Joao
Facebook, Instagram, TikTok
30 60 20
45 25 100
```

## 1. Importações — linhas 1 a 3

| Linha | Código | Explicação |
|---:|---|---|
| 1 | `import java.util.ArrayList;` | Importa `ArrayList`, uma lista que pode aumentar ou diminuir durante a execução. |
| 2 | `import java.util.Locale;` | Importa `Locale`, usado para definir a convenção dos números apresentados. |
| 3 | `import java.util.Scanner;` | Importa `Scanner`, usado para ler dados escritos no terminal. |

## 2. Classe e início do programa — linhas 5 a 8

| Linha | Código | Explicação |
|---:|---|---|
| 5 | `public class TrabalhoPratico {` | Declara a classe pública chamada `TrabalhoPratico`. O nome deve coincidir com o nome do ficheiro. |
| 6 | `public static void main(String[] args) {` | Método principal executado pelo Java. `args` pode receber argumentos da linha de comando. |
| 7 | `Locale.setDefault(Locale.US);` | Usa a convenção americana dos números, por exemplo, ponto como separador decimal. |
| 8 | `Scanner input = new Scanner(System.in);` | Cria o objeto `input` para ler dados da entrada padrão, normalmente o teclado. |

## 3. Leitura dos dados — linhas 10 a 19

| Linha | Código | Explicação |
|---:|---|---|
| 10 | `String[] people = input.nextLine().trim().split("\\s*,\\s*");` | Lê a primeira linha, elimina espaços nas extremidades e divide o texto pelas vírgulas. |
| 11 | `String[] networks = input.nextLine().trim().split("\\s*,\\s*");` | Lê a segunda linha e cria o array com os nomes das redes sociais. |
| 12 | `int[][] time = new int[people.length][networks.length];` | Cria uma tabela com uma linha por pessoa e uma coluna por rede. |
| 14 | `for (int p = 0; p < people.length; p++) {` | Percorre os índices das pessoas, começando em `0`. |
| 15 | `for (int r = 0; r < networks.length; r++) {` | Para cada pessoa, percorre os índices das redes sociais. |
| 16 | `time[p][r] = input.nextInt();` | Lê um número inteiro e guarda-o na posição da pessoa `p` e da rede `r`. |
| 17 | `}` | Fecha o ciclo das redes sociais. |
| 18 | `}` | Fecha o ciclo das pessoas. |
| 19 | `input.close();` | Fecha o objeto `Scanner`, libertando os recursos associados à leitura. |

Exemplo da tabela criada:

| Pessoa | Facebook | Instagram | TikTok |
|---|---:|---:|---:|
| Ana | 30 | 60 | 20 |
| Joao | 45 | 25 | 100 |

Nesta tabela, `time[0][1]` corresponde aos 60 minutos de Ana no Instagram.

## 4. Tabela de utilização — linhas 21 a 32

| Linha | Código | Explicação |
|---:|---|---|
| 21 | `// b) tabela` | Comentário que identifica o exercício b. |
| 22 | `System.out.println("\nb) Social Media Usage (minutes/day)");` | Escreve uma linha vazia e o título da tabela. |
| 23 | `System.out.printf("%-12s", "");` | Escreve uma célula vazia com largura mínima de 12 caracteres. |
| 24 | `for (String r : networks) System.out.printf("%12s", r);` | Percorre todas as redes e escreve cada nome como coluna alinhada à direita. |
| 25 | `System.out.println();` | Termina o cabeçalho da tabela. |
| 26 | `for (int p = 0; p < people.length; p++) {` | Percorre todas as pessoas para escrever uma linha por pessoa. |
| 27 | `System.out.printf("%-12s", people[p]);` | Escreve o nome da pessoa alinhado à esquerda em uma célula de 12 caracteres. |
| 28 | `for (int r = 0; r < networks.length; r++) {` | Percorre as redes dessa pessoa. |
| 29 | `System.out.printf("%12d", time[p][r]);` | Escreve o tempo inteiro, alinhado à direita. |
| 30 | `}` | Fecha o ciclo das redes. |
| 31 | `System.out.println();` | Termina a linha da tabela. |
| 32 | `}` | Fecha o ciclo das pessoas. |

## 5. Tempo total e média — linhas 34 a 40

| Linha | Código | Explicação |
|---:|---|---|
| 34 | `// c) tempo total e media` | Comentário que identifica o exercício c. |
| 35 | `System.out.println("\nc) Daily usage");` | Escreve o título da secção. |
| 36 | `for (int p = 0; p < people.length; p++) {` | Percorre todas as pessoas. |
| 37 | `int total = sum(time[p]);` | Soma os tempos da pessoa usando o método `sum`. |
| 38 | `System.out.printf("%-12s: %4d min (%5.1f min average/network)%n",` | Começa a definição do formato de apresentação do resultado. |
| 39 | `people[p], total, (double) total / networks.length);` | Mostra o nome, o total e a média por rede. |
| 40 | `}` | Fecha o ciclo das pessoas. |

`(double) total` converte o total em número decimal antes da divisão. Assim, por exemplo, `100 / 3` produz `33.333`, em vez de ser um cálculo inteiro.

## 6. Utilizador mais ativo em cada rede — linhas 42 a 56

| Linha | Código | Explicação |
|---:|---|---|
| 42 | `// d) rede mais usada por rede` | Comentário que identifica o exercício d. |
| 43 | `System.out.println("\nd) Most active Users");` | Escreve o título da secção. |
| 44 | `for (int r = 0; r < networks.length; r++) {` | Percorre cada rede. |
| 45 | `int max = 0;` | Inicializa o maior tempo dessa rede com zero. |
| 46 | `for (int p = 0; p < people.length; p++) {` | Percorre todas as pessoas dessa rede. |
| 47 | `if (time[p][r] > max) max = time[p][r];` | Atualiza `max` se encontrar um tempo maior. |
| 48 | `}` | Fecha o ciclo usado para encontrar o máximo. |
| 49 | `System.out.printf("%-12s: ", networks[r]);` | Escreve o nome da rede antes da lista de utilizadores. |
| 50 | `for (int p = 0; p < people.length; p++) {` | Percorre novamente todas as pessoas. |
| 51 | `if (time[p][r] == max && max > 0) {` | Verifica se a pessoa tem o máximo e se o máximo é maior que zero. |
| 52 | `System.out.print("\"" + people[p] + "\" ");` | Escreve o nome da pessoa entre aspas. |
| 53 | `}` | Fecha a condição. |
| 54 | `}` | Fecha o ciclo das pessoas. |
| 55 | `System.out.println();` | Termina a linha da rede atual. |
| 56 | `}` | Fecha o ciclo das redes. |

Em caso de empate, o programa mostra todas as pessoas que têm o máximo.

## 7. Pessoas acima da média em todas as redes — linhas 58 a 73

| Linha | Código | Explicação |
|---:|---|---|
| 58 | `// e) acima da media em todas as redes` | Comentário que identifica o exercício e. |
| 59 | `System.out.println("\ne) Users consistently above average");` | Escreve o título da secção. |
| 60 | `ArrayList<String> above = new ArrayList<String>();` | Cria uma lista vazia para guardar os utilizadores encontrados. |
| 61 | `for (int p = 0; p < people.length; p++) {` | Percorre todas as pessoas. |
| 62 | `boolean result = true;` | Assume inicialmente que esta pessoa está acima da média em todas as redes. |
| 63 | `for (int r = 0; r < networks.length; r++) {` | Percorre todas as redes dessa pessoa. |
| 64 | `int total = 0;` | Reinicia a soma dos tempos da rede atual. |
| 65 | `for (int p2 = 0; p2 < people.length; p2++) {` | Percorre todas as pessoas para somar a rede atual. |
| 66 | `total += time[p2][r];` | Adiciona o tempo de cada pessoa à variável `total`. |
| 67 | `}` | Fecha o ciclo usado para calcular o total da rede. |
| 68 | `if (time[p][r] <= (double) total / people.length) result = false;` | Se a pessoa não estiver acima da média da rede, altera `result` para `false`. |
| 69 | `}` | Fecha o ciclo das redes. |
| 70 | `if (result) above.add(people[p]);` | Se continuar como `true`, adiciona o nome à lista. |
| 71 | `}` | Fecha o ciclo das pessoas. |
| 72 | `if (above.isEmpty()) System.out.println("none");` | Se a lista estiver vazia, escreve `none`. |
| 73 | `else for (String name : above) System.out.println(name);` | Caso contrário, escreve cada nome encontrado numa linha. |

A comparação usa o valor estritamente maior: uma pessoa igual à média não é considerada acima dela.

## 8. Rede mais usada por pessoa — linhas 75 a 86

| Linha | Código | Explicação |
|---:|---|---|
| 75 | `// f) rede mais usada por pessoa` | Comentário que identifica o exercício f. |
| 76 | `System.out.println("\nf) Most used social network");` | Escreve o título da secção. |
| 77 | `for (int p = 0; p < people.length; p++) {` | Percorre todas as pessoas. |
| 78 | `int max = max(time[p]);` | Obtém o maior tempo entre as redes dessa pessoa. |
| 79 | `System.out.printf("%-12s: ", people[p]);` | Escreve o nome da pessoa. |
| 80 | `for (int r = 0; r < networks.length; r++) {` | Percorre todas as redes dessa pessoa. |
| 81 | `if (time[p][r] == max) {` | Verifica se o tempo desta rede é igual ao máximo. |
| 82 | `System.out.print("[" + networks[r] + "] ");` | Escreve o nome da rede entre parênteses retos. |
| 83 | `}` | Fecha a condição. |
| 84 | `}` | Fecha o ciclo das redes. |
| 85 | `System.out.println();` | Termina a linha da pessoa. |
| 86 | `}` | Fecha o ciclo das pessoas. |

## 9. Percentagem de utilização — linhas 88 a 101

| Linha | Código | Explicação |
|---:|---|---|
| 88 | `// g) percentagens` | Comentário que identifica o exercício g. |
| 89 | `System.out.println("\ng) Percentage of use");` | Escreve o título da secção. |
| 90 | `System.out.printf("%-12s", "");` | Escreve uma célula vazia para alinhar o cabeçalho. |
| 91 | `for (String r : networks) System.out.printf("%12s", r);` | Escreve os nomes das redes como cabeçalhos das colunas. |
| 92 | `System.out.println();` | Termina o cabeçalho. |
| 93 | `for (int p = 0; p < people.length; p++) {` | Percorre todas as pessoas. |
| 94 | `int total = sum(time[p]);` | Soma o tempo total da pessoa atual. |
| 95 | `System.out.printf("%-12s", people[p]);` | Escreve o nome da pessoa. |
| 96 | `for (int r = 0; r < networks.length; r++) {` | Percorre todas as redes dessa pessoa. |
| 97 | `if (time[p][r] == 0) System.out.printf("%12s", "");` | Deixa vazia a célula cujo tempo é zero. |
| 98 | `else System.out.printf("%11.2f%%", 100.0 * time[p][r] / total);` | Calcula e apresenta a percentagem com duas casas decimais. |
| 99 | `}` | Fecha o ciclo das redes. |
| 100 | `System.out.println();` | Termina a linha da pessoa. |
| 101 | `}` | Fecha o ciclo das pessoas. |

O cálculo é:

```text
100 × tempo nessa rede ÷ tempo total da pessoa
```

Em `printf`, `%%` é usado para escrever literalmente o símbolo `%`.

## 10. Perfis dos utilizadores — linhas 103 a 127

| Linha | Código | Explicação |
|---:|---|---|
| 103 | `// h) perfis` | Comentário que identifica o exercício h. |
| 104 | `System.out.println("\nh) Users profile");` | Escreve o título da secção. |
| 105 | `System.out.printf("%-16s%-16s%-16s%-16s%n",` | Começa o formato de uma linha com quatro colunas. |
| 106 | `"Moderate", "Regular", "Intensive", "Very Intensive");` | Escreve os nomes dos quatro perfis. |
| 107 | `System.out.println("------------|------------|------------|------------");` | Escreve uma linha separadora entre títulos e nomes. |
| 108 | `ArrayList<ArrayList<String>> profiles = new ArrayList<ArrayList<String>>();` | Cria uma lista que contém quatro listas de nomes. |
| 109 | `for (int i = 0; i < 4; i++) profiles.add(new ArrayList<String>());` | Adiciona uma lista vazia para cada perfil. |
| 110 | `for (int p = 0; p < people.length; p++) {` | Percorre todas as pessoas. |
| 111 | `int total = sum(time[p]);` | Calcula o tempo total da pessoa. |
| 112 | `int profile = total < 90 ? 0 : total <= 180 ? 1 :` | Escolhe o perfil usando uma condição ternária encadeada. |
| 113 | `total <= 300 ? 2 : 3;` | Termina a escolha: até 300 é `2`; acima é `3`. |
| 114 | `profiles.get(profile).add(people[p]);` | Adiciona o nome à lista correspondente ao perfil. |
| 115 | `}` | Fecha o ciclo das pessoas. |
| 116 | `int lines = 0;` | Inicializa o número máximo de linhas da tabela de perfis. |
| 117 | `for (ArrayList<String> list : profiles) {` | Percorre cada uma das quatro listas. |
| 118 | `if (list.size() > lines) lines = list.size();` | Guarda o maior número de nomes encontrado numa lista. |
| 119 | `}` | Fecha o ciclo das listas. |
| 120 | `for (int line = 0; line < lines; line++) {` | Percorre as posições existentes nas colunas. |
| 121 | `for (int profile = 0; profile < 4; profile++) {` | Percorre os quatro perfis. |
| 122 | `String name = line < profiles.get(profile).size()` | Seleciona uma pessoa caso exista nesta posição. |
| 123 | `? profiles.get(profile).get(line) : "";` | Obtém o nome ou usa uma string vazia. |
| 124 | `System.out.printf("%-16s", name);` | Escreve o nome numa coluna com 16 caracteres. |
| 125 | `}` | Fecha o ciclo dos perfis. |
| 126 | `System.out.println();` | Termina a linha da tabela. |
| 127 | `}` | Fecha o ciclo das linhas. |

Os limites são:

- `Moderate`: menos de 90 minutos.
- `Regular`: entre 90 e 180 minutos.
- `Intensive`: entre 181 e 300 minutos.
- `Very Intensive`: mais de 300 minutos.

## 11. Total por rede e histograma — linhas 129 a 165

| Linha | Código | Explicação |
|---:|---|---|
| 129 | `// i) redes ordenadas e histograma` | Comentário que identifica o exercício i. |
| 130 | `int[] totals = new int[networks.length];` | Cria um array para guardar o tempo total de cada rede. |
| 131 | `int[] users = new int[networks.length];` | Cria um array para contar quantas pessoas usam cada rede. |
| 132 | `int grandTotal = 0;` | Inicializa o tempo total de todas as redes. |
| 133 | `for (int r = 0; r < networks.length; r++) {` | Percorre todas as redes. |
| 134 | `for (int p = 0; p < people.length; p++) {` | Percorre todas as pessoas. |
| 135 | `totals[r] += time[p][r];` | Soma o tempo da pessoa atual à rede atual. |
| 136 | `if (time[p][r] > 0) users[r]++;` | Conta esta pessoa como utilizadora da rede se o tempo for maior que zero. |
| 137 | `}` | Fecha o ciclo das pessoas. |
| 138 | `grandTotal += totals[r];` | Adiciona o total da rede ao total geral. |
| 139 | `}` | Fecha o ciclo das redes. |
| 140 | `int[] order = new int[networks.length];` | Cria um array que vai guardar os índices das redes já ordenadas. |
| 141 | `for (int i = 0; i < networks.length; i++) order[i] = i;` | Coloca os índices das redes em `order`. |
| 142 | `for (int i = 0; i < networks.length - 1; i++) {` | Percorre as posições disponíveis para ordenar. |
| 143 | `for (int j = i + 1; j < networks.length; j++) {` | Compara cada posição seguinte com a posição atual. |
| 144 | `int a = order[i], b = order[j];` | Guarda os índices das duas redes que estão a ser comparadas. |
| 145 | `boolean swap = totals[a] < totals[b]` | Começa a verificar se `a` deve aparecer depois de `b`. |
| 146 | `|| totals[a] == totals[b] && users[a] < users[b]` | Se os totais forem iguais, compara o número de utilizadores. |
| 147 | `|| totals[a] == totals[b] && users[a] == users[b]` | Se total e utilizadores forem iguais, prepara a comparação alfabética. |
| 148 | `&& networks[a].compareTo(networks[b]) > 0;` | Troca se o nome `a` vier alfabeticamente depois do nome `b`. |
| 149 | `if (swap) {` | Se uma das condições anteriores tiver sido verdadeira, troca as posições. |
| 150 | `int temp = order[i];` | Guarda temporariamente o índice da posição `i`. |
| 151 | `order[i] = order[j];` | Coloca o índice de `j` na posição `i`. |
| 152 | `order[j] = temp;` | Coloca o índice guardado na posição `j`. |
| 153 | `}` | Fecha a condição de troca. |
| 154 | `}` | Fecha o ciclo interno. |
| 155 | `}` | Fecha o ciclo externo. |
| 156 | `System.out.println("\ni) Use of social networks");` | Escreve o título da secção. |
| 157 | `for (int r : order) {` | Percorre as redes pela ordem calculada. |
| 158 | `double percent = grandTotal == 0 ? 0 : 100.0 * totals[r] / grandTotal;` | Calcula a percentagem da rede no total geral. |
| 159 | `int stars = (int) Math.round(percent / 5.0);` | Converte 5% de utilização em um asterisco e arredonda o resultado. |
| 160 | `System.out.printf("%-12s|", networks[r]);` | Escreve o nome da rede seguido de uma barra. |
| 161 | `for (int k = 0; k < stars; k++) System.out.print("*");` | Escreve um asterisco por cada bloco de 5%. |
| 162 | `System.out.printf(" %.2f%%%n", percent);` | Escreve a percentagem com duas casas decimais e muda de linha. |
| 163 | `}` | Fecha o ciclo das redes ordenadas. |
| 164 | `System.out.println("            +----+----+----+----+");` | Desenha a linha inferior do histograma. |
| 165 | `System.out.println("          %     25   50   75 100");` | Escreve a escala do histograma. |

## 12. Utilizadores mais equilibrados — linhas 167 a 186

| Linha | Código | Explicação |
|---:|---|---|
| 167 | `// j) utilizadores mais equilibrados` | Comentário que identifica o exercício j. |
| 168 | `int smallestDifference = Integer.MAX_VALUE;` | Inicializa a menor diferença com o maior valor inteiro possível. |
| 169 | `ArrayList<String> balanced = new ArrayList<String>();` | Cria uma lista vazia para os utilizadores mais equilibrados. |
| 170 | `for (int p = 0; p < people.length; p++) {` | Percorre todas as pessoas. |
| 171 | `int difference = max(time[p]) - min(time[p]);` | Calcula a diferença entre o maior e o menor tempo da pessoa. |
| 172 | `if (difference < smallestDifference) {` | Verifica se esta pessoa é mais equilibrada que todas as anteriores. |
| 173 | `smallestDifference = difference;` | Guarda a nova menor diferença. |
| 174 | `balanced.clear();` | Remove os utilizadores encontrados anteriormente. |
| 175 | `balanced.add(people[p]);` | Adiciona a nova pessoa à lista. |
| 176 | `} else if (difference == smallestDifference) {` | Verifica se existe empate com a menor diferença. |
| 177 | `balanced.add(people[p]);` | Adiciona mais uma pessoa equilibrada. |
| 178 | `}` | Fecha a condição do cálculo de equilíbrio. |
| 179 | `}` | Fecha o ciclo das pessoas. |
| 180 | `System.out.println("\nj) Most balanced users");` | Escreve o título da secção. |
| 181 | `System.out.print("{");` | Escreve a abertura das chavetas. |
| 182 | `for (int i = 0; i < balanced.size(); i++) {` | Percorre todos os utilizadores mais equilibrados. |
| 183 | `if (i > 0) System.out.print(", ");` | Escreve uma vírgula antes de todos os nomes, exceto o primeiro. |
| 184 | `System.out.print("\"" + balanced.get(i) + "\"");` | Escreve o nome entre aspas. |
| 185 | `}` | Fecha o ciclo dos nomes. |
| 186 | `System.out.println("}");` | Escreve a chave de fecho e muda de linha. |

## 13. Métodos auxiliares — linhas 189 a 205

| Linha | Código | Explicação |
|---:|---|---|
| 189 | `static int sum(int[] values) {` | Declara um método estático que recebe um array e devolve um inteiro. |
| 190 | `int result = 0;` | Inicializa o total em zero. |
| 191 | `for (int value : values) result += value;` | Percorre todos os valores e adiciona-os a `result`. |
| 192 | `return result;` | Devolve o total calculado. |
| 193 | `}` | Fecha o método `sum`. |
| 195 | `static int max(int[] values) {` | Declara um método estático que encontra o maior valor. |
| 196 | `int result = values[0];` | Usa o primeiro elemento como valor inicial. |
| 197 | `for (int value : values) if (value > result) result = value;` | Substitui `result` sempre que encontra um valor maior. |
| 198 | `return result;` | Devolve o maior valor. |
| 199 | `}` | Fecha o método `max`. |
| 201 | `static int min(int[] values) {` | Declara um método estático que encontra o menor valor. |
| 202 | `int result = values[0];` | Usa o primeiro elemento como valor inicial. |
| 203 | `for (int value : values) if (value < result) result = value;` | Substitui `result` sempre que encontra um valor menor. |
| 204 | `return result;` | Devolve o menor valor. |
| 205 | `}` | Fecha o método `min`. |
| 206 | `}` | Fecha a classe `TrabalhoPratico`. |

## Conceitos importantes

### O que é um array

Um array é uma variável que guarda vários valores numa única estrutura.

```java
String[] nomes = {"Ana", "Joao"};
```

Os índices começam em zero:

- `nomes[0]` = `"Ana"`
- `nomes[1]` = `"Joao"`

### O que é um array bidimensional

Um array bidimensional guarda valores organizados em linhas e colunas.

```java
int[][] tempo = new int[2][3];
```

Neste programa:

- `tempo[p][r]` representa o tempo da pessoa `p` na rede `r`.
- `tempo[0][0]` representa o tempo da primeira pessoa na primeira rede.
- `tempo[1][2]` representa o tempo da segunda pessoa na terceira rede.

### O que é um ciclo `for`

```java
for (int i = 0; i < 5; i++) {
    // instruções repetidas
}
```

Esta estrutura significa:

1. Começar com `i = 0`.
2. Repetir enquanto `i < 5`.
3. Executar `i++` no final de cada repetição.
4. Parar quando `i` deixa de ser menor que `5`.

### O que é uma condição ternária

```java
int resultado = condição ? valorSeVerdadeiro : valorSeFalso;
```

Exemplo:

```java
int perfil = total < 90 ? 0 : 3;
```

É semelhante a:

```java
int perfil;

if (total < 90) {
    perfil = 0;
} else {
    perfil = 3;
}
```

### O que é `System.out.printf`

`printf` apresenta texto usando formatos:

- `%d`: número inteiro.
- `%f`: número decimal.
- `%.2f`: número decimal com duas casas.
- `%s`: texto.
- `%-12s`: texto alinhado à esquerda com largura mínima de 12 caracteres.
- `%12s`: texto alinhado à direita com largura mínima de 12 caracteres.
- `%%`: símbolo `%` literal.
- `%n`: mudança de linha.
