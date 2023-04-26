import java.util.List;

public interface OpcoesDoMenu {
	void exibirTodasAsTarefas(List<CheckList> lista);
	void exibirTarefasConcluidas(List<CheckList> lista);
	void exibirPontuacao(List<CheckList> lista);
	void cadastrarTarefa(List<CheckList> lista, String descricao, int pontuacao);
	void excluirTarefa(List<CheckList> lista, int numeroDaTarefa);
	void editarTarefa(List<CheckList> lista, int numeroDaTarefa);
	void marcarTarefaComoConcluida(List<CheckList> lista, int numeroDaTarefa);
}
