import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.MonthDay;
import java.time.Year;
import java.util.*;


public class LeArquivo {
	private Scanner entrada;

	ArrayList<Compra> lista = new ArrayList<Compra>();
	CadCompra cadastro =new CadCompra(lista);

	/**
	 * Construtor
	 * @param nome => Nome do arquivo que sera aberto para lei0tura
	 * @throws FileNotFoundException => Excecao se nao encontrar o arquivo
	 */
	public LeArquivo (String nome) throws FileNotFoundException{
		try{
			this.entrada = new Scanner (new File (nome));
		}
		catch (FileNotFoundException e){
			throw new FileNotFoundException ("ARQUIVO NAO ENCONTRADO");
		}
	}
	/**
	 * Metodo para ler os dados contidos no arquivo
	 * @return Vetor de Contas que serao lidas nesse metodo
	 * @throws NoSuchElementException
	 */
	public CadCompra Le ()throws ArrayIndexOutOfBoundsException, 
	NumberFormatException{

		String linha;
		// Enquanto tiver informacao na linha do cursor ele roda o while
		while (this.entrada.hasNext()){
			linha = this.entrada.nextLine();// pega a linha toda e joga na variavel linha
			Compra aux = separaDados(linha);
			cadastro.insere(aux);
			// separa os dados e adiciona na lista de contas
		}
		return cadastro;
	}

	/**
	 * Metodo para transformar uma linha do arquivo em um objeto
	 * da classe Conta
	 * @param linha => String contendo a linha do arquivo que sera transformada
	 * @param  
	 * @return => A conta criada a partir do linha passada
	 * @throws NoSuchElementException => Excecao causada por elementos insuficientes
	 * 					na String, durante a transformacao
	 * @throws NumberFormatException => Excecao causada por transformar uma String
	 * 					que nao tem apenas digitos em inteiro
	 */
	private Compra separaDados (String linha )throws
	NoSuchElementException{
		String[] dados;
		String CPF, aux;
		Calendar data = Calendar.getInstance();
		double valor, cupom;
		Compra compra;
		Cliente cli;
		ClienteEspecial cliesp;

		try{
			dados = linha.split(";");

			CPF = dados[1];

			if(dados.length == 5) {	
				aux = dados[3];
				data=montaData(aux);
				
				cupom = Double.parseDouble(dados[2]);
				valor = Double.parseDouble(dados[4]);
				cliesp = new ClienteEspecial(dados[0], dados[1], cupom);
				compra = new Compra(cliesp, data, valor);

			}else {
				cupom = 0;
				data=montaData(dados[2]);
				valor = Double.parseDouble(dados[3]);
				cli = new Cliente(dados[0], dados[1]);
				compra = new Compra(cli, data, valor);
			}

		}
		catch (NoSuchElementException erro){
			throw new NoSuchElementException ("ARQUIVO DIFERENTE DO REGISTRO");
		}
		return compra;
	}

	/**
	 * Metodo para separar o dia, mes e o ano de uma data.
	 * Eles estao separados por / de cria uma data com
	 * esses inteiros.
	 * @param str => String contendo dd/mm/aa
	 * @return um objeto do tipo Calendar com a data.
	 */

	protected static Calendar montaData (String str){
		String[] aux;
		Calendar data = Calendar.getInstance();
		aux = str.split("/");	
		int ano, mes, dia;
		dia = Integer.parseInt(aux[0]);
		mes = Integer.parseInt(aux[1])-1;
		ano = Integer.parseInt(aux[2]);	
		data.set(ano, mes, dia);
			return data;
		
	}

	/**
	 * Metodo para fechar o arquivo de leitura
	 * @throws IllegalStateException => Excecao causada se nao conseguir fechar o arquivo.
	 */
	public void fecha ()throws IllegalStateException{
		try{
			this.entrada.close();
		}
		catch (IllegalStateException e){
			throw new IllegalStateException ("ERRO AO FECHAR O ARQUIVO");
		}
	}
}