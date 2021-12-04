package system;

import java.util.Calendar;

public interface vetor {
	public Compra get(Integer pos);

	public void insere(Compra compra);

	public void remove(String cpf, Calendar data);
}
