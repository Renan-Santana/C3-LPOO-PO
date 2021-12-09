import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class AppAVL {

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

			ArrayList<Compra> lista = new ArrayList<Compra>();
			CadCompra cadastro = new CadCompra(lista);
			String pathCpf = "C:\\Users\\12000226\\Documents\\C3\\C3-LPOO-PO\\files\\compra.txt";

			String pathPurchase = "C:\\Users\\12000226\\Documents\\C3\\C3-LPOO-PO\\files\\" + fileName;

			Long start = System.currentTimeMillis();

			loadArchive(cadastro, pathPurchase);

			AVL avl = new AVL();

			insertAVL(cadastro, avl);

			System.out.println(avl.getnElem());

			ArrayList<String> cpfs = new ArrayList<String>();

			loadArchive(cpfs, pathCpf);
			
			System.out.println(cpfs.size());

			String content = generateArchiveFinal(avl, cpfs);

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

	public static void insertAVL(CadCompra cadastro, AVL avl) {
		for (Compra compra : cadastro.getVetCompra()) {
			avl.inserir(compra);
		}

	}

	public static String generateArchiveFinal(AVL avl, ArrayList<String> cpfs) {
		String content = "";
		NoAVL nodeAux = new NoAVL();
		Double total = 0.0;

		for (String cpf : cpfs) {
			nodeAux = avl.pesquisar(cpf);
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

}
