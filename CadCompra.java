import java.util.ArrayList;
import java.util.Calendar;

public class CadCompra implements Ordenacao, vetor {
	private ArrayList<Compra> vetCompra;

	public CadCompra(ArrayList<Compra> vetCompra) {
		super();
		this.vetCompra = vetCompra;
	}

	public ArrayList<Compra> getVetCompra() {
	return vetCompra;
	}

	public void setVetCompra(ArrayList<Compra> vetCompra) {
		this.vetCompra = vetCompra;
	}

	@Override
	public String toString() {
	return "CadCompra [vetCompra=" + vetCompra + "]";
	}

	@Override
	public Compra get(Integer pos) {
		// TODO Auto-generated method stub
		if (!this.vetCompra.isEmpty()) {
			if (this.vetCompra.size() > pos) {
				return this.vetCompra.get(pos);

			}
		}
		return null;
	}

	@Override
	public void insere(Compra compra) {
		// TODO Auto-generated method stub
		this.vetCompra.add(compra);
	}

	@Override
	public void remove(String cpf, Calendar data) {
		// TODO Auto-generated method stub
		if (!this.vetCompra.isEmpty()) {
			Integer cont = this.vetCompra.size();
			for (Compra compra : this.vetCompra) {
				if (compra.getCliente().getCpf() == cpf && compra.getData() == data) {
					this.vetCompra.remove(cont);
				}
				cont--;
			}
		}
	}

	@Override
	public void insercaoDireta() {
		// TODO Auto-generated method stub
		int i, j;
		Compra temp;
		for(i = 1; i < vetCompra.size(); i++) {
			temp = vetCompra.get(i);
			j = i - 1;
			while((j>=0 )&& vetCompra.get(j).getCliente().getCpf().compareTo(temp.getCliente().getCpf()) == 1){
				vetCompra.set(j+1, vetCompra.get(j)); 
				chaveSecundaria(i,j);
				j--;
			}
			
		}
	}

	public void quickSort(int esq, int dir) {
		// TODO Auto-generated method stub
		int i = esq, j=dir;
		String pivo;
		Compra temp;
		
		pivo = vetCompra.get((i+j)/2).getCliente().getCpf();
	
		do {
			while(vetCompra.get(i).getCliente().getCpf().compareTo(pivo) == -1) {
				i++;
			}
			while(vetCompra.get(j).getCliente().getCpf().compareTo(pivo) == 1) {
				if(i <= j) {
					temp = vetCompra.get(i);
					vetCompra.set(i, vetCompra.get(j));
					vetCompra.set(j, temp);
					i++;
					j--;
				}
				chaveSecundaria(i, j);
			}
			
		}while(i <= j);
		if(esq < j) {
			quickSort(esq, j);
		}
		if (dir > 1) {
			quickSort(i, dir);
		}
	}

	
	public void quickComInsercao(int esq, int dir) {
		// TODO Auto-generated method stub
		int i = esq, j=dir;
		String pivo;
		Compra temp;
		
		pivo = vetCompra.get((i+j)/2).getCliente().getCpf();
	
		do {
			while(vetCompra.get(i).getCliente().getCpf().compareTo(pivo) == -1) {
				i++;
			}
			while(vetCompra.get(j).getCliente().getCpf().compareTo(pivo) == 1) {
				if(i <= j) {
					temp = vetCompra.get(i);
					vetCompra.set(i, vetCompra.get(j));
					vetCompra.set(j, temp);
					i++;
					j--;
				}
				insercaoDireta();
			}
			
		}while(i <= j);
		if(esq < j) {
			quickComInsercao(esq, j);
		}
		if (dir > 1) {
			quickComInsercao(i, dir);
		}
	}

	@Override
	public void shellSort() {
		// TODO Auto-generated method stub
		int i, j, h = 1;
		Compra temp;
		
		do {
			h  = 3*h+1;
		}while(h < vetCompra.size());
		do {
			h = h/3;
			for(i = h; i < vetCompra.size(); i++) {
				temp = vetCompra.get(i);
				j = i;
				while(vetCompra.get(j-h).getCliente().getCpf().compareTo(temp.getCliente().getCpf())==1){
					vetCompra.set(j, vetCompra.get(j-h));
					chaveSecundaria(i, j);
					j -= h;
					if(j < h) {
						break;
					}
					vetCompra.set(j, temp);
				}
			}
		}while(h != 1);
	}

	private void chaveSecundaria(int i, int j) {
		Compra temp = null;
		if((j>=0 )&& 
				vetCompra.get(j).getCliente().getCpf().compareTo(temp.getCliente().getCpf()) == 0){
				if(j>=0 && vetCompra.get(j).getData().before(temp.getData())){
					temp = vetCompra.get(j);
					vetCompra.set(j, vetCompra.get(j+1)); 
					j--;
				}
			}
			vetCompra.set(j+1, temp);
	}


}