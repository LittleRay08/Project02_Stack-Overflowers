// arquivo: src/apl2/DLinkedList.java

// Nome: Jully Manuele Dias Lima - RA: 10420556
// Nome: Leonardo Silva Moreno Ruiz - RA: 10420477
// Nome: Pedro Moniz Canto - RA: 10418358
// Nome: Rayane Yumi Da Silva Tahara - RA: 10410892

package apl2;

// -- A classe DLinkedList (que pertence ao pacote apl2) deve implementar uma
// lista duplamente encadeada. Os nós dessa lista são do tipo [da classe] Node.
// -- A classe deve possuir dois nós especiais, head e tail, que são
// referências para o primeiro e último nó da lista, respectivamente.
// -- A classe deve possuir um contador de quantos nós existem na lista.
// -- A classe deve sobrescrever (override) o método public String toString()
// {...}, retornando uma string com o conteúdo da lista.
// -- A classe deve implementar as operações a seguir, respeitando o
// comportamento descrito em cada operação.

public class DLinkedList {
	private Node head;
	private Node tail;
	private int count;

// OPERAÇÃO:		Método construtor
// COMPORTAMENTO:	Cria uma lista vazia.
	public DLinkedList() {
		head = null;
		tail= null;
		count= 0;
	}

// OPERAÇÃO:		insert(<dados da pessoa>)
// COMPORTAMENTO:	Aloca um Node que contém os <dados da pessoa> e insere o novo nó no início da lista.
	public void insert(String id, String nome, float nota) {
		Node aux= new Node(id,nome,nota );
		if(isEmpty()){
			head = tail = aux;
		}else{
			aux.setProx(head);
			head.setAnt(aux);
			head=aux;
		}
		count++;
	}


// OPERAÇÃO:		append(<dados da pessoa>)
// COMPORTAMENTO:	Aloca um Node que contém os <dados da pessoa> e insere o
//					novo nó no final da lista.
	public void append(String id, String nome, float nota) {
		Node aux= new Node(id,nome,nota );
		if(isEmpty()){
			head = tail = aux;
		}else{
			aux.setAnt(tail);
			tail.setProx(aux);
			tail=aux;
		}
		count++;
	}


// OPERAÇÃO: 		removeHead()
// COMPORTAMENTO:	Remove o nó do início da lista e retorna a referência do
//					nó removido.
//					Ou retorna null caso a lista esteja vazia.
	public Node removeHead() {
		if(isEmpty()) return null; 

		Node removido = head;
		if(count==1){
			head=tail=null;
		}
		else{
			head= head.getProx();
			head.setAnt(null);
		}
		count--;
		return removido;
	}


// OPERAÇÃO:		removeTail()
// COMPORTAMENTO:	Remove o nó do final da lista e retorna a referência do
//					nó removido.
//					Ou retorna null caso a lista esteja vazia.
	public Node removeTail() {
		if(isEmpty()) return null; 

		Node removido = tail;
		if(count==1){
			head=tail=null;
		}
		else{
			tail=tail.getAnt();
			tail.setProx(null);

		}
		count--;
		return removido;
	}


// OPERAÇÃO:		removeNode(<ID da pessoa>)
// COMPORTAMENTO:	Remove o nó que contém o <ID da pessoa> da lista e retorna
//					a referência do nó removido.
//					Ou retorna null caso não exista um nó com <ID da pessoa>.
	public Node removeNode(String id) {
//ver se ta vazia
		if(isEmpty()) return null;
//ver se encontrou
		Node removido = getNode(id);
		if (removido == null) return null;
//se tiver so um elemento
		if (count == 1) {
			head = tail = null;
			count--;
			return removido;
		}
//se achou no head
		if (removido == head) {
			return removeHead(); // já atualiza count e retorna
		}
//se achou no tail
		if (removido == tail) {
			return removeTail(); // já atualiza count e retorna
		}
	
// Se chegou aqui, o nó está no meio da lista
		removido.getAnt().setProx(removido.getProx());
		removido.getProx().setAnt(removido.getAnt());
		count--;
	
		return removido;
	}


// OPERAÇÃO:		getHead()
// COMPORTAMENTO:	Retorna uma referência para o nó do início da lista.
//					Ou retorna null caso a lista esteja vazia.
	public Node getHead() {
		return head;
	}


// OPERAÇÃO:		getTail()
// COMPORTAMENTO:	Retorna uma referência para o nó do final da lista.
//					Ou retorna null caso a lista esteja vazia.
	public Node getTail() {
		return tail;
	}


// OPERAÇÃO:		getNode(<ID da pessoa>)
// COMPORTAMENTO:	Retorna uma referência para o nó que contém o <ID da pessoa>
//					da lista.
//					Ou retorna null caso não exista um nó com <ID da pessoa>.
	public Node getNode(String id) {

		if(isEmpty()) return null; 

		Node aux = head;
		
		while (aux != null) {
			if (aux.getId().equals(id)) {
				return aux;
			}
			aux = aux.getProx();
		}
		return null; // Se não encontrar
	}


// OPERAÇÃO:		count()
// COMPORTAMENTO:	Retorna a quantidade de nós da lista.
	public int count() {
		return count;
	}


// OPERAÇÃO:		isEmpty()
// COMPORTAMENTO:	Retorna true se a lista estiver vazia ou false, caso contrário.
	public boolean isEmpty() {
		return (count==0);
	}


// OPERAÇÃO:		clear()
// COMPORTAMENTO:	Esvazia a lista, liberando a memória de todos os nós da lista.
	public void clear() {
		if(isEmpty()) return;

		while(head!=null) removeHead();

	}


// OPERAÇÃO:		toString()
// COMPORTAMENTO:	Retorna uma string com o conteúdo da lista (caso queira, use o
//					exemplo do método toString() da classe LinkedListOriginal).
@Override
public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("(").append(count).append(")\n\n");

    Node node = head;
    while (node != null) {
        // Proteção contra ponteiros nulos
        String antId = (node.getAnt() == null) ? "null" : node.getAnt().getId();
        String proxId = (node.getProx() == null) ? "null" : node.getProx().getId();
		//montar impressao
        sb.append(antId)
          .append(" <- (")
          .append(node.getId())
          .append(" ; ")
          .append(node.getNome())
          .append(" ; ")
          .append(node.getNota())
          .append(") -> ")
          .append(proxId)
          .append("\n");

        node = node.getProx();
    }

    return sb.toString();
}

}