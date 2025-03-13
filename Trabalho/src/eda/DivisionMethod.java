package eda;

public class DivisionMethod {
	  public static int hashFunction(int key, int size) {
	        // Calculando o "valor do hash" diretamente (o valor após a operação)
	        int hashValue = Math.abs(key); // Esse é o valor do hash (não modificado ainda)
	        
	        // O índice é calculado pela operação de módulo (%)
	        int index = hashValue % size; 
	        
	        // Retorna o índice
	        return index;
}
}