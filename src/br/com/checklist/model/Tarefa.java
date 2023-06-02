package br.com.checklist.model;

public class Tarefa {
	private Integer id;
	private String descricao;
	private boolean concluido;
	private int pontuacao;

	public Tarefa() {}

	public Tarefa(Integer id, String descricao, boolean concluido, int pontuacao) {
		this.id = id;
		this.descricao = descricao;
		this.concluido = concluido;
		this.pontuacao = pontuacao;
	}

	@Override
	public String toString() {
		String tarefaToString = "Tarefa " + this.id + ": ";
		if (this.concluido)
			tarefaToString += "☑ ";
		else
			tarefaToString += "☐ ";
		return tarefaToString += this.descricao + " - " + this.pontuacao + " ponto(s)";
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isConcluido() {
		return concluido;
	}

	public void setConcluido(boolean concluido) {
		this.concluido = concluido;
	}

	public int getPontuacao() {
		return pontuacao;
	}

	public void setPontuacao(int pontuacao) {
		this.pontuacao = pontuacao;
	}

}
