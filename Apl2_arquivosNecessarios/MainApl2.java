package apl2;
//*************************** ATENÇÃO! ****************************
// O método main() deve ser alterado somente nos comentários TODO.
// Todas as outras instruções devem permanecer intactas e o código
// deve funcionar conforme descrito no enunciado da atividade.
//*************************** ATENÇÃO! ****************************
// arquivo: src/MainApl2.java

// Nome: Jully Manuele Dias Lima - RA: 10420556
// Nome: Leonardo Silva Moreno Ruiz - RA: 10420477
// Nome: Pedro Moniz Canto - RA: 10418358
// Nome: Rayane Yumi Da Silva Tahara - RA: 10410892

// TODO: Listar todas as referências consultadas para solucionar a atividade.

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class MainApl2 {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LinkedListOriginal list = new LinkedListOriginal();
		Scanner sc = new Scanner(System.in);
		
		// TODO: Carregar o conteúdo do arquivo "dados.txt" e adicionar cada linha como um nó na LinkedListOriginal list.
		try (BufferedReader br = new BufferedReader(new FileReader("Apl2_arquivosNecessarios\\dados.txt"))) {
			String linha;
			while ((linha = br.readLine()) != null) { //pecorrer arquivo
				String[] partes = linha.split("#"); //separa quando encontra #
				if (partes.length == 4) {
					int id = Integer.parseInt(partes[0]);         
					String nome = partes[1];
					int inteiro = Integer.parseInt(partes[2]);
					int decimo = Integer.parseInt(partes[3]);
		
					list.append(id, nome, inteiro, decimo);  //adiciona dados na lista original
				}
			}
		} catch (IOException e) {
			System.out.println("Erro ao ler o arquivo: " + e.getMessage()); //colocar em casos de uso quando da errado
		}
		DLinkedList fixedList = null;
		DLinkedList filteredGradedList = null;
		DLinkedList filteredNonGradedList = null;
		DLinkedList aboveAverageList = null;
		float average = 0f;
		String contents = null;

		int opcao = -1;
		while(opcao != 0){
			System.out.println("\n================== Sistema Conversor de Notas ==================");
            System.out.println("1 - Exibir Dados Originais");
            System.out.println("2 - Exibir Dados Convertidos");
            System.out.println("3 - Lista Notas Filtradas Válidas");
            System.out.println("4 - Lista Notas Filtradas Inválidas");
            System.out.println("5 - Média De Notas Válidas");
            System.out.println("6 - Notas Acima Da Média");
            System.out.println("7 - Lista Mapeada P/ Uma Única String");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();

			switch (opcao){
				case 1:
					System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
					System.out.println(list);
					System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");
					break;
				case 2:
					fixedList = Operation.map(list);
					System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
					System.out.println(fixedList);
					System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");
					break;
				case 3:
					filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
					System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
					System.out.println(filteredGradedList);
					System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
					break;
				case 4:
					filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
					System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
					System.out.println(filteredNonGradedList);
					System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");
					break;
				case 5:
					average = Operation.reduce(filteredGradedList);
					System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
					System.out.println(average);
					System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
					break;
				case 6:
					aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
					System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
					System.out.println(aboveAverageList);
					System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
					break;
				case 7:
					contents = Operation.mapToString(fixedList);
					System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
					System.out.println(contents);
					System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");		
					break;
				case 0:
					System.out.println("Saindo do programa...");
					break;
			}
		}
/*	 
		System.out.println(">>>>>>>>>> Dados originais (sistema legado) >>>>>>>>>>");
		System.out.println(list);
		System.out.println("<<<<<<<<<< Dados originais (sistema legado) <<<<<<<<<<\n");
		
		DLinkedList fixedList = Operation.map(list);
		System.out.println(">>>>>>>>>> Dados convertidos para a nova representação dos dados >>>>>>>>>>");
		System.out.println(fixedList);
		System.out.println("<<<<<<<<<< Dados convertidos para a nova representação dos dados <<<<<<<<<<\n");
		
		DLinkedList filteredGradedList = Operation.filterRemoveNonGraded(fixedList);
		System.out.println(">>>>>>>>>> Lista filtrada (somente notas válidas) >>>>>>>>>>");
		System.out.println(filteredGradedList);
		System.out.println("<<<<<<<<<< Lista filtrada (somente notas válidas) <<<<<<<<<<\n");
		
		DLinkedList filteredNonGradedList = Operation.filterRemoveGraded(fixedList);
		System.out.println(">>>>>>>>>> Lista filtrada (somente 'ausência de nota') >>>>>>>>>>");
		System.out.println(filteredNonGradedList);
		System.out.println("<<<<<<<<<< Lista filtrada (somente 'ausência de nota') <<<<<<<<<<\n");

		float average = Operation.reduce(filteredGradedList);
		System.out.println(">>>>>>>>>> Média das notas válidas >>>>>>>>>>");
		System.out.println(average);
		System.out.println("<<<<<<<<<< Média das notas válidas <<<<<<<<<<\n");
		
		DLinkedList aboveAverageList = Operation.filterRemoveBelowAverage(filteredGradedList, average);
		System.out.println(">>>>>>>>>> Lista com notas acima da média >>>>>>>>>>");
		System.out.println(aboveAverageList);
		System.out.println("<<<<<<<<<< Lista com notas acima da média <<<<<<<<<<\n");
		
		String contents = Operation.mapToString(fixedList);
		System.out.println(">>>>>>>>>> Lista mapeada para uma única string >>>>>>>>>>");
		System.out.println(contents);
		System.out.println("<<<<<<<<<< Lista mapeada para uma única string <<<<<<<<<<\n");
	
		// TODO: Salvar o conteúdo da String contents em um arquivo chamado "dados.csv".

		
		Node test1 = fixedList.getNode("23.S1-999");
		System.out.println(">>>>>>>>>> test1 >>>>>>>>>>\n" + test1 + "\n<<<<<<<<<< test1 <<<<<<<<<<\n");

		Node test2 = fixedList.removeNode("23.S1-999");
		System.out.println(">>>>>>>>>> test2 >>>>>>>>>>\n" + test2 + "\n<<<<<<<<<< test2 <<<<<<<<<<\n");

		Node test3 = fixedList.getNode("23.S1-999");
		System.out.println(">>>>>>>>>> test3 >>>>>>>>>>\n" + test3 + "\n<<<<<<<<<< test3 <<<<<<<<<<\n");

		aboveAverageList.clear();
		System.out.println(">>>>>>>>>> aboveAverageList.clear() >>>>>>>>>>\n" + aboveAverageList + "\n<<<<<<<<<< aboveAverageList.clear() <<<<<<<<<<\n");

		DLinkedList testList = new DLinkedList();
		// TODO: Inserir um nó no início da lista testList com os dados ("ABC", "John Doe", 4.7f).
		testList.insert("ABC","John Doe", 4.7f);
		// TODO: Inserir um nó no final da lista testList com os dados ("XYZ", "Jane Doe", 9.9f).
		testList.append("XYZ", "Jane Doe", 9.9f);
		// TODO: Inserir um nó no início da lista testList com os dados ("321", "Test", 2.3f).
		testList.insert("321","Test", 2.3f);
		// TODO: Inserir um nó no final da lista testList com os dados ("Nothing", "Yada yada yada", 99.9f).
		testList.append("Nothing", "Yada yada yada", 99.9f);

		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		System.out.println("testList.getHead(): " + testList.getHead());
		System.out.println("testList.getTail(): " + testList.getTail());
		System.out.println("testList.removeHead(): " + testList.removeHead());
		System.out.println("testList.removeTail(): " + testList.removeTail() + '\n');
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		System.out.println("testList.getHead(): " + testList.getHead());
		System.out.println("testList.getTail(): " + testList.getTail());
		System.out.println("testList.removeNode(\"ABC\"): " + testList.removeNode("ABC") + '\n');
		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		System.out.println("testList.getHead(): " + testList.getHead());
		System.out.println("testList.getTail(): " + testList.getTail() + '\n');

		// TODO: Inserir um nó no início da lista testList com os dados ("qwerty", "QWERTY", 1.2f).
		testList.insert("qwerty","QWERTY", 1.2f);
		// TODO: Inserir um nó no final da lista testList com os dados ("WASD", "wasd", 3.4f).
		testList.append("WASD", "wasd", 3.4f);
		// TODO: Inserir um nó no início da lista testList com os dados ("ijkl", "IJKL", 5.6f).
		testList.insert("ijkl","IJKL", 5.6f);
		// TODO: Inserir um nó no final da lista testList com os dados ("1234", "Um Dois Tres Quatro", 7.8f).
		testList.append("1234", "Um Dois Tres Quatro", 7.8f);

		System.out.println(">>>>>>>>>> testList >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList <<<<<<<<<<\n");
		testList.clear();
		System.out.println(">>>>>>>>>> testList.clear() >>>>>>>>>>\n" + testList  + "\n<<<<<<<<<< testList.clear() <<<<<<<<<<\n");
	}
*/

}

}