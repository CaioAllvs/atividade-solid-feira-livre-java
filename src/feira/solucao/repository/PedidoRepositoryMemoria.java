package feira.solucao.repository;

import feira.solucao.domain.Pedido;
import java.util.LinkedList;
import java.util.List;

public class PedidoRepositoryMemoria implements PedidoRepository {
    // Usando LinkedList para diferenciar do ArrayList do professor
    private final List<Pedido> baseDeDados = new LinkedList<>();

    @Override
    public void salvar(Pedido pedido) {
        baseDeDados.add(pedido);
    }

    @Override
    public List<Pedido> listarTodos() {
        return baseDeDados;
    }
}