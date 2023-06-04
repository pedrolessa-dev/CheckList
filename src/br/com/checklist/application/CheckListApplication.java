// Programa para gerenciamento de tarefas com sistema de pontuação
package br.com.checklist.application;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.checklist.model.Tarefa;
import br.com.checklist.service.TarefaService;

public class CheckListApplication {
	private static TarefaService service = new TarefaService();
	private static Tarefa tarefa = new Tarefa();
	private static Scanner sc = new Scanner(System.in).useDelimiter("\n");

	public static void main(String[] args) {

		// Exibe menu de opções
		System.out.println("===================================");
		System.out.println("Programa de checklist");

		// Loop principal que funciona até que o usuário digite a opção de sair
		int opcaoEscolhida = -1;
		while (opcaoEscolhida != 0) {
			try {
				opcaoEscolhida = exibirMenu();

				// Verifica se a opção escolhida é válida
				if (opcaoEscolhida >= 1 && opcaoEscolhida <= 5) {

					// Opções do menu que serão executadas de acordo com a escolha do usuário
					switch (opcaoEscolhida) {
					case 1:
						exibirTarefas();
						break;
					case 2:
						cadastrarTarefa();
						break;
					case 3:
						editarTarefa();
						break;
					case 4:
						excluirTarefa();
						break;
					case 5:
						marcarTarefaComoConcluida();
						break;
					}
				} else if (opcaoEscolhida == 0) {
					System.out.println("Saindo do programa...");
				} else {
					System.out.println("Opção inválida, tente novamente.");
				}
			} catch (NumberFormatException | InputMismatchException e) {
				System.out.println("Entrada inválida, por favor digite um valor numérico válido.");
			} catch (RuntimeException e) {
				System.out.println("Erro: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
				e.printStackTrace();
			}
		}
	}

	private static int exibirMenu() {
		System.out.print("""
				===================================
				1 - Exibir todas as tarefas
				2 - Cadastrar tarefa
				3 - Editar tarefa
				4 - Excluir tarefa
				5 - Marcar tarefa como concluída
				0 - Sair
				""");
		return Integer.parseInt(sc.nextLine());
	}

	private static void exibirTarefas() {
		List<Tarefa> tarefas = service.exibirTarefas();
		tarefas.stream().forEach(System.out::println);
		System.out.println("Tarefas concluídas: " + service.getTotalDeTarefasConcluidas() + "/" + tarefas.size());
		System.out.println("Sua pontuação atual: " + service.exibirPontuacao() + " ponto(s)");
	}

	private static void cadastrarTarefa() {
		System.out.println("Digite a descrição da sua tarefa");
		String descricao = sc.nextLine();
		System.out.println("Digite uma pontuação para essa tarefa");
		int pontuacao = Integer.parseInt(sc.nextLine());
		service.cadastrarTarefa(new Tarefa(null, descricao, false, pontuacao));
		System.out.println("Tarefa cadastrada com sucesso!");
	}

	private static void editarTarefa() {
		System.out.println("Digite o número da tarefa que deseja editar");
		tarefa.setId(Integer.parseInt(sc.nextLine()));
		System.out.println("Digite a nova descrição desta tarefa");
		tarefa.setDescricao(sc.nextLine());
		System.out.println("Digite a nova pontuação desta tarefa");
		tarefa.setPontuacao(Integer.parseInt(sc.nextLine()));
		service.editarTarefa(tarefa);
		System.out.println("Tarefa " + tarefa.getId() + " foi editada com sucesso!");
	}

	private static void excluirTarefa() {
		System.out.println("Digite o número da tarefa que deseja excluir");
		tarefa.setId(Integer.parseInt(sc.nextLine()));
		service.excluirTarefa(tarefa);
		System.out.println("Tarefa " + tarefa.getId() + " foi excluída com sucesso!");
	}

	private static void marcarTarefaComoConcluida() {
		System.out.println("Digite o número da tarefa que deseja marcar como concluída");
		tarefa.setId(Integer.parseInt(sc.nextLine()));
		service.marcarTarefaComoConcluida(tarefa);
		System.out.println("Tarefa " + tarefa.getId() + " foi marcada como concluída! Parabéns!");
	}
}
