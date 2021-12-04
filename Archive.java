import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;

public class Archive {

    public static String Read(String path, CadCompra cadCompra) {
        String content = "";
        try {
            FileReader archive = new FileReader(path);
            BufferedReader readArchive = new BufferedReader(archive);
            String line = "";
            try {
                line = readArchive.readLine();
                while (line != null) {
                    content += line + "\n";
                    cadCompra.insere(separaDados(line));
                    line = readArchive.readLine();
                }
                archive.close();
            } catch (IOException e) {
                content = "Error: Error reading file";
            }
        } catch (FileNotFoundException e) {
            content = "Error: File not found";
        }
        if (content.contains("Error")) {
            System.err.println(content);
            return "";
        } else {
            return content;
        }
    }

    public static String Read(String path, ArrayList<String> cpfs) {
        String content = "";
        try {
            FileReader archive = new FileReader(path);
            BufferedReader readArchive = new BufferedReader(archive);
            String line = "";
            try {
                line = readArchive.readLine();
                while (line != null) {
                    content += line + "\n";
                    cpfs.add(line);
                    line = readArchive.readLine();
                }
                archive.close();
            } catch (IOException e) {
                content = "Error: Error reading file";
            }
        } catch (FileNotFoundException e) {
            content = "Error: File not found";
        }
        if (content.contains("Error")) {
            System.err.println(content);
            return "";
        } else {
            return content;
        }
    }

    public static Boolean Write(String path, String content) {
        try {
            FileWriter archive = new FileWriter(path);
            PrintWriter recordArchive = new PrintWriter(archive);
            recordArchive.println(content);
            recordArchive.close();
            return true;
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }

    public static Compra separaDados(String linha) throws ArrayIndexOutOfBoundsException, NumberFormatException {
        String[] dados = null;
        String CPF;
        Calendar data;
        double valor, cupom;
        Compra compra;
        Cliente cli;
        ClienteEspecial cliesp;

        try {
            dados = linha.split(";");

            CPF = dados[1];

            if (dados.length == 5) {
                data = montaData(dados[3]);
                cupom = Double.parseDouble(dados[2]);
                valor = Double.parseDouble(dados[4]);
                cliesp = new ClienteEspecial(dados[0], dados[1], cupom);
                compra = new Compra(cliesp, data, valor);

            } else {
                cupom = 0;
                data = montaData(dados[2]);
                valor = Double.parseDouble(dados[3]);
                cli = new Cliente(dados[0], dados[1]);
                compra = new Compra(cli, data, valor);

            }

        } catch (ArrayIndexOutOfBoundsException erro) {
            throw new ArrayIndexOutOfBoundsException("REGISTRO TEM " + dados.length + " INFORMACOES");
        } catch (NumberFormatException erro) {
            throw new NumberFormatException("NUMERO DA CONTA NAO E INTEIRO");
        }
        return compra;
    }

    protected static Calendar montaData(String str) {
        int dia, mes, ano;
        String[] aux;
        Calendar data = Calendar.getInstance();
        aux = str.split("/");
        dia = Integer.parseInt(aux[0]);
        mes = Integer.parseInt(aux[1]) - 1;
        ano = Integer.parseInt(aux[2]);
        data.set(ano, mes, dia);
        return data;
    }

}
