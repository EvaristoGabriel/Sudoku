package brufjfdcc025.sudoku;

import java.util.Random;
import java.util.Scanner;

/*Gabriel Evaristo 201965034AB
Sudoku- Exercício 1- DCC025*/

public class Sudoku {
    public static void main(String[] args) {
        
        Scanner teclado = new Scanner(System.in);
        
        int[][] tabuleiro = new int[9][9];
        int[][] tabuleiroInicial = new int[9][9];
        
        int quant, valorA, linhaA,colunaA,verificador,linha,coluna,valor;
        String continuar = "Sim";
        
        
        while("Sim".equals(continuar)){
            //inicializando o tabuleiro com 0
            ZerarTabuleiro(tabuleiro);
            System.out.println("Bem vindo!! Como pretende gerar um jogo?");
            System.out.println("1-Jogo aleatório\n2-Definir o próprio jogo");
            System.out.println("Sua opção: ");
            int opcao = teclado.nextInt();
            
            switch(opcao){
                case 1: System.out.println("Quantos números deseja sortear: ");
                        quant = teclado.nextInt();
                        if(quant > 81){
                            System.out.println("Numero invalido, o tabuleiro tem 81 casas, sendo este o máximo");
                            System.out.println("Tente novamente: ");
                            quant = teclado.nextInt();
                        }

                        Random gerador = new Random();
                        for(int i=0;i<quant;i++){
                            linhaA = gerador.nextInt(9);
                            colunaA = gerador.nextInt(9);
                            valorA = gerador.nextInt(9)+1;

                            if(tabuleiro[linhaA][colunaA] == 0){
                                verificador = Verifica(tabuleiro,valorA,linhaA,colunaA);
                                if(verificador == 0){
                                    tabuleiro[linhaA][colunaA] = valorA;
                                }
                                else
                                    i--;
                            }
                            else
                                i--;
                        }
                        break;

                case 2: System.out.println("Definindo o jogo (para sair digite -1,-1,-1)");
                        System.out.println("Formato de entrada ([linha],[coluna],[valor])");
                        System.out.println("Linha: 1-9");
                        System.out.println("Coluna: 1-9");
                        System.out.println("Valor: 1-9");
                        linha = teclado.nextInt();
                        System.out.println("("+linha+",");
                        coluna = teclado.nextInt();
                        System.out.println("("+linha+","+coluna+",");
                        valor =teclado.nextInt();
                        System.out.println("("+linha+","+coluna+","+valor+")");

                        while(linha != -1 && coluna != -1 && valor != -1){
                            if(tabuleiro[linha-1][coluna-1] == 0){
                                verificador = Verifica(tabuleiro,valor,linha-1,coluna-1);
                                if(verificador == 0){
                                    tabuleiro[linha-1][coluna-1] = valor;
                                }
                                if(verificador == 1){
                                    System.out.println("Não foi possivel colocar o valor no local!");
                                    System.out.println("Tente novamente!");
                                }
                            }
                            else
                                System.out.println("Local já ocupado");

                            ImprimirTabuleiro(tabuleiro);
                            linha = teclado.nextInt();
                            System.out.println("("+linha+",");
                            coluna = teclado.nextInt();
                            System.out.println("("+linha+","+coluna+",");
                            valor =teclado.nextInt();
                            System.out.println("("+linha+","+coluna+","+valor+")");
                        }
                        break;
                default:System.out.println("Opcao invalida");
            }
            System.out.println("Seu Tabuleiro de ínicio:");
            ImprimirTabuleiro(tabuleiro);
            for(int i=0; i<9; i++){
                for(int j=0;j<9;j++)
                    tabuleiroInicial[i][j] = tabuleiro[i][j];
            }

            int opc = 1;
            while(opc != 5){
                System.out.println("Menu de opções: ");
                System.out.println("1-Adicionar Jogada");
                System.out.println("2-Remover Jogada");
                System.out.println("3-Verificar");
                System.out.println("4-Dica");
                System.out.println("5-Sair");

                opc = teclado.nextInt();

                if(opc != 5 ){
                    switch(opc){
                        case 1: int ganhar = AdicionarJogada(tabuleiro);
                                if(ganhar == 1)
                                    opc = 5;
                                break;
                        case 2: RemoverJogada(tabuleiro,tabuleiroInicial);
                                break;
                        case 3: Verificar(tabuleiro);
                                break;
                        case 4: Dica(tabuleiro);
                                break;
                        default: System.out.println("Opção invalida!");
                                break;
                    }
                }
                else{
                    System.out.println("Saindo!");
                    System.out.println("Foi bom enquanto durou...");
                    continuar = "nao";
                }
            }
        }
    }   
    
