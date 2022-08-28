package exercicio1;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Motoqueiros {
	private static ArrayList<Integer> tempoEntrega;

	public static void setTempoEntrega(ArrayList<Integer> valores){
		tempoEntrega = valores;
	}
	public static ArrayList<Integer> sum_up_recursive(ArrayList<Integer> num, int somaAlvo, ArrayList<Integer> valoresEntrega) {
		int s = 0;
		for (int x: valoresEntrega) s += x;
		if (s == somaAlvo) {
			setTempoEntrega(valoresEntrega);
			return valoresEntrega;
		}
		if (s >= somaAlvo) {
			return valoresEntrega;
		}
		for(int i=0;i<num.size();i++) {
			ArrayList<Integer> resto = new ArrayList<Integer>();
			int n = num.get(i);
			for (int j=i+1; j < num.size();j++) resto.add(num.get(j));
			ArrayList<Integer> parcialCmp = new ArrayList<Integer>(valoresEntrega);
			parcialCmp.add(n);
			sum_up_recursive(resto,somaAlvo,parcialCmp);
		}
		return valoresEntrega;
	}
	public static ArrayList<Integer> sum_up(ArrayList<Integer> num, int valoresEntrega) {
		return  sum_up_recursive(num,valoresEntrega,new ArrayList<Integer>());

	}

	public static int soma(ArrayList<Integer> somado){
		int soma = 0;

		for (int valor : somado ){
			soma += valor;
		}
		return soma;
	}
	public static void escreveArquivo(ArrayList<Integer> tempoEntregador1, ArrayList<Integer> tempoEntregador2, int melhorTempo) throws IOException {
		BufferedWriter escrever = new BufferedWriter(new FileWriter("respostas.txt"));
		escrever.write(" ==> Melhor Tempo: " + melhorTempo + "\n");
		escrever.write("Moto 1 \n");

		for (int i = 0; i< tempoEntregador1.size(); i++){
			if(i < tempoEntregador1.size()-1) {
				escrever.write(tempoEntregador1.get(i) / 2 + "\n");
			}
			else {
				escrever.write(tempoEntregador1.get(i) + "\n");
			}
		}
		escrever.write("Moto 2 \n");

		for (int moto2 : tempoEntregador2){
			escrever.write(moto2 + "\n");
		}
		escrever.close();
	}
	public static void main(String[] args) {
		// tempos dos motoqueiros
		int tempo1 = 10;
		int tempo2 = 0;
		int somaArray = 0;
		int divisaoMotoqueiros = 0;
		int resto = 0;
		try {
		
		// criando instancias p/ ler e armazenar o arquivos 
		File arquivo = new File("Exer1/exemplo.txt");
		Scanner scan = new Scanner(arquivo);
		ArrayList<Integer> tempos = new ArrayList();

		// lendo o arquivo
		while(scan.hasNext())
		{
			tempos.add(scan.nextInt());
		}
			
		// verificando possibilidades para os motoqueiros
		tempos.sort(null);

		for (int i = 0; i< tempos.size()-2; i++)
		{
			int x = tempos.remove(i);
			tempos.add(i,x*2);
		}
		somaArray = soma(tempos);
		divisaoMotoqueiros = somaArray/2;
		resto = somaArray - divisaoMotoqueiros;

		System.out.println(divisaoMotoqueiros + " "+ resto );
		ArrayList<Integer> entrega2 = sum_up(tempos,divisaoMotoqueiros);

		for (int tempo : tempoEntrega){
			int index = tempos.indexOf(tempo);
			tempos.remove(index);
		}
		System.out.println(tempos);
		System.out.println(tempoEntrega);

		escreveArquivo(tempoEntrega, tempos, resto);

		}catch (FileNotFoundException f){
			System.out.println("Caminho nao encontrado");
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
}
