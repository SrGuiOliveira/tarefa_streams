package tarefa_streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Pessoa> lista = new ArrayList<>();

        while (true) {
            System.out.println("\nDigite nome e sexo separados por vírgula (ex: 'Ana, F') ou 'sair' para encerrar:");
            String entrada = scanner.nextLine().trim();

            if (entrada.equalsIgnoreCase("sair")) {
                exibirResumoFinal(lista);
                break;
            }

            String[] partes = entrada.split(",");
            if (partes.length != 2) {
                System.out.println("Formato inválido. Use o padrão 'Nome, Sexo'.");
                continue;
            }

            String nome = partes[0].trim();
            String sexo = normalizarSexo(partes[1].trim());

            if (sexo == null) {
                System.out.println("Sexo não reconhecido. Use 'M' ou 'F'.");
                continue;
            }

            lista.add(new Pessoa(nome, sexo));
            System.out.println("\n" + nome + " foi adicionado ao sistema.");

            exibirLista("Lista de mulheres no Sistema", filtrarPorSexo(lista, "f"));
            exibirLista("Lista de homens no Sistema", filtrarPorSexo(lista, "m"));
        }
        scanner.close();
    }

    public static String normalizarSexo(String sexoEntrada) {
        sexoEntrada = sexoEntrada.toLowerCase();
        if (sexoEntrada.equals("f") || sexoEntrada.equals("feminino")) return "f";
        if (sexoEntrada.equals("m") || sexoEntrada.equals("masculino")) return "m";
        return null;
    }

    public static List<Pessoa> filtrarPorSexo(List<Pessoa> lista, String sexoPadrao) {
        return lista.stream()
            .filter(p -> p.getSexo().equalsIgnoreCase(sexoPadrao))
            .collect(Collectors.toList());
    }

    public static void exibirLista(String titulo, List<Pessoa> lista) {
        System.out.println("\n*** " + titulo + " ***");
        if (lista.isEmpty()) {
            System.out.println("Nenhum registro encontrado.");
        } else {
            lista.forEach(System.out::println);
        }
    }

    public static void exibirResumoFinal(List<Pessoa> lista) {
        System.out.println("\nEncerrando o programa...");
        System.out.println("Aqui estão as listas finais:");
        exibirLista("Mulheres no sistema", filtrarPorSexo(lista, "f"));
        exibirLista("Homens no sistema", filtrarPorSexo(lista, "m"));
    }
}