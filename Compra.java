import java.util.Calendar;

public class Compra {
	private Cliente cliente;
	private Calendar data;
	private Double valor;

	public Compra(Cliente cliente, Calendar data, Double valor) {
		super();
		this.cliente = cliente;
		this.data = data;
		this.valor = valor;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public String getDataString() {

		Integer dia = data.get(Calendar.DAY_OF_MONTH);
		Integer mes = data.get(Calendar.MONTH) + 1;
		Integer ano = data.get(Calendar.YEAR);

		return dia + "/" + mes + "/" + ano;
	}

	@Override
	public String toString() {
		return "Compra Cliente " + cliente + ", data = " + data.getTime() + ", valor = " + valor + "\n";
	}

}