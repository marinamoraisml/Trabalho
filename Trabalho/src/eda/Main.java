package eda;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        int capacidadeInicial = DivisionMethod.getProximoPrimo(1000);
        DivisionMethod hashTable = new DivisionMethod(capacidadeInicial);
        String arquivo = "random_numbers3.txt"; 
        
        try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
            String line;
            
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                
                for (String token : tokens) {
                    try {
                        int chave = Integer.parseInt(token);
                        hashTable.add(chave);
                        int index = hashTable.hash(chave);
                        if (hashTable.contains(chave)) {
                            System.out.println("A Chave " + chave + " colocada no hash " + index);
                        } else {
                            System.out.println("A Chave " + chave + " colocada no hash " + index + ". ALERTA!! Colisão");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Token inválido: " + token);
                    }
                }
            }
            
            System.out.println("Elementos: " + hashTable.size());
            System.out.println("Quantidade de colisões: " + hashTable.getColisoes());
        } catch (IOException ioe) {
            System.out.println("Erro ao ler arquivo: " + ioe.getMessage());
        }
    }
}
