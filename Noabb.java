import java.util.ArrayList;

public class Noabb {

	private String chave;
	private ArrayList<Compra> compras;
	private Noabb esq, dir;

	public Noabb(Compra compra) {

		this.chave = compra.getCliente().getCpf();
		compras = new ArrayList<Compra>();
		compras.add(compra);
	}

	public Noabb(ArrayList<Compra> compras) {
		this.compras = compras;
	}

	public Noabb() {
		this.compras = null;
	}

	public Noabb getEsq() {
		return esq;
	}

	public void setEsq(Noabb esq) {
		this.esq = esq;
	}

	public Noabb getDir() {
		return dir;
	}

	public void setDir(Noabb dir) {
		this.dir = dir;
	}

	public ArrayList<Compra> getCompras() {
		return compras;
	}

	public String getChave() {
		return chave;
	}

	@Override
	public String toString() {
		return "NoABB [compra=" + compras + ", esq=" + esq + ", dir=" + dir + "]";
	}

	public boolean insereCompra(Compra compra) {
		if (compra.getCliente().getCpf().equals(this.chave)) {
			compras.add(compra);
			return true;
		} else {
			return false;
		}
	}

	public Compra getCompra(Noabb no, int i) {
		return no.getCompras().get(i);
	}
}
