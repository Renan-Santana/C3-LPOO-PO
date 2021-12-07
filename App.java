import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class App {

	public static void main(String[] args) throws Exception {

		ArrayList<String> fileNamesPurchase = new ArrayList<>();
		fileNamesPurchase.add("compra500ord.txt");
		fileNamesPurchase.add("compra500inv.txt");
		fileNamesPurchase.add("compra500alea.txt");

		fileNamesPurchase.add("compra1000ord.txt");
		fileNamesPurchase.add("compra1000inv.txt");
		fileNamesPurchase.add("compra1000alea.txt");

		fileNamesPurchase.add("compra5000ord.txt");
		fileNamesPurchase.add("compra5000inv.txt");
		fileNamesPurchase.add("compra5000alea.txt");

		fileNamesPurchase.add("compra10000ord.txt");
		fileNamesPurchase.add("compra10000inv.txt");
		fileNamesPurchase.add("compra10000alea.txt");

		fileNamesPurchase.add("compra50000ord.txt");
		fileNamesPurchase.add("compra50000inv.txt");
		fileNamesPurchase.add("compra50000alea.txt");

		ArrayList<Compra> lista = new ArrayList<Compra>();
		CadCompra cadastro = new CadCompra(lista);
		String pathCpf = "C:\\Users\\Renan Santana\\Documents\\C3\\C3-LPOO-PO\\files\\compra.txt";

		for (String fileName : fileNamesPurchase) {

			String pathPurchase = "C:\\Users\\Renan Santana\\Documents\\C3\\C3-LPOO-PO\\files\\" + fileName;

			Long start = System.currentTimeMillis();

			loadArchive(cadastro, pathPurchase);

			Abb abb = new Abb();

			insertAbb(cadastro, abb);

			balanceAbb(abb);

			ArrayList<String> cpfs = new ArrayList<String>();

			loadArchive(cpfs, pathCpf);

			String content = generateArchiveFinal(abb, cpfs);

			Long timeSeconds = (System.currentTimeMillis() - start);

			try {
				String archive = fileName + "___" + timeSeconds;
				Archive.Write(archive, content);
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			System.out.println("runtime file " + fileName + "in MilliSeconds: " + timeSeconds + "; \n\n");
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

	public static void insertAbb(CadCompra cadastro, Abb abb) {
		String content = "";
		Noabb nodeAux = new Noabb();

		for (Compra compra : cadastro.getVetCompra()) {
			abb.inserir(compra);

		}

	}

	public static void balanceAbb(Abb abb) {
		abb.balancear();
	}

	public static String generateArchiveFinal(Abb abb, ArrayList<String> cpfs) {
		String content = "";
		Noabb nodeAux = new Noabb();
		Double total = 0.0;

		for (String cpf : cpfs) {
			nodeAux = abb.pesquisar(cpf);
			if (nodeAux == null) {
				content += "\n\nCPF " + cpf + ":\n NAO HA NENHUMA COMPRA COM O CPF " + cpf;
			} else {

				content += "\n\n" + nodeAux.getCompra(nodeAux, 0).getCliente().getCpf() + "\t"
						+ nodeAux.getCompra(nodeAux, 0).getCliente().getNome() + "\n";

				total = 0.0;
				for (Compra compra : nodeAux.getCompras()) {

					content += "Data: " + compra.getDataString() + "   Valor: R$" + compra.getValor() + "\n";
					total += compra.getValor();

				}

				content += "Total: " + total;

			}
		}

		return content;
	}

	public static void start() {

	}
}