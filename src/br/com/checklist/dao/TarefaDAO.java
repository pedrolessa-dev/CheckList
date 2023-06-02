package br.com.checklist.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.checklist.model.Tarefa;

public class TarefaDAO {
	private Connection connection;

	public TarefaDAO(Connection connection) {
		this.connection = connection;
	}

	public void cadastrarTarefa(Tarefa tarefa) {
		String sqlInsert = "INSERT INTO tbl_tarefa (descricao, concluido, pontuacao) VALUES (?, ?, ?)";
		try {
			PreparedStatement preparedStatement = connection.prepareStatement(sqlInsert);
			preparedStatement.setString(1, tarefa.getDescricao());
			preparedStatement.setBoolean(2, false);
			preparedStatement.setInt(3, tarefa.getPontuacao());
			preparedStatement.execute();
			preparedStatement.close();
			this.connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public List<Tarefa> listarTarefas(int tipoSelect) {
		List<Tarefa> tarefas = new ArrayList<>();
		String sqlSelect;
		PreparedStatement preparedStatement;
		ResultSet result;
		if (tipoSelect == 1)
			sqlSelect = "SELECT * FROM tbl_tarefa";
		else if (tipoSelect == 2)
			sqlSelect = "SELECT * FROM tbl_tarefa WHERE concluido = TRUE";
		else
			throw new RuntimeException("Query SQL não encontrada");
		try {
			preparedStatement = this.connection.prepareStatement(sqlSelect);
			result = preparedStatement.executeQuery();
			while (result.next()) {
				int id = result.getInt(1);
				String descricao = result.getString(2);
				boolean concluido = result.getBoolean(3);
				int pontuacao = result.getInt(4);
				tarefas.add(new Tarefa(id, descricao, concluido, pontuacao));
			}
			result.close();
			preparedStatement.close();
			this.connection.close();
			return tarefas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void marcarTarefaComoConcluida(Tarefa tarefa) {
		String sqlSelect = "SELECT concluido FROM tbl_tarefa WHERE id = ?";
		String sqlUpdate = "UPDATE tbl_tarefa SET concluido = TRUE WHERE id = ?";
		try {
			PreparedStatement selectStatement = this.connection.prepareStatement(sqlSelect);
			selectStatement.setInt(1, tarefa.getId());
			ResultSet resultSet = selectStatement.executeQuery();
			if (resultSet.next()) {
				if (resultSet.getBoolean("concluido"))
					throw new RuntimeException("Essa tarefa já foi marcada como concluída");
			} else
				throw new RuntimeException("O número digitado não corresponde a uma tarefa");
			PreparedStatement updateStatement = this.connection.prepareStatement(sqlUpdate);
			updateStatement.setInt(1, tarefa.getId());
			updateStatement.executeUpdate();
			selectStatement.close();
			updateStatement.close();
			this.connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void editarTarefa(Tarefa tarefa) {
		String sqlUpdate = "UPDATE tbl_tarefa SET descricao = ?, pontuacao = ? WHERE id = ?";
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(sqlUpdate);
			preparedStatement.setString(1, tarefa.getDescricao());
			preparedStatement.setInt(2, tarefa.getPontuacao());
			preparedStatement.setInt(3, tarefa.getId());
			int linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			this.connection.close();
			if (linhasAfetadas == 0)
				throw new RuntimeException("O número digitado não corresponde a uma tarefa");
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public void excluirTarefa(Integer id) {
		String sql = "DELETE FROM tbl_tarefa WHERE id = ?";
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int linhasAfetadas = preparedStatement.executeUpdate();
			preparedStatement.close();
			this.connection.close();
			if (linhasAfetadas == 0) {
				throw new RuntimeException("O número digitado não corresponde a uma tarefa");
			}
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int pontuacaoTotal() {
		String sql = "SELECT SUM(pontuacao) FROM tbl_tarefa WHERE concluido = TRUE";
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return resultSet.getInt(1);
			return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	public int getTotalDeTarefasConcluidas() {
		String sql = "SELECT COUNT(*) FROM tbl_tarefa WHERE concluido = TRUE";
		try {
			PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next())
				return resultSet.getInt(1);
			return 0;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}