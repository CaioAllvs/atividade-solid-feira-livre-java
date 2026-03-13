package feira.solucao.domain;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private final String cliente;
    private final List<PedidoItem> itens;

    public Pedido(String cliente) {
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public String getCliente() { return cliente; }
    public List<PedidoItem> getItens() { return itens; }
    
    public void adicionarItem(Produto produto, int quantidade) {
        this.itens.add(new PedidoItem(produto, quantidade));
    }

    public boolean vazio() { return itens.isEmpty(); }

    // Usando Stream para somar o total, fica mais limpo que o for tradicional do professor
    public double totalBruto() {
        return itens.stream()
                    .mapToDouble(PedidoItem::subtotal)
                    .sum();
    }
}