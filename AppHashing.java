

import java.util.ArrayList;

public class AppHashing {

	public static void main(String[] args) throws Exception {

		ArrayList<String> fileNamesPurchase = new ArrayList<>();
//		fileNamesPurchase.add("compra500ord.txt");
//		fileNamesPurchase.add("compra500inv.txt");
		fileNamesPurchase.add("compra500alea.txt");
//
//		fileNamesPurchase.add("compra1000ord.txt");
//		fileNamesPurchase.add("compra1000inv.txt");
//		fileNamesPurchase.add("compra1000alea.txt");
//
//		fileNamesPurchase.add("compra5000ord.txt");
//		fileNamesPurchase.add("compra5000inv.txt");
//		fileNamesPurchase.add("compra5000alea.txt");
//
//		fileNamesPurchase.add("compra10000ord.txt");
//		fileNamesPurchase.add("compra10000inv.txt");
//		fileNamesPurchase.add("compra10000alea.txt");
//
//		fileNamesPurchase.add("compra50000ord.txt");
//		fileNamesPurchase.add("compra50000inv.txt");
//		fileNamesPurchase.add("compra50000alea.txt");

		for (String fileName : fileNamesPurchase) {
			int hash500=557;
			ArrayList<Compra> lista = new ArrayList<Compra>();
			CadCompra cadastro = new CadCompra(lista);
			String pathCpf = "C:\\Users\\eduardo\\eclipse-workspace\\TRABALHOFINAL\\src\\trabalhofinal\\files\\compra.txt";

			String pathPurchase = "C:\\Users\\eduardo\\eclipse-workspace\\TRABALHOFINAL\\src\\trabalhofinal\\files\\" + fileName;

			Long start = System.currentTimeMillis();

			loadArchive(cadastro, pathPurchase);

			Hashing hashing500 = new Hashing(hash500);

			insertHashing(cadastro, hashing500);

			//System.out.println(avl.getnElem());

			ArrayList<String> cpfs = new ArrayList<String>();

			loadArchive(cpfs, pathCpf);
			
			System.out.println(cpfs.size());

			String content = generateArchiveFinal(hashing500, cpfs);

			Long timeSeconds = (System.currentTimeMillis() - start);

			try {
				String archive = fileName + "___" + timeSeconds;
				System.out.print("Gerando arquivo " + archive + ", aguarde!\n");
				Archive.Write(archive, content);
				System.out.print("Arquivo " + archive + " gerado\n");
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			System.out.println("runtime file " + fileName + " in MilliSeconds: " + timeSeconds + "; \n\n");
		}
	}

	public static void loadArchive(CadCompra cadastro, String path) {
		try {
			Archive.Read(path, cadastro);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void loadArchive(ArrayList<String> cpfs, String path) {
		try {
			Archive.Read(path, cpfs);
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
	}

	public static void insertHashing(CadCompra cadastro,Hashing hash) {
		for (Compra compra : cadastro.getVetCompra()) {
			hash.inserir(compra);
		}

	}

	public static String generateArchiveFinal(Hashing hashing, ArrayList<String> cpfs) {
		String content = "";
		Lista listaaux = new Lista();
		Double total = 0.0;

		for (String cpf : cpfs) {
			if (listaaux == null) {
				content += "\n\nCPF " + cpf + ":\n NAO HA NENHUMA COMPRA COM O CPF " + cpf;
			} else {

				content += "\n\n" + listaaux.pesquisa(cpf).getCliente().getCpf()+" "+listaaux.pesquisa(cpf).getCliente().getNome();
				
				total = 0.0;
				for (int i=0; i<listaaux.size();i++) {

					content += "Data: " + listaaux.pesquisa(cpf).getData() + "   Valor: R$" + listaaux.pesquisa(cpf).getValor() + "\n";
	//				total += listaaux.get(i).getValor();

				}

				content += "Total: " + total;

			}
		}

		return content;
	}

}
