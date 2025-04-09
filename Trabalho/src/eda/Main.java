package eda;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main{
public static void main(String[] args) {
    int capacidadeInicial = DivisionMethod.getProximoPrimo(3000);
    DivisionMethod hashTable = new DivisionMethod(capacidadeInicial);
    String arquivo = "random_numbers3.txt"; 

    try (BufferedReader reader = new BufferedReader(new FileReader(arquivo))) {
        String line;

        while ((line = reader.readLine()) != null) {
            String[] tokens = line.split(" ");

            for (String token : tokens) {
                try {
                    int chave = Integer.parseInt(token);
                    int index = hashTable.hash(chave);
                    boolean isColisao = false;
                    if (hashTable.contains(chave)) {
                        isColisao = true;
                    }
                    hashTable.add(chave);
                    if (isColisao) {
                        System.out.println("A Chave " + chave + " colocada no hash " + index + ". ALERTA!! Colisão");
                    } else {
                        System.out.println("A Chave " + chave + " colocada no hash " + index + ".");
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
