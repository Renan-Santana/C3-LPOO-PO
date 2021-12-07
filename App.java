import java.util.Scanner;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class App {

	public static void main(String[] args) throws Exception {

		ArrayList<Compra> lista = new ArrayList<Compra>();
		CadCompra cadastro = new CadCompra(lista);
		String pathPurchase = "C:\\Users\\Renan Santana\\Documents\\C3\\C3-LPOO-PO\\files\\compra500alea.txt";

		loadArchive(cadastro, pathPurchase);

//		for (Compra compra : cadastro.getVetCompra()) {
//			System.out.println(compra.toString());
//		}

		Abb abb = new Abb();

		insertAbb(cadastro, abb);

		balanceAbb(abb);

		ArrayList<String> cpfs = new ArrayList<String>();

		String pathCpf = "C:\\Users\\Renan Santana\\Documents\\C3\\C3-LPOO-PO\\files\\compra.txt";
		loadArchive(cpfs, pathCpf);

//		for (String string : cpfs) {
//			System.out.println(string);
//		}

		System.out.println(generateArchiveFinal(abb, cadastro));

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

	public static String generateArchiveFinal(Abb abb, CadCompra cadastro) {
		String content = "";
		Noabb nodeAux = new Noabb();

		for (Compra compra : cadastro.getVetCompra()) {
			if (abb.pesquisar(compra.getCliente().getCpf()) != null) {
				content += "\n\n\n" + compra.getCliente().getCpf() + "\t" + compra.getCliente().getNome() + "\n"
						+ compra.getDataString() + "  Valor:" + compra.getValor();
			} else {
				content += "\n\n\n" + compra.getCliente().getCpf() + "\n" + "NAO HA NENHUMA COMPRA COM O CPF";
			}

		}

		return content;
	}

}