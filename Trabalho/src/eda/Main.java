package eda;

import java.util.ArrayList;
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Criação da tabela de hash com tamanho inicial de 300
        HashTable hashTable = new HashTable(1000); 
        String arquivo = "random_numbers3.txt"; // Caminho do arquivo
        
        try {
            // Leitura do arquivo linha por linha
            BufferedReader reader = new BufferedReader(new FileReader(arquivo));
            String line = "";
            
            // Lê linha por linha do arquivo
            while ((line = reader.readLine()) != null) {
                // Quebra a linha em tokens separados por espaço
                String[] tokens = line.split(" ");
                
                // Medindo tempo de execução
                long start = System.nanoTime();

                // Loop para adicionar os números na tabela de hash
                for (String token : tokens) {
                    try {
                        int chave = Integer.parseInt(token); // Converte o token para número inteiro
                        hashTable.add(chave); // Adiciona à tabela de hash
                    } catch (NumberFormatException e) {
                        // Ignora caso algum token não seja um número válido
                        System.out.println("Token inválido: " + token);
                    }
                }
            }
            
            // Exibe o número total de colisões após o processamento do arquivo
            System.out.println("Número total de colisões: " + hashTable.getNumeroDeColisoes());

            // Fecha o BufferedReader após ler o arquivo
            reader.close();

        } catch (IOException ioe) {
            System.out.println("Erro ao ler a entrada do arquivo: " + ioe.getMessage());
        }
    }
}

class HashTable {
    private List<Integer>[] table; // Tabela de hash
    private int tamanho; // Tamanho da tabela
    private int count; // Número de elementos na tabela
    private int numeroDeColisoes; // Contador de colisões

    // Construtor da tabela hash
    public HashTable(int tamanhoInicial) {
    	this.tamanho = DivisionMethod.nextPrime(tamanhoInicial);
    	this.table = new ArrayList[this.tamanho];
        this.count = 0;
        this.numeroDeColisoes = 0; // Inicializa o contador de colisões

        // Inicializa as listas vazias
        for (int i = 0; i < tamanho; i++) {
            table[i] = new ArrayList<>();
        }
    }

    // Função para verificar se o índice está vazio
    public boolean isIndexEmpty(int hash) {
        return table[hash].isEmpty();  // Retorna verdadeiro se a lista estiver vazia, caso contrário, falso
    }

    // Função para adicionar uma chave na tabela hash
    public void add(int key) {
        // Usando o método de divisão (DivisionMethod) para o cálculo do índice de hash
        int hash = DivisionMethod.hashFunction(key, tamanho); 
        
        // Verifica se o índice está vazio
        if (isIndexEmpty(hash)) {
            System.out.println("Chave " + key + " será adicionada no hash " + hash);
            table[hash].add(key);  // Adiciona a chave
            count++;  // Incrementa o contador de elementos
        } else {
            // Se já houver algo na lista, significa que houve uma colisão
            table[hash].add(key);
            count++; 
            numeroDeColisoes++;  // Incrementa o contador de colisões
            System.out.println("Chave " + key + " será adicionada no hash " + hash + ". Colisão detectada!");
        }

        // Se a tabela estiver mais de 85% cheia, realiza o redimensionamento
        if (count > tamanho * 0.85) {
            resize(); // Redimensiona a tabela se necessário
        }
    }

    // Função para redimensionar a tabela hash
    private void resize() {
        System.out.println("Redimensionando a tabela...");

        // Dobra o tamanho da tabela
        int novoTamanho = DivisionMethod.nextPrime(tamanho * 2);

        // Cria uma nova tabela de hash com o novo tamanho
        List<Integer>[] novaTabela = new ArrayList[novoTamanho];

        // Inicializa as novas listas
        for (int i = 0; i < novoTamanho; i++) {
            novaTabela[i] = new ArrayList<>();
        }

        // Reinsere os elementos na nova tabela com o novo tamanho
        for (int i = 0; i < tamanho; i++) {
            for (int key : table[i]) {
                // Calculando o índice com o novo tamanho da tabela
                int hashNovo = DivisionMethod.hashFunction(key, novoTamanho);  
                novaTabela[hashNovo].add(key); // Adiciona o valor na nova tabela
            }
        }

        // Substitui a tabela antiga pela nova
        this.table = novaTabela;
        this.tamanho = novoTamanho; // Atualiza o tamanho da tabela
    }

    // Função para obter o número total de colisões
    public int getNumeroDeColisoes() {
        return numeroDeColisoes;
    }
}
