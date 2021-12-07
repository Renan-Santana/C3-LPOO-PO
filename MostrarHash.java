package system;
import java.util.Scanner;
import repository.MapaHash;
import repository.Registro;

public class MostrarHash {
	public static void main(String[] args) {
		
		Scanner teclado = new Scanner(System.in);
		MapaHash mapa = new MapaHash();
		
		String texto;
		int chave;
		int continua;
		Registro r;
		
		
		do {
			
			System.out.println("Digite uma chave");
			texto = teclado.next();
			chave = Integer.parseInt(texto);
			System.out.println("Digite um valor");
			texto = teclado.next();
			
			r = new Registro(chave, texto);
			mapa.put(r);
	
			System.out.println("Continua? (1-Sim / 0-Não)");
			continua = Integer.parseInt(teclado.next());
			
		} while (continua != 0);
		
		
		System.out.println("=================================================================");
		do {
			
			System.out.println("Digite uma chave");
			chave = Integer.parseInt(teclado.next());
			r = mapa.get(chave);
			
			if(r != null) {
				System.out.println("Registro = "+r.getValue());
			} else {	
				System.out.println("Não existe");
			}		
			
		 }while(chave != -1);
		
		

	}

}
