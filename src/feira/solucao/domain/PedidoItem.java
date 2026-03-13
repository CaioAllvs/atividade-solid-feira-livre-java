package feira.solucao.domain;

public class PedidoItem {
    private final Produto produto;
    private final int quantidade;

    public PedidoItem(Produto produto, int quantidade) {
        this.produto = produto;
        this.quantidade = Math.max(1, quantidade); // Garante pelo menos 1 item
    }

    public Produto getProduto() { return produto; }
    public int getQuantidade() { return quantidade; }

    public double subtotal() {
        return produto.getPreco() * quantidade;
    }
}