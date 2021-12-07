package system;

import java.util.ArrayList;

public class AVL {
	private NoAVL raiz;
	private String chave;
	private ArrayList<Compra> compras;
	private boolean h;
	private int nElem;

	public AVL() {
		this.raiz = null;
		this.h = true;
	}

	public boolean eVazia() {
		if (this.raiz == null) {
			return true;
		} else {
			return false;
		}
	}

	public NoAVL getRaiz() {
		return this.raiz;
	}

	public int getnElem() {
		return this.nElem;
	}

	public String getChave() {
		return this.chave;
	}

	private NoAVL pesquisar1(String cpf, NoAVL no) {
		if (cpf == no.getChave()) {
		} else if (no.getChave().compareTo(cpf) > 0) {
			no = pesquisar1(cpf, no.getDir());
		} else if (no.getChave().compareTo(cpf) < 0) {
			no = pesquisar1(cpf, no.getEsq());
		}
		return no;
	}

	private NoAVL balancearDir(NoAVL no) {
		if (this.h)
			switch (no.getFb()) {
			case 1:
				no.setFb((byte) 0);
				h = false;
				break;
			case 0:
				no.setFb((byte) -1);
				break;
			case -1:
				no = this.rotacaoEsquerda(no);
			}
		return no;
	}

	private NoAVL balancearEsq(NoAVL no) {
		if (this.h)
			switch (no.getFb()) {
			case -1:
				no.setFb((byte) 0);
				h = false;
				break;
			case 0:
				no.setFb((byte) -1);
				break;
			case 1:
				no = this.rotacaoDireita(no);
			}
		return no;
	}

	private NoAVL rotacaoDireita(NoAVL no) {

		NoAVL temp1, temp2;
		temp1 = no.getEsq();
		if (no.getFb() == -1) {
			no.setEsq(temp1.getDir());
			temp1.setDir(no);
			no.setFb((byte) 0);
			no = temp1;
		} else {
			temp2 = temp1.getDir();
			temp1.setDir(temp2.getEsq());
			temp2.setEsq(temp1);
			no.setFb((byte) 0);
			if (temp2.getFb() == -1)
				no.setFb((byte) 1);
			else
				no.setFb((byte) 1);
			if (temp2.getFb() == 1)
				temp1.setFb((byte) -1);
			else
				temp1.setFb((byte) 0);
			no = temp2;
		}
		no.setFb((byte) 0);
		this.h = false;
		return no;
	}

	private NoAVL rotacaoEsquerda(NoAVL no) {
		NoAVL temp1, temp2;
		temp1 = no.getDir();
		if (no.getFb() == -1) {
			no.setDir(temp1.getEsq());
			temp1.setEsq(no);
			no.setFb((byte) 0);
			no = temp1;
		} else {
			temp2 = temp1.getEsq();
			temp1.setEsq(temp2.getDir());
			temp2.setDir(temp1);
			no.setFb((byte) 0);
			if (temp2.getFb() == 1)
				no.setFb((byte) -1);
			else
				no.setFb((byte) 0);
			if (temp2.getFb() == -1)
				temp1.setFb((byte) 1);
			else
				temp1.setFb((byte) 0);
			no = temp2;
		}
		no.setFb((byte) 0);
		this.h = false;
		return no;

	}

	public NoAVL pesquisar(String cpf) {
		return pesquisar1(cpf, raiz);
	}

	private NoAVL pesquisar(String cpf, NoAVL no) {
		if (cpf == no.getChave()) {
		} else if (no.getChave().compareTo(cpf) > 0) {
			no = pesquisar1(cpf, no.getDir());
		} else if (no.getChave().compareTo(cpf) < 0) {
			no = pesquisar1(cpf, no.getEsq());
		}
		return no;
	}

	public ArrayList<Compra> getCompras(String cpf) {
		return pesquisar1(cpf, raiz).getCompras();
	}

	public NoAVL inserir(Compra compra) {
		return inserir(compra, this.raiz);
	}

	private NoAVL inserir(Compra compra, NoAVL no) {
		if (no == null) {
			NoAVL novo = new NoAVL(compra);
			nElem++;
			return novo;
		} else {
			if (compra.getCliente().getCpf().compareTo(no.getChave()) < 0) {
				no.setEsq(inserir(compra, no.getEsq()));
			} else if (compra.getCliente().getCpf().compareTo(no.getChave()) > 0) {
				no.setDir(inserir(compra, no.getDir()));
			} else {
				no.insereCompra(compra);
			}
		}
		return no;
	}

	public ArrayList<NoAVL> fazCamCentral() {
		ArrayList<NoAVL> vetor = new ArrayList<NoAVL>(this.nElem);
		return fazCamCentral(vetor, this.raiz);
	}

	private ArrayList<NoAVL> fazCamCentral(ArrayList<NoAVL> vetor, NoAVL no) {
		if (no != null) {
			vetor = fazCamCentral(vetor, no.getEsq());
			vetor.add(no);
			vetor = fazCamCentral(vetor, no.getDir());
		}
		return vetor;
	}

}