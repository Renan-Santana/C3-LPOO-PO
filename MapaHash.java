package repository;

public class MapaHash {

	
	private Registro valores[];
	
	public MapaHash() {
		valores = new Registro[100];
	}
	
	public void put(Registro registro) {
		
		int posicao = registro.hashCode();
		if(valores[posicao] == null){
			valores[posicao] = registro;
			System.out.println("Posição vazia!!. Novo Registro");
			return;
		}
		else {
			System.out.println("Ops...colisão!");
			Registro reg = valores[posicao];
			
			
			// Especial para tratar o primero elemento.
			if(reg.getKey() == registro.getKey()) 
			{
				reg.setValue(registro.getValue());
				System.out.println("É o primeiro registro existente - atualizando...");
				return;
			} 
			//Se ele está no meio da lista.
			while(reg.getProximo() != null) {
				if(reg.getKey() == registro.getKey()) {
					reg.setValue(registro.getValue());
					System.out.println("Tá no meio da lista Registro existente - atualizando.");
					return;
				} 	
				reg = reg.getProximo();
			}	
			
			//Se ele é o ultimo da lista.
			if(reg.getProximo() == null) {
				if(reg.getKey() == registro.getKey()) {
					reg.setValue(registro.getValue());
					System.out.println("É o ultimo! Registro Existente - atualizando...");
					return;
				}
			}
			
			reg.setProximo(registro);
			System.out.println("Novo Regsitro");
		}
	}
	
	public Registro get(int key) {
		
		Registro r = new Registro();
		r.setKey(key);
		int posicao = r.hashCode();	
		Registro resultado = valores[posicao];
		if(resultado != null && resultado.getKey() == key) 
		{
			return resultado;
		}
		else {
			
			while(resultado.getProximo() != null) {
				resultado = resultado.getProximo();
				if(resultado !=null && resultado.getKey() == key) {
					return resultado;
				}
			}	
		}
		
		return null;
	}
	
}
