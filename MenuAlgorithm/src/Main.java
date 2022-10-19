import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {



    public static void resultado(ArrayList<Integer> venda, ArrayList<Integer> lucro, int qtdPratos, int qtdDias, int qtdDinheiro){

        HashMap<Float,Integer> valorPorLucro = new HashMap<>();
        ArrayList<Float> valorPLucroSorted = new ArrayList<>();
        ArrayList<Integer> lucroA = new ArrayList<>();
        ArrayList<Float> indexLucro = new ArrayList<>();
        int soma = 0;
        int valorPrato = 0;
        int lucroPrato  = 0;
        int countDias = 0;

        for(int i = 0; i< venda.size(); i++){

            valorPrato = venda.get(i);
            lucroPrato = lucro.get(i);

            lucroA.add(lucroPrato);
            indexLucro.add((float)lucroPrato/valorPrato);
            valorPorLucro.put((float)lucroPrato/valorPrato, valorPrato); // cria hash com valor por lucro
            valorPLucroSorted.add((float)lucroPrato/valorPrato);

            }

        Collections.sort(valorPLucroSorted, Collections.reverseOrder());
        Collections.sort(lucroA, Collections.reverseOrder());

        for (float numero: valorPLucroSorted){

            countDias = 0;
            soma = valorPorLucro.get(numero);

            if(valorPorLucro.get(numero) >= qtdDinheiro  && (qtdPratos == 1)){

                System.out.println("0.0");

            }
            if (valorPorLucro.get(numero)*2 > qtdDinheiro && (qtdPratos == 1)){
                System.out.println("0.0");
            }
                else{

                for (float numero2: valorPLucroSorted) {

                    if ((valorPorLucro.get(numero) + (valorPorLucro.get(numero2))) < qtdDinheiro
                            && (valorPorLucro.get(numero) == valorPorLucro.get(numero2))
                            && lucro.get(indexLucro.indexOf(numero2)) == lucro.get(indexLucro.indexOf(numero)) ){

                        System.out.println("soma iguais: " + (valorPorLucro.get(numero) + valorPorLucro.get(numero2)/2));

                    }
                    if (soma + valorPorLucro.get(numero2) <= qtdDinheiro && countDias <= qtdDias){

                        if (countDias % 2 != 0){

                            System.out.println("Lucro diferente: " + lucro.get(indexLucro.indexOf(numero2)) +" " +  lucro.get(indexLucro.indexOf(numero)));

                            int aux = 0;
                            int res = qtdDias/2;
                            int totalPrato2 = res* valorPorLucro.get(numero2);
                            int totalPrato1 = (qtdDias - res) * valorPorLucro.get(numero);

                            if(totalPrato1 + totalPrato2 <= qtdDinheiro) {

                                System.out.println("Total: " +(totalPrato1 + totalPrato2) );
                                System.out.println("Lucro: " + (res*lucro.get(indexLucro.indexOf(numero2)) + (qtdDias -res)*lucro.get(indexLucro.indexOf(numero))));

                                for (int j = 1; j <= qtdDias; j++) {

                                    if (aux == 0) {
                                        System.out.print(" " + (indexLucro.indexOf(numero) + 1) + " ");
                                        aux = 1;
                                    } else {
                                        System.out.print(" " + (indexLucro.indexOf(numero2) + 1 ) + " ");
                                        aux = 0;
                                    }
                                }
                            }

                            System.out.println("");

                            }
                        countDias++;
                        soma+= valorPorLucro.get(numero2);
                    }
                }

            }
        }

    }

    public static void main(String[] args)
    {
        ArrayList<String> arg = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int count = 0;
        String input = "";
        int i = 0;

        input = scan.nextLine();
        arg.add(input);

        while(!input.equals("0 0 0")){

            input = scan.nextLine();
            arg.add(input);
        }


        if(arg.get(count).split(" ").length != 3) {

            System.out.printf("Nao eh possivel realizar essa operacao!");

        }else{

        int z = 0;
        int dia = 0;
        int qtdPratos = 0;
        int dinheiro = 0;
        ArrayList<Integer> lucro = new ArrayList<>();
        ArrayList<Integer> venda = new ArrayList<>();

            for(String val: arg) {


                if ((val.split(" ").length == 3) && !(val.equals("0 0 0"))) {
                    lucro.clear();
                    venda.clear();
                    String[] comando = val.split(" ");
                    dia = Integer.parseInt(comando[0]);
                    qtdPratos = Integer.parseInt(comando[1]);
                    dinheiro = Integer.parseInt(comando[2]);
                    z = qtdPratos;

                }

                if(val.split(" ").length == 2){
                    String[] valores = val.split(" "); // pos 0 valor gasto, pos 1 lucro
                    venda.add(Integer.valueOf(valores[0]));
                    lucro.add(Integer.valueOf(valores[1]));
                    z--;
                    if(z == 0){
                        System.out.println("qtd pratos: " + qtdPratos);
                        resultado(venda,lucro,qtdPratos, dia, dinheiro);
                    }
                }


            }

        }



    }
}