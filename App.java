import java.util.Scanner;

import java.io.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class App {

	public static void main(String[] args) throws Exception {

		ArrayList<Compra> lista = new ArrayList<Compra>();
		CadCompra cadastro = new CadCompra(lista);
		String path = "C:\\Users\\Renan Santana\\Documents\\C3\\C3-LPOO-PO\\files\\compra500alea.txt";

		loadArchive(cadastro, path);
		for (Compra compra : cadastro.getVetCompra()) {
			System.out.println(compra.toString());
		}

		Abb abb = new Abb();
		insertAbb(cadastro, abb);
		balanceAbb(abb);

		ArrayList<String> cpfs = new ArrayList<String>();
		path = "C:\\Users\\Renan Santana\\Documents\\C3\\C3-LPOO-PO\\files\\compra.txt";
		loadArchive(cpfs, path);

		for (String string : cpfs) {
			System.out.println(string);
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
		for (Compra compra : cadastro.getVetCompra()) {
			abb.inserir(compra);
		}
	}

	public static void balanceAbb(Abb abb) {
		abb.balancear();
	}


}