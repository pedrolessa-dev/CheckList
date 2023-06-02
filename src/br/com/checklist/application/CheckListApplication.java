// Programa para gerenciamento de tarefas com sistema de pontuação
package br.com.checklist.application;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import br.com.checklist.model.Tarefa;
import br.com.checklist.service.TarefaService;

public class CheckListApplication {
	public static void main(String[] args) {

		// Cria uma lista e declara as variáveis
		List<Tarefa> tarefas = new ArrayList<Tarefa>();
		TarefaService service = new TarefaService();
		Tarefa tarefa = new Tarefa();
		Scanner sc = new Scanner(System.in);
		int opcaoEscolhida = 1;

		// Exibe menu de opções
		System.out.println("===================================");
		System.out.println("Programa de checklist");
		// Loop principal que funciona até que o usuário digite a opção de sair
		while (opcaoEscolhida != 0) {
			try {
				System.out.print(service.exibirMenu());
				opcaoEscolhida = sc.nextInt();
				sc.nextLine(); // adicionado para descartar a entrada inválida

				// Opções do menu que serão executadas de acordo com a escolha do usuário
				switch (opcaoEscolhida) {
				case 1:
					tarefas = service.exibirTodasAsTarefas();
					tarefas.stream().forEach(System.out::println);
					System.out.println("Tarefas concluídas: " + service.getTotalDeTarefasConcluidas() + "/" + tarefas.size());
					break;
				case 2:
					tarefas = service.exibirTarefasConcluidas();
					tarefas.stream().forEach(System.out::println);
					break;
				case 3:
					System.out.println("Sua pontuação atual: " + service.exibirPontuacao() + " ponto(s)");
					break;
				case 4:
					System.out.println("Digite a descrição da sua tarefa");
					var descricao = sc.nextLine();
					System.out.println("Digite uma pontuação para essa tarefa");
					var pontuacao = Integer.parseInt(sc.nextLine());
					service.cadastrarTarefa(new Tarefa(null, descricao, false, pontuacao));
					System.out.println("Tarefa cadastrada com sucesso!");
					break;
				case 5:
					System.out.println("Digite o número da tarefa que deseja excluir");
					tarefa.setId(Integer.parseInt(sc.nextLine()));
					service.excluirTarefa(tarefa);
					System.out.println("Tarefa " + tarefa.getId() + " foi excluída com sucesso!");
					break;
				case 6:
					System.out.println("Digite o número da tarefa que deseja editar");
					tarefa.setId(Integer.parseInt(sc.nextLine()));
					System.out.println("Digite a nova descrição desta tarefa");
					tarefa.setDescricao(sc.nextLine());
					System.out.println("Digite a nova pontuação desta tarefa");
					tarefa.setPontuacao(Integer.parseInt(sc.nextLine()));
					service.editarTarefa(tarefa);
					System.out.println("Tarefa " + tarefa.getId() + " foi editada com sucesso!");
					break;
				case 7:
					System.out.println("Digite o número da tarefa que deseja marcar como concluída");
					tarefa.setId(Integer.parseInt(sc.nextLine()));
					service.marcarTarefaComoConcluida(tarefa);
					System.out.println("Tarefa " + tarefa.getId() + " foi marcada como concluída!");
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
			} catch (RuntimeException e) {
				System.out.println("Erro: " + e.getMessage());
			} catch (Exception e) {
				System.out.println("Ocorreu um erro inesperado: " + e.getMessage());
				e.printStackTrace();
			}
		}
		sc.close();
	}
}
