package eda;

public class DivisionMethod {
	public static boolean isPrime(int num) {
        if (num <= 1) {
            return false; // Números menores ou iguais a 1 não são primos
        }
        for (int i = 2; i * i <= num; i++) { // Verifica até a raiz quadrada de 'num'
            if (num % i == 0) {
                return false; // Se for divisível, não é primo
            }
        }
        return true; // Se não encontrar divisores, é primo
    }

    // Função para encontrar o próximo número primo maior ou igual a 'm'
    public static int nextPrime(int m) {
        while (!isPrime(m)) { // Continua até encontrar um número primo
            m++;
        }
        return m; // Retorna o próximo número primo
    }

    // Função hash utilizando o método de divisão
    public static int divisionHash(int key, int m) {
        return key % m; // Retorna o índice (hash) calculado pelo módulo
    }

    // Função para exibir os resultados de inserção na tabela de hash
    public static void printHashTable(int[] table, int m) {
        System.out.println("\nTabela de Hash:");
        for (int i = 0; i < m; i++) {
            System.out.println("Índice " + i + ": " + table[i]);
        }
    }}
//mudança