// Programa para gerenciamento de checklist com sistema de pontuação
// e caixa de seleção para indicar qual tarefa já foi concluída

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Programa {
	public static void main(String[] args) {

		// Cria uma lista e declara as variáveis
		List<CheckList> lista = new ArrayList<CheckList>();
		Menu menu = Menu.getInstance();
		Scanner sc = new Scanner(System.in);
		int opcaoEscolhida = 1, numeroDaTarefa = 1;

		// Exibe menu de opções
		System.out.println("===================================");
		System.out.println("Programa de checklist");

		// Loop principal que funciona até que o usuário digite a opção de sair
		while (opcaoEscolhida != 0) {
			try {
				System.out.print(menu.exibirMenu());
				opcaoEscolhida = sc.nextInt();
				sc.nextLine(); // adicionado para descartar a entrada inválida

				// Opções do menu que serão executadas de acordo com a escolha do usuário
				switch (opcaoEscolhida) {
				case 1:
					menu.exibirTodasAsTarefas(lista);
					break;
				case 2:
					menu.exibirTarefasConcluidas(lista);
					break;
				case 3:
					menu.exibirPontuacao(lista);
					break;
				case 4:
					System.out.println("Digite a descrição da sua tarefa");
					String descricao = sc.nextLine();
					System.out.println("Digite uma pontuação para essa tarefa");
					int pontuacao = sc.nextInt();
					menu.cadastrarTarefa(lista, descricao, pontuacao);
					break;
				case 5:
					System.out.println("Digite o número da tarefa que deseja excluir");
					numeroDaTarefa = sc.nextInt();
					menu.excluirTarefa(lista, numeroDaTarefa);
					break;
				case 6:
					System.out.println("Digite o número da tarefa que deseja editar");
					numeroDaTarefa = sc.nextInt();
					menu.editarTarefa(lista, numeroDaTarefa);
					break;
				case 7:
					System.out.println("Digite o número da tarefa que deseja marcar como concluída");
					numeroDaTarefa = sc.nextInt();
					menu.marcarTarefaComoConcluida(lista, numeroDaTarefa);
					break;
				case 0:
					System.out.println("Saindo do programa...");
					break;
				default:
					System.out.println("Opção inválida, tente novamente");
					continue;
				}
			} catch (InputMismatchException e) {
				System.out.println("Entrada inválida, por favor digite um valor numérico válido.");
				sc.nextLine(); // adicionado para descartar a entrada inválida
			} catch (NumberFormatException e) {
				System.out.println("Número inválido, por favor digite um valor numérico válido.");
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Índice inválido, por favor digite um índice dentro dos limites da lista.");
			} catch (Exception e) {
				System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
				e.printStackTrace();
			}
		}
		sc.close();
	}
}
