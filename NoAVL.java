import java.util.ArrayList;

public class NoAVL {
	private byte fb;
	private String chave;
	private ArrayList<Compra> compras;
	private NoAVL esq, dir;
	
	public NoAVL(Compra compra) {
		this.chave = compra.getCliente().getCpf();
		compras = new ArrayList<Compra> ();
		compras.add(compra);
	}

	public NoAVL (ArrayList<Compra> compras) {
		this.compras = compras;
	}
	public NoAVL() {
		this.compras = null;	
	}
	protected byte getFb() {
		return this.fb;
	}
	protected void setFb(byte fb) {
		this.fb = fb;
	}
	public NoAVL getEsq() {
		return esq;
	}
	public void setEsq(NoAVL esq) {
		this.esq = esq;
	}
	public NoAVL getDir() {
		return dir;
	}
	public void setDir(NoAVL dir) {
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
		String frase = null;
		for(int i = 0 ; i < compras.size();i++) {
			frase += compras.get(i).getCliente().getCpf() + "\n";
		}
		return frase;
	}
	
				
	public boolean insereCompra(Compra compra) {
		if (compra.getCliente().getCpf().equals(chave)) {
			compras.add(compra);
			return true;
		}else {
			return false;
		}
	}

	public Compra getCompra (NoAVL no, int i) {
		return no.getCompras().get(i);
	}
	
}
