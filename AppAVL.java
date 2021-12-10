import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class AppAVL {

	public static void main(String[] args) throws Exception {

		ArrayList<String> fileNamesPurchase = new ArrayList<>();
		fileNamesPurchase.add("compra500ord");
//		fileNamesPurchase.add("compra500inv");
//		fileNamesPurchase.add("compra500alea");
//
//		fileNamesPurchase.add("compra1000ord");
//		fileNamesPurchase.add("compra1000inv");
//		fileNamesPurchase.add("compra1000alea");
//
//		fileNamesPurchase.add("compra5000ord");
//		fileNamesPurchase.add("compra5000inv");
//		fileNamesPurchase.add("compra5000alea");
//
//		fileNamesPurchase.add("compra10000ord");
//		fileNamesPurchase.add("compra10000inv");
//		fileNamesPurchase.add("compra10000alea");
//
//		fileNamesPurchase.add("compra50000ord");
//		fileNamesPurchase.add("compra50000inv");
//		fileNamesPurchase.add("compra50000alea");

		String resultFinal = "";

		for (String fileName : fileNamesPurchase) {

			ArrayList<Compra> lista = new ArrayList<Compra>();
			CadCompra cadastro = new CadCompra(lista);
			String pathCpf = "C:\\Users\\12000226\\Documents\\C3\\C3-LPOO-PO\\files\\compra.txt";

			String pathPurchase = "C:\\Users\\12000226\\Documents\\C3\\C3-LPOO-PO\\files\\" + fileName + ".txt";

			Long start = System.currentTimeMillis();

			loadArchive(cadastro, pathPurchase);

			AVL avl = new AVL();

			insertAVL(cadastro, avl);

			ArrayList<String> cpfs = new ArrayList<String>();

			loadArchive(cpfs, pathCpf);

			String content = generateArchiveFinal(avl, cpfs);

			Long timeSeconds = (System.currentTimeMillis() - start);

			try {
				String archive = fileName + "___" + timeSeconds + "___AVL.txt";
				System.out.print("Gerando arquivo " + archive + ", aguarde!\n");
				Archive.Write("C:\\Users\\12000226\\Documents\\C3\\C3-LPOO-PO\\fileResults\\withAvl\\" + archive,
						content);
				System.out.print("Arquivo " + archive + " gerado\n");
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}

			String result = "runtime file " + fileName + " in MilliSeconds with AVL: " + timeSeconds + " \n\n";
			System.out.println(result);
			resultFinal += result;
		}

		try {
			Archive.Write(
					"C:\\Users\\12000226\\Documents\\C3\\C3-LPOO-PO\\fileResults\\resultFinal\\Resultados AVL.txt",
					resultFinal);
		} catch (Exception e) {
			System.err.println(e.getMessage());
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
