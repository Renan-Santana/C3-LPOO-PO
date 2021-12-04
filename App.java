import java.util.Scanner;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import Archive;

public class App {

	public static void main(String[] args) throws Exception {

		ArrayList<Compra> lista = new ArrayList<Compra>();
		CadCompra cadastro = new CadCompra(lista);
		// READ FILE FINAL
		try {
			String file = Archive.Read("C:\\Users\\Renan Santana\\Documents\\C3\\C3-LPOO-PO\\files\\compra500alea.txt", cadastro);
			System.out.println(file);

		} catch (Exception e) {
			System.err.println(e.getMessage());
		}

	}
}