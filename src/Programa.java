// Programa para gerenciamento de checklist com sistema de pontuação
// e caixa de seleção para indicar qual tarefa já foi concluída

import java.util.ArrayList;
import java.util.Scanner;

public class Programa {
	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);

		// Cria uma lista e declara as variáveis
		ArrayList<CheckList> lista = new ArrayList<CheckList>();
		int opcaoEscolhida = 1, numeroDaTarefa = 1, totalDePontos = 0, totalDeTarefasConcluidas = 0;

		// Exibe menu de opções
		System.out.println("===================================");
		System.out.println("Programa de checklist");

		// Loop principal que funciona até que o usuário digite a opção de sair
		while (opcaoEscolhida != 0) {
			System.out.println("===================================");
			System.out.println("1 - Exibir todas as tarefas");
			System.out.println("2 - Exibir tarefas concluídas");
			System.out.println("3 - Exibir sua pontuação");
			System.out.println("4 - Cadastrar tarefa");
			System.out.println("5 - Excluir tarefa");
			System.out.println("6 - Editar tarefa");
			System.out.println("7 - Marcar tarefa como concluída");
			System.out.println("0 - Sair");
			opcaoEscolhida = teclado.nextInt();
			teclado.nextLine();
			switch (opcaoEscolhida) {
			case 1:
				if (lista.size() > 0) {
					for (CheckList checkList : lista) {
						if (checkList.isConcluido())
							System.out.print("☑ ");
						else
							System.out.print("☐ ");
						System.out.println("Tarefa " + (lista.indexOf(checkList) + 1) + ": " + checkList.getDescricao()
								+ " - " + checkList.getPontuacao() + " ponto(s)");
					}
					System.out.println("Tarefas concluídas: " + totalDeTarefasConcluidas + "/" + lista.size());
				} else
					System.out.println("Nenhuma tarefa cadastrada");
				break;
			case 2:
				if (lista.size() > 0) {
					for (CheckList checkList : lista) {
						if (checkList.isConcluido()) {
							System.out.println("☑ Tarefa " + (lista.indexOf(checkList) + 1) + ": "
									+ checkList.getDescricao() + " - " + checkList.getPontuacao() + " ponto(s)");
						} else
							System.out.println("Nenhuma tarefa concluída no momento");
					}
				} else
					System.out.println("Nenhuma tarefa cadastrada");
				break;
			case 3:
				for (CheckList checkList : lista) {
					if (checkList.isConcluido()) {
						totalDePontos += checkList.getPontuacao();
					}
				}
				System.out.println("Sua pontuação atual: " + totalDePontos + " ponto(s)");
				break;
			case 4:
				System.out.println("Digite a descrição da sua tarefa");
				String descricao = teclado.nextLine();
				System.out.println("Digite uma pontuação para essa tarefa");
				int pontuacao = teclado.nextInt();
				lista.add(new CheckList(descricao, pontuacao));
				System.out.println("Tarefa cadastrada com sucesso!");
				break;
			case 5:
				System.out.println("Digite o número da tarefa que deseja excluir");
				numeroDaTarefa = teclado.nextInt();
				if (numeroDaTarefa > 0 && numeroDaTarefa <= lista.size()) {
					if (lista.get(numeroDaTarefa - 1).isConcluido())
						totalDeTarefasConcluidas--;
					lista.remove(numeroDaTarefa - 1);
					System.out.println("Tarefa " + numeroDaTarefa + " foi removida com sucesso");
				} else
					System.out.println("Este número de tarefa não existe");
				break;
			case 6:
				System.out.println("Digite o número da tarefa que deseja editar");
				numeroDaTarefa = teclado.nextInt();
				if (numeroDaTarefa > 0 && numeroDaTarefa <= lista.size()) {
					teclado.nextLine();
					System.out.println("Qual será a nova descrição dessa tarefa?");
					lista.get(numeroDaTarefa - 1).setDescricao(teclado.nextLine());
					System.out.println("Qual será a nova pontuação?");
					lista.get(numeroDaTarefa - 1).setPontuacao(teclado.nextInt());
					System.out.println("Tarefa " + numeroDaTarefa + " foi alterada com sucesso");
				} else
					System.out.println("Este número de tarefa não existe");
				break;
			case 7:
				System.out.println("Digite o número da tarefa que deseja marcar como concluída");
				numeroDaTarefa = teclado.nextInt();
				if (numeroDaTarefa > 0 && numeroDaTarefa <= lista.size()) {
					if (!lista.get(numeroDaTarefa - 1).isConcluido()) {
						lista.get(numeroDaTarefa - 1).setConcluido(true);
						lista.get(numeroDaTarefa - 1).somarPontuacao(lista.get(numeroDaTarefa - 1).getPontuacao());
						totalDeTarefasConcluidas++;
						System.out.println("Tarefa " + numeroDaTarefa + " foi marcada como concluída, parabéns!");
					} else
						System.out.println("Essa tarefa já foi marcada como concluída");
				} else
					System.out.println("Este número de tarefa não existe");
				break;
			case 0:
				System.out.println("Saindo do programa...");
				break;
			default:
				System.out.println("Opção inválida, tente novamente");
			}
		}
		teclado.close();
	}
}
