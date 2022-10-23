import java.util.*;

public class Main {


    public static void resultado(ArrayList<Integer> venda, ArrayList<Integer> lucro, int qtdPratos, int qtdDias, int qtdDinheiro) {

        HashMap<Float, Integer> valorPorLucro = new HashMap<>();
        ArrayList<Float> valorPLucroSorted = new ArrayList<>();
        ArrayList<Integer> lucroA = new ArrayList<>();
        ArrayList<Float> indexLucro = new ArrayList<>();
        int soma = 0;
        int valorPrato = 0;
        int lucroPrato = 0;
        int countDias = 0;
        float lucroCmp = 0;
        int entry = 0;
        int[] pratos = new int[qtdDias];
        for (int i = 0; i < venda.size(); i++) {

            valorPrato = venda.get(i);
            lucroPrato = lucro.get(i);

            lucroA.add(lucroPrato);
            indexLucro.add((float) lucroPrato / valorPrato);
            valorPorLucro.put((float) lucroPrato / valorPrato, valorPrato); // cria hash com valor por lucro
            valorPLucroSorted.add((float) lucroPrato / valorPrato);


        }

        Collections.sort(valorPLucroSorted, Collections.reverseOrder());
        Collections.sort(lucroA, Collections.reverseOrder());

        for (float numero : valorPLucroSorted) {


            if (valorPorLucro.get(numero) >= qtdDinheiro && (qtdPratos == 1)) {
                break;
            }
            if (valorPorLucro.get(numero) * 2 > qtdDinheiro && (qtdPratos == 1)) {
                break;
            } else {

                for (float numero2 : valorPLucroSorted) {

                    int aux = 0;
                    int res = qtdDias / 2;
                    int totalPrato2 = res * valorPorLucro.get(numero2);
                    int totalPrato1 = (qtdDias - res) * valorPorLucro.get(numero);
                    float totalParcial1 = (float) ((qtdDias - (qtdDias % 2)) * (venda.get((indexLucro.indexOf(numero2)))));
                    float totalParcial2 = (qtdDias % 2) * venda.get((indexLucro.indexOf(numero)));
                    float lucroCalculado = 0;


                    if (totalParcial2 + totalParcial1 < qtdDinheiro && numero != numero2 && qtdDias % 2 != 0) {

                        lucroCalculado = (float) ((qtdDias - (qtdDias % 2)) * (lucro.get((indexLucro.indexOf(numero2))) * 1.5)) / 2 + ((qtdDias % 2) * lucro.get((indexLucro.indexOf(numero))));

                        if (lucroCalculado > lucroCmp) {

                            lucroCmp = lucroCalculado;

                            for (int j = 0; j < qtdDias; j++) {
                                if (aux < 2) {
                                    pratos[j] = (indexLucro.indexOf(numero2) + 1);
                                    aux++;
                                } else {
                                    pratos[j] = indexLucro.indexOf(numero) + 1;
                                    aux = 0;
                                }

                            }
                        }

                    }
                    if (totalPrato1 + totalPrato2 <= qtdDinheiro) {

                        if (numero == numero2 && qtdDias > 1) {
                            lucroCalculado = (float) (lucro.get((indexLucro.indexOf(numero2))) * 1.5);

                        } else {
                            lucroCalculado = (res * lucro.get(indexLucro.indexOf(numero2)) + (qtdDias - res) * lucro.get(indexLucro.indexOf(numero)));
                        }
                        if (lucroCalculado > lucroCmp) {

                            lucroCmp = lucroCalculado;

                            for (int j = 0; j < qtdDias; j++) {

                                if (aux == 0) {
                                    pratos[j] = (indexLucro.indexOf(numero) + 1);
                                    aux = 1;
                                } else {
                                    pratos[j] = (indexLucro.indexOf(numero2) + 1);
                                    aux = 0;
                                }
                            }

                        }

                    }

                }

            }
        }
        // verifica o resultado
      if(qtdDias % 2 == 0){

        int[] aux = pratos;
        int index = pratos[pratos.length-1];
        int somaVenda = 0;

        if(index -1 > 0) {
            for (int lucroC : lucro) {
                somaVenda = 0;
                float somatorio = 0;
                somatorio = (lucroCmp - lucro.get(index - 1)) + lucroC;
                aux[pratos.length - 1] = lucro.indexOf(lucroC) + 1;
                for (int valor : aux) {
                    somaVenda += venda.get(valor - 1);
                }
                if (lucro.get(index - 2) != lucroC && somatorio > lucroCmp && somaVenda <= qtdDinheiro) {

                    pratos[pratos.length - 1] = lucro.indexOf(lucroC) + 1;
                    lucroCmp = somatorio;


                }


            }
        }

      }



        // print do resultado final
        System.out.println(lucroCmp);
        if (lucroCmp != 0) {
            for (int numPrato : pratos) {
                System.out.print(" " + numPrato + " ");
            }
            System.out.println("");
        }
    }



    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        ArrayList<String> arg = new ArrayList<>();
        Scanner scan = new Scanner(System.in);
        int count = 0;
        String input = "";
        int i = 0;

        input = scan.nextLine();
        arg.add(input);

        while (!input.equals("0 0 0")) {

            input = scan.nextLine();
            arg.add(input);
        }


        if (arg.get(count).split(" ").length != 3) {

            System.out.printf("Nao eh possivel realizar essa operacao!");

        } else {

            int z = 0;
            int dia = 0;
            int qtdPratos = 0;
            int dinheiro = 0;
            ArrayList<Integer> lucro = new ArrayList<>();
            ArrayList<Integer> venda = new ArrayList<>();

            for (String val : arg) {


                if ((val.split(" ").length == 3) && !(val.equals("0 0 0"))) {
                    lucro.clear();
                    venda.clear();
                    String[] comando = val.split(" ");
                    dia = Integer.parseInt(comando[0]);
                    qtdPratos = Integer.parseInt(comando[1]);
                    dinheiro = Integer.parseInt(comando[2]);
                    z = qtdPratos;

                }

                if (val.split(" ").length == 2) {
                    String[] valores = val.split(" "); // pos 0 valor gasto, pos 1 lucro
                    venda.add(Integer.valueOf(valores[0]));
                    lucro.add(Integer.valueOf(valores[1]));
                    z--;
                    if (z == 0) {
                        resultado(venda, lucro, qtdPratos, dia, dinheiro);
                    }
                }


            }

        }

    long endTime = System.currentTimeMillis();
        System.out.println("Tempo: " + (endTime - startTime));
    }
}