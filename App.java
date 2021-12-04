package system;

import java.util.Scanner;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;

public class App {
	
		static ArrayList<Compra> lista = new ArrayList<Compra>() ;
		static CadCompra cadcompra = new CadCompra(lista);
		
		public static void main (String[] args)throws Exception {
			
			// TODO Auto-generated method stub

			String file = "D:\\FAESA\\4° SEMESTRE\\Pesquisa e Ordenacao\\trabalho\\compra500alea.txt";
			String file2 = "D:\\FAESA\\4° SEMESTRE\\Pesquisa e Ordenacao\\trabalho\\testacompra500alea.txt";
				LeArquivo learquivo = new LeArquivo(file);
				GravaArquivo gravarquivo = new GravaArquivo(file2);
				
					cadcompra.insercaoDireta();				
	
					System.out.println(learquivo.Le());
					gravarquivo.gravaArquivo(file2);		

					for (String string : args) {
						
					}
					cadcompra.insercaoDireta();
					System.out.println("___________________________");				
				}
			}