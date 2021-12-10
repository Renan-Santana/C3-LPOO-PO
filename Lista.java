
import java.util.ArrayList;

public class Lista {

	private ArrayList< Compra> lista;
	
	public Lista() {
		super();
		lista = new ArrayList <Compra>();
	}
	
	public void add (Compra compra) {
		lista.add(compra);
	}
	
	public int size() {
		return lista.size();
	}
	
	public String imprime() {
		String temp ="";
		for(Compra compra : this.lista) {
			temp+=compra.getCliente().getCpf()+ " | ";
		}
		return temp;
	}
	
	public Compra pesquisa (String cpf) {
		if (this.eVazia()) {
			return null;
		}
		for(Compra compra: this.lista) {
			if(compra.getCliente().getCpf().equals(cpf)) {
				return compra;
			}
		}
		return null;
	}
	
	public boolean eVazia () {
		if(this.lista.size()==0)
			return true;
		else 
			return false;
	}
}
