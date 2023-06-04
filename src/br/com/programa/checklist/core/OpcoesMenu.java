package br.com.programa.checklist.core;

import java.util.List;
import java.util.Scanner;

public class OpcoesMenu {
	private static OpcoesMenu instanciaUnica;
	private int totalDeTarefasConcluidas;

	private OpcoesMenu() {
	}

	public static OpcoesMenu getInstance() {
		if (OpcoesMenu.instanciaUnica == null)
			OpcoesMenu.instanciaUnica = new OpcoesMenu();
		return OpcoesMenu.instanciaUnica;
	}

	public String exibirMenu() {
		return """
				===================================
				1 - Exibir todas as tarefas
				2 - Exibir tarefas concluídas
				3 - Exibir sua pontuação
				4 - Cadastrar tarefa
				5 - Excluir tarefa
				6 - Editar tarefa
				7 - Marcar tarefa como concluída
				0 - Sair
				""";
	}

	public void exibirTodasAsTarefas(List<CheckList> lista) {
		if (lista.size() > 0) {
			for (CheckList checkList : lista) {
				if (checkList.isConcluido())
					System.out.print("☑ ");
				else
					System.out.print("☐ ");
				System.out.println("Tarefa " + (lista.indexOf(checkList) + 1) + ": " + checkList.getDescricao() + " - "
						+ checkList.getPontuacao() + " ponto(s)");
			}
			System.out.println("Tarefas concluídas: " + this.totalDeTarefasConcluidas + "/" + lista.size());
		} else
			System.out.println("Nenhuma tarefa cadastrada");

	}

	public void exibirTarefasConcluidas(List<CheckList> lista) {
		if (lista.size() > 0) {
			for (CheckList checkList : lista) {
				if (checkList.isConcluido()) {
					System.out.println("☑ Tarefa " + (lista.indexOf(checkList) + 1) + ": " + checkList.getDescricao()
							+ " - " + checkList.getPontuacao() + " ponto(s)");
				}
			}
			if (this.totalDeTarefasConcluidas == 0)
				System.out.println("Nenhuma tarefa concluída no momento");
		} else
			System.out.println("Nenhuma tarefa cadastrada");
	}

	public void exibirPontuacao(List<CheckList> lista) {
		int totalDePontos = 0;
		for (CheckList checkList : lista) {
			if (checkList.isConcluido()) {
				totalDePontos += checkList.getPontuacao();
			}
		}
		System.out.println("Sua pontuação atual: " + totalDePontos + " ponto(s)");
	}

	public void cadastrarTarefa(List<CheckList> lista, String descricao, int pontuacao) {
		lista.add(new CheckList(descricao, pontuacao));
		System.out.println("Tarefa cadastrada com sucesso!");
	}

	public void excluirTarefa(List<CheckList> lista, int numeroDaTarefa) {
		if (numeroDaTarefa > 0 && numeroDaTarefa <= lista.size()) {
			if (lista.get(numeroDaTarefa - 1).isConcluido())
				this.totalDeTarefasConcluidas--;
			lista.remove(numeroDaTarefa - 1);
			System.out.println("Tarefa " + numeroDaTarefa + " foi removida com sucesso");
		} else
			System.out.println("Este número de tarefa não existe");
	}

	public void editarTarefa(List<CheckList> lista, int numeroDaTarefa) {
		if (numeroDaTarefa > 0 && numeroDaTarefa <= lista.size()) {
			@SuppressWarnings("resource")
			Scanner sc = new Scanner(System.in);
			System.out.println("Qual será a nova descrição dessa tarefa?");
			lista.get(numeroDaTarefa - 1).setDescricao(sc.nextLine());
			System.out.println("Qual será a nova pontuação?");
			lista.get(numeroDaTarefa - 1).setPontuacao(sc.nextInt());
			System.out.println("Tarefa " + numeroDaTarefa + " foi alterada com sucesso");
		} else
			System.out.println("Este número de tarefa não existe");
	}

	public void marcarTarefaComoConcluida(List<CheckList> lista, int numeroDaTarefa) {
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
	}

}
