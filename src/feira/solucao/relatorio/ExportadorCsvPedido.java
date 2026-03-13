package feira.solucao.relatorio;

import feira.solucao.domain.Pedido;
import feira.solucao.domain.PedidoItem;

public class ExportadorCsvPedido implements ExportadorRelatorioPedido {
    @Override
    public String exportar(Pedido pedido, double totalLiquido) {
        StringBuilder sb = new StringBuilder();
        // Cabeçalho ligeiramente diferente
        sb.append("Cliente;Produto;Qtd;Subtotal\n"); 
        
        for (PedidoItem item : pedido.getItens()) {
            sb.append(String.format("%s;%s;%d;%.2f\n", 
                pedido.getCliente(), 
                item.getProduto().getNome(), 
                item.getQuantidade(), 
                item.subtotal()));
        }
        sb.append(String.format("TOTAL_FINAL;;;%.2f", totalLiquido));
        
        return sb.toString();
    }
}