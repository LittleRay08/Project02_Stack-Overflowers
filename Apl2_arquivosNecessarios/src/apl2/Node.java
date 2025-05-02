// arquivo: src/apl2/Node.java

// Nome: Jully Manuele Dias Lima - RA: 10420556
// Nome: Leonardo Silva Moreno Ruiz - RA: 10420477
// Nome: Pedro Moniz Canto - RA: 10418358
// Nome: Rayane Yumi Da Silva Tahara - RA: 10410892

package apl2;

public class Node {
	private String id;
	private String nome;
	private float nota;
	private Node ant;
	private Node prox;
	
	public Node(String id, String nome, float nota) {
		this(id, nome, nota, null, null);
	}
	
	public Node(String id, String nome, float nota, Node ant, Node prox) {
		this.id = id;
		this.nome = nome;
		this.nota = nota;
		this.ant= ant;
		this.prox = prox;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public float getNota() {
		return nota;
	}
	
	public void setNota(float nota) {
		this.nota = nota;
	}

	
	public Node getAnt() {
		return ant;
	}
	
	public void setAnt(Node ant) {
		this.ant = ant;
	}

	public Node getProx() {
		return prox;
	}
	
	public void setProx(Node prox) {
		this.prox = prox;
	}
	
	@Override
	public String toString() {
		return id + ";" + nome + ";" + nota;
	}

   }