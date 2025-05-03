//*************************** ATENÇÃO! *****************************
// As assinaturas dos métodos desta classe não devem ser alteradas!
//*************************** ATENÇÃO! *****************************
// arquivo: src/apl2/Operation.java

// Nome: Jully Manuele Dias Lima - RA: 10420556
// Nome: Leonardo Silva Moreno Ruiz - RA: 10420477
// Nome: Pedro Moniz Canto - RA: 10418358
// Nome: Rayane Yumi Da Silva Tahara - RA: 10410892

package apl2;

public class Operation {

	/**
	 * <p>Recebe como parâmetro uma lista encadeada do tipo {@code LinkedListOriginal}, sendo que os nós da lista estão
	 * populados com o conteúdo da base de dados original (conteúdo do arquivo dados.txt).</p>
	 * <p>A operação {@code map()} deve mapear os dados originais para uma lista encadeada do tipo {@code DLinkedList} e
	 * retornar a referência da {@code DLinkedList} que possui os dados mapeados para a nova estrutura usada pelo sistema de notas.</p>
	 * 
	 * @param original Base de dados original carregada em uma {@code LinkedListOriginal}.
	 * @return Uma nova {@code DLinkedList} que contém o mapeamento da coleção de dados {@code original} para a nova estrutura usada pelo sistema de notas. 
	 */
	public static DLinkedList map(final LinkedListOriginal original) {
		DLinkedList dados = new DLinkedList(); //cria uma nova lista para coolocar os dados novos
		NodeOriginal aux = original.getHead(); //pegar o header da lista original
		while (aux!=null){ 
			String id = "23.S1-" + aux.getId();
			String nome = aux.getNome();
			if((aux.getInteiro() != -1)&&(aux.getDecimo()!=-1)){ //verifica sem tem nota valida diferente de -1
				float nota = aux.getInteiro()+ aux.getDecimo()/ 10.0f; //PRECISA DIVIDIR POR 10 A PARTE DECIMAL JULLY!!!!
				dados.append(id, nome, nota);
			}
			else{
				dados.append(id, nome,(float) 99.9 ); //99.9 indica que nao tem nota
			}	
			aux = aux.getNext(); //avanca na lista de dados antigos 
		}
		return dados;
	}

	/**
	 * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
	 * populados com o resultado da operação {@code map()}.</p>
	 * <p>A operação {@code filterRemoveNonGraded()} deve filtrar os nós que não possuem notas válidas (caso de "ausência de nota")
	 * e retornar uma nova lista do tipo {@code DLinkedList} contendo apenas os nós com notas válidas.</p>
	 * 
	 * @param data Base de dados mapeada para o formato {@code DLinkedList} (via operação {@code map()}).
	 * @return Uma nova {@code DLinkedList} que contém a coleção de dados ({@code data}) filtrada com nós que possuem apenas pessoas com notas válidas.
	 */
	public static DLinkedList filterRemoveNonGraded(final DLinkedList data) {
		DLinkedList notavalida = new DLinkedList();
		Node aux= data.getHead();
		while (aux!=null){
			if(aux.getNota()!= 99.9f){ //ver se tem nota invalida, esse 99.9f indica que é float

				notavalida.append(aux.getId(), aux.getNome(),aux.getNota());
			}
			aux= aux.getProx(); //pecorrer a lista
		}
		return notavalida;
	}

	/**
	 * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
	 * populados com o resultado da operação {@code map()}.</p>
	 * <p>A operação {@code filterRemoveGraded()} deve filtrar os nós que possuem notas válidas e retornar uma nova lista do
	 * tipo {@code DLinkedList} contendo apenas os nós com notas inválidas (caso de "ausência de nota").</p>
	 * 
	 * @param data Base de dados mapeada para o formato {@code DLinkedList} (via operação {@code map()}).
	 * @return Uma nova {@code DLinkedList} que contém a coleção de dados ({@code data}) filtrada com nós que possuem apenas pessoas com notas inválidas.
	 */
	public static DLinkedList filterRemoveGraded(final DLinkedList data) {
		DLinkedList notainvalida = new DLinkedList();
		Node aux= data.getHead();
		while (aux!=null){
			if(aux.getNota()== 99.9f){ //ver se tem nota invalida
				notainvalida.append(aux.getId(), aux.getNome(), (float) 99.9);
			}
			aux= aux.getProx(); //pecorrer a lista
		}
		return notainvalida;
	}

	/**
	 * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
	 * populados com o resultado da operação {@code filterRemoveNonGraded()}, e a média de notas válidas, calculadas com a
	 * operação {@code reduce()}.</p>
	 * <p>A operação {@code filterRemoveBelowAverage()} deve filtrar os nós que possuem notas abaixo da média e retornar uma
	 * nova lista do tipo {@code DLinkedList} contendo apenas os nós com notas acima da média.
	 * 
	 * @param data Base de dados filtrada com a operação {@code filterRemoveNonGraded()}.
	 * @param average Média de notas válidas calculada com a operação {@code reduce()}.
	 * @return Uma nova {@code DLinkedList} que contém a coleção de dados ({@code data}) filtrada somente com pessoas com notas maiores do que {@code average}.
	 */
	public static DLinkedList filterRemoveBelowAverage(final DLinkedList data, float average) {
		DLinkedList acimamedia = new DLinkedList();
		Node aux= data.getHead();
		while (aux!=null){
			if(aux.getNota()> average ){ //ver quais notas estao acima da media
				acimamedia.append(aux.getId(), aux.getNome(), aux.getNota());
			}
			aux= aux.getProx(); //pecorrer a lista
		}
		return acimamedia;
	}
	
	/**
	 * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
	 * populados com o resultado da operação {@code filterRemoveNonGraded()}.</p>
	 * <p>A operação {@code reduce()} deve calcular a média das notas contidas na coleção de dados passada como parâmetro e
	 * retornar a média calculada.
	 * 
	 * @param data Base de dados filtrada com a operação {@code filterRemoveNonGraded()}.
	 * @return Média das notas ({@code float}) contidas na coleção de dados ({@code data}).
	 */
	public static float reduce(final DLinkedList data) {
		Node aux= data.getHead();
		int tam = data.count(); //pega o tamanho da lista data
		float media=0;
		while(aux!=null){
			media= media + aux.getNota(); //vai somando as notas
			aux= aux.getProx(); //percorre a lista
		}
		media = media / tam; //faz a media da soma com tamanho da lista 

		return media;
	}

	/**
	 * <p>Recebe como parâmetro uma lista duplamente encadeada do tipo {@code DLinkedList}, sendo que os nós da lista estão
	 * populados com o resultado da operação {@code map()}.</p>
	 * <p>A operação {@code mapToString()} deve mapear todos os nós da coleção de dados passada como parâmetro para uma única
	 * {@code String}, sendo que cada dado de uma pessoa é separado por ponto-e-vírgula (;) e cada pessoa é separada por uma
	 * quebra de linha.</p>
	 * 
	 * @param data Base de dados mapeada para o formato {@code DLinkedList} (via operação {@code map()}).
	 * @return {@code String} com a coleção de dados separada por ponto-e-vírgula (dados de cada pessoa) e quebras de linha (cada pessoa).
	 */
	public static String mapToString(final DLinkedList data) {
		StringBuilder sb = new StringBuilder();
		Node node = data.getHead();

		while (node != null) {
			sb.append(node.getId())
			.append(";")
			.append(node.getNome())
			.append(";")
			.append(node.getNota())
			.append("\n");
			node = node.getProx();
		}
		return sb.toString();
		}

}
