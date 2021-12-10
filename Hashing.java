

public class Hashing {

	private Lista[] vetor;
	
	public Hashing (int tam) {
		super();
		vetor = new Lista[tam];
		for(int i=0; i<tam; i++) {
			vetor[i] =new Lista();
		}
	}
	
	private int Hashing (String cpf) {
		long auxcpf = Long.parseLong(cpf);
		return (int) (auxcpf%vetor.length);
	}
	
	public void inserir (Compra compra) {
		int hash = Hashing(compra.getCliente().getCpf());
		vetor[hash].add(compra);  
	}
	
	public Compra pesquisar (String cpf) {
		int hash = Hashing(cpf);
		return vetor[hash].pesquisa(cpf);
	}
	
	public String imprimir () {
		String temp="";
		for (int i=0; i<vetor.length; i++) {
			temp+= "pos = "+i+"  ";
			if (vetor[i].eVazia()) {
				temp+="\n";
			}else {
				temp+= vetor[i].imprime()+"\n";
			}
		}
		return temp;
	}
}