    public static void ImprimirTabuleiro(int[][] tabuleiro){
        for(int i=0; i<9; i++){
            for(int j=0;j<9;j++){
                if(j == 3 || j == 6){
                    System.out.print(" | ");
                }
                System.out.print(tabuleiro[i][j]+" ");
            }
            
            if(i == 2 || i == 5){
                System.out.println("");
                System.out.println("_______________________");
            }
            System.out.println("");
        }
    }
    
    public static void ZerarTabuleiro(int[][] tabuleiro){
        for(int i=0; i<9; i++){
            for(int j=0;j<9;j++){
                tabuleiro[i][j] = 0;
            }
        }
    }

    public static int AdicionarJogada(int[][] tabuleiro){
        boolean ganhar =false;
        System.out.println("Qual jogada você quer fazer: ");
        Scanner teclado = new Scanner(System.in);
        System.out.println("Linha (1-9): ");
        int linha = teclado.nextInt();
        System.out.println("Coluna (1-9): ");
        int coluna = teclado.nextInt();
        System.out.println("Valor (1-9): ");
        int valor = teclado.nextInt();
        if(linha < 1 || linha > 9 || coluna < 1 || coluna > 9 || valor < 1 || valor > 9){
            while((linha > 9 || linha <1) || (coluna > 9 || coluna < 1) || (valor > 9 || valor < 1)){
                System.out.println("Somente números de 1 a 9 são válidos!");
                System.out.println("Linha (1-9): ");
                linha = teclado.nextInt();
                System.out.println("Coluna (1-9): ");
                coluna = teclado.nextInt();
                System.out.println("Valor (1-9): ");
                valor = teclado.nextInt();
            }
        }
        if(tabuleiro[linha-1][coluna-1] == 0){
            int verificador = Verifica(tabuleiro, valor, linha-1, coluna-1);
            if(verificador == 0){
                tabuleiro[linha-1][coluna-1] = valor;
                System.out.println("Valor adicionado!");
            }
            else{
                System.out.println("Já existe este valor em uma linha ou coluna!");
            }
        }
        else{
            System.out.println("Já existe um valor!");
        }
        
        ImprimirTabuleiro(tabuleiro);
        ganhar = VerificaTabuleiro(tabuleiro);
        if(ganhar == true)
            return 1;   
        else
            return 0;
    }
    
    public static void RemoverJogada(int[][] tabuleiro,int[][] tabuleiroInicial){
        System.out.println("Qual jogada você quer cancelar:");
        Scanner teclado = new Scanner(System.in);
        System.out.println("Linha (1-9): ");
        int linha = teclado.nextInt();
        System.out.println("Coluna (1-9): ");
        int coluna = teclado.nextInt();
        
        if(tabuleiro[linha-1][coluna-1] != tabuleiroInicial[linha-1][coluna-1]){
            System.out.println("Jogada Removida!");
            tabuleiro[linha-1][coluna-1] = 0;
        }
        else{
            System.out.println("Não foi possível, o valor já foi definido previamente!");
        }
        
        ImprimirTabuleiro(tabuleiro);
    }
    
