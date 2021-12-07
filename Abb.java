import java.util.ArrayList;

public class Abb {
	private Noabb raiz;
	private int nElem;

	public Abb() {
		this.nElem = 0;
	}

	public int getNElem() {
		return this.nElem;
	}

	public boolean eVazia() {
		if (this.raiz == null) {
			return true;
		} else {
			return false;
		}
	}

	public Noabb pesquisar(String cpf) {
		return pesquisar(cpf, this.raiz);
	}

	private Noabb pesquisar(String cpf, Noabb no) {
		if (no == null) {
			return null;
		} else {
			if (cpf == no.getChave()) {
				return no;
			} else if (no.getChave().compareTo(cpf) > 0) {
				no = pesquisar(cpf, no.getDir());
			} else if (no.getChave().compareTo(cpf) < 0) {
				no = pesquisar(cpf, no.getEsq());
			}
		}

		return no;
	}

	public ArrayList<Compra> getCompras(String cpf) {
		return pesquisar(cpf, raiz).getCompras();
	}

	public void inserir(Compra compra) {
		Noabb nodeAux = pesquisar(compra.getCliente().getCpf());
		if (nodeAux == null) {
			this.raiz = inserir(compra, this.raiz);
		} else {
			nodeAux.insereCompra(compra);
		}
	}

	private Noabb inserir(Compra compra, Noabb no) {
		if (no == null) {
			Noabb novo = new Noabb(compra);
			nElem++;
			return novo;
		} else {
			if (compra.getCliente().getCpf().compareTo(no.getChave()) < 0) {
				no.setEsq(inserir(compra, no.getEsq()));
			} else if (compra.getCliente().getCpf().compareTo(no.getChave()) > 0) {
				no.setDir(inserir(compra, no.getDir()));
			}
		}
		return no;
	}

	private Noabb inserir(Noabb novo, Noabb no) {
		if (no == null) {
			novo.setDir(null);
			novo.setEsq(null);
			nElem++;
			return novo;
		} else {
			if (novo.getChave().compareTo(no.getChave()) < 0) {
				no.setEsq(inserir(novo, no.getEsq()));
			} else if (novo.getChave().compareTo(no.getChave()) > 0) {
				no.setDir(inserir(novo, no.getDir()));
			}
		}
		return no;
	}

	public ArrayList<Noabb> fazCamCentral() {
		ArrayList<Noabb> vetor = new ArrayList<Noabb>(this.nElem);
		return fazCamCentral(vetor, this.raiz);
	}

	private ArrayList<Noabb> fazCamCentral(ArrayList<Noabb> vetor, Noabb no) {
		if (no != null) {
			vetor = fazCamCentral(vetor, no.getEsq());
			vetor.add(no);
			vetor = fazCamCentral(vetor, no.getDir());
		}
		return vetor;
	}

	public Abb balancear() {
		Abb arv = new Abb();
		ArrayList<Noabb> vetor = fazCamCentral();
		balancear(vetor, arv, 0, vetor.size() - 1);
		return arv;
	}

	private void balancear(ArrayList<Noabb> vetor, Abb arv, int inicio, int fim) {
		int meio;
		if (fim >= inicio) {
			meio = (inicio + fim) / 2;
			arv.inserir(vetor.get(meio), this.raiz);
			balancear(vetor, arv, inicio, meio - 1);
			balancear(vetor, arv, meio + 1, fim);
		}
	}
}