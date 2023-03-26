
public class CheckList {
	private String descricao;
	private boolean concluido;
	private int pontuacao;

	public CheckList(String descricao, int pontuacao) {
		super();
		this.descricao = descricao;
		this.concluido = false;
		this.pontuacao = pontuacao;
	}

	public void somarPontuacao(int pontuacao) {
		if (isConcluido() && !concluido) {
			concluido = true;
			this.pontuacao += pontuacao;
		}
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