    public static void Verificar(int [][] tabuleiro){
        Scanner teclado = new Scanner(System.in);
        int verificador = 0;
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++){
                verificador = Verifica(tabuleiro,i,j);
                if(verificador == 1)
                    break;
            }
            if(verificador == 1)
                break;
        }
        if(verificador == 1)
            System.out.println("Seu jogo está errado!");
        else
            System.out.println("Jogo correto!");
        
        ImprimirTabuleiro(tabuleiro);
        }
        
    
    public static int Verifica(int[][] tabuleiro, int linha, int coluna){
        int valor = tabuleiro[linha][coluna];
        if(valor == 0){
            return 0;
        }
        else{
            for(int i = 0; i < 9; i++){
                if(linha == i && coluna == coluna)
                    return 0;
                if(tabuleiro[i][coluna] == valor)
                    return 1;
            }
            for(int j = 0; j < 9; j++){
                if(coluna == j && linha == linha)
                    return 0;
                if(tabuleiro[linha][j] == valor)
                    return 1;
            }
            int caixalinha = linha - linha%3;
            int caixacoluna = coluna - coluna%3;
            
            for(int i = 0; i < 3; i++)
                for(int j = 0; j < 3; j++)
                    if(tabuleiro[caixalinha + i][caixacoluna + j] == valor)
                        return 1;
        }

        return 0;
    }
    
    public static int Verifica(int[][] tabuleiro, int valor,int linha, int coluna){
        for(int i = 0; i < 9; i++){
            if(tabuleiro[i][coluna] == valor)
                return 1;
        }
        for(int j = 0; j < 9; j++){
            if(tabuleiro[linha][j] == valor)
                return 1;
        }
        
        int caixalinha = linha - linha%3;
        int caixacoluna = coluna - coluna%3;
        
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                if(tabuleiro[caixalinha + i][caixacoluna + j] == valor)
                    return 1;

        return 0;
    }
    public static boolean VerificaTabuleiro(int[][] tabuleiro){
        boolean verificando = true;
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                if(tabuleiro[i][j] == 0)
                    verificando = false;
            }
        }
        if(verificando == true){
            int verificador=0;
            for(int i=0;i<9;i++){
                for(int j=0;j<9;j++){
                    verificador = Verifica(tabuleiro,i,j);
                    if(verificador == 1)
                        break;
                }
                if(verificador == 1)
                    break;
            }
            
            if(verificador == 1){
                System.out.println("Seu jogo está errado!");
                return false;
            }
            else{
                System.out.println("***************************************************");
                System.out.println("****          PARABENS VOCÊ GANHOU!!!          ****");
                System.out.println("***************************************************");
                return true;
            }
        }
        return false;
    }
    
    public static void Dica(int[][] tabuleiro){
        Scanner teclado = new Scanner(System.in);
        System.out.println("Qual linha você quer dica: ");
        int linhaEsc = teclado.nextInt();
        System.out.println("Qual coluna você quer dica: ");
        int colunaEsc = teclado.nextInt();
        int vetor[];
        vetor = new int[9];
        int quant = 0, num=1, linha = linhaEsc-1, coluna = colunaEsc-1, verificador=0;
        while(num <10){
            verificador = 0;
            for(int i=0; i<9;i++){
                if(i != linha){
                    if(tabuleiro[i][coluna] == num)
                        verificador = 1;
                }
            }
            if(verificador == 0){
                for(int j=0; j<9; j++){
                    if(j!= coluna){
                        if(tabuleiro[linha][j] == num)
                            verificador =1;
                    }
                }
                if(verificador == 0){
                    int caixalinha = linha - linha%3;
                    int caixacoluna = coluna - coluna%3;
                    for(int i = 0; i < 3; i++){
                        for(int j = 0; j < 3; j++){
                            if(tabuleiro[caixalinha + i][caixacoluna + j] == num){
                                verificador = 1;
                            }
                        }
                    }
                    if(verificador == 0)
                        vetor[quant] = num;
                }
            }
            if(vetor[quant] != 0)
                quant++;
            num++;
        }
        System.out.println("Os valores que podem ser usado nessa posição são: ");
        for(int i=0;i<9;i++){
            if(vetor[i] != 0)
                System.out.print(vetor[i]+" ");
            else
                break;
        }
        System.out.println("");
    }
}
