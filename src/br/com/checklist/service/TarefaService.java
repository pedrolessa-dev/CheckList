package br.com.checklist.service;

import java.sql.Connection;
import java.util.List;

import br.com.checklist.dao.TarefaDAO;
import br.com.checklist.database.ConnectionFactory;
import br.com.checklist.model.Tarefa;

public class TarefaService {
	private ConnectionFactory connection;

	public TarefaService() {
		this.connection = new ConnectionFactory();
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

	public List<Tarefa> exibirTodasAsTarefas() {
		Connection conn = this.connection.recuperarConexao();
		return new TarefaDAO(conn).listarTarefas(1);
	}

	public List<Tarefa> exibirTarefasConcluidas() {
		Connection conn = this.connection.recuperarConexao();
		return new TarefaDAO(conn).listarTarefas(2);
	}

	public int exibirPontuacao() {
		Connection conn = this.connection.recuperarConexao();
		return new TarefaDAO(conn).pontuacaoTotal();
	}

	public void cadastrarTarefa(Tarefa tarefa) {
		Connection conn = this.connection.recuperarConexao();
		new TarefaDAO(conn).cadastrarTarefa(tarefa);
	}

	public void excluirTarefa(Tarefa tarefa) {
		Connection conn = this.connection.recuperarConexao();
		new TarefaDAO(conn).excluirTarefa(tarefa.getId());
	}

	public void editarTarefa(Tarefa tarefa) {
		Connection conn = this.connection.recuperarConexao();
		new TarefaDAO(conn).editarTarefa(tarefa);
	}

	public void marcarTarefaComoConcluida(Tarefa tarefa) {
		if (!tarefa.isConcluido()) {
			Connection conn = this.connection.recuperarConexao();
			new TarefaDAO(conn).marcarTarefaComoConcluida(tarefa);
		} else
			throw new RuntimeException("Essa tarefa já foi marcada como concluída");
	}

	public int getTotalDeTarefasConcluidas() {
		Connection conn = this.connection.recuperarConexao();
		return new TarefaDAO(conn).getTotalDeTarefasConcluidas();
	}
}
