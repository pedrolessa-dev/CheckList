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

	public List<Tarefa> exibirTarefas() {
		Connection conn = this.connection.recuperarConexao();
		return new TarefaDAO(conn).listarTarefas();
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
		Connection conn = this.connection.recuperarConexao();
		new TarefaDAO(conn).marcarTarefaComoConcluida(tarefa);
	}

	public int getTotalDeTarefasConcluidas() {
		Connection conn = this.connection.recuperarConexao();
		return new TarefaDAO(conn).getTotalDeTarefasConcluidas();
	}
}
