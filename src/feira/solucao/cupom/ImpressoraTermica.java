package feira.solucao.cupom;

import feira.solucao.domain.Pedido;
import feira.solucao.domain.PedidoItem;

public class ImpressoraTermica implements ImpressoraCupom {
    @Override
    public void imprimir(Pedido pedido, double totalLiquido) {
        System.out.println("--------------------------------");
        System.out.println("      CUPOM DA FEIRA LIVRE      ");
        System.out.println("--------------------------------");
        System.out.println("Comprador: " + pedido.getCliente());
        System.out.println("Itens:");
        
        for (PedidoItem item : pedido.getItens()) {
            System.out.printf(" - %s (x%d) -> R$ %.2f%n", 
                item.getProduto().getNome(), 
                item.getQuantidade(), 
                item.subtotal());
        }
        
        System.out.println("--------------------------------");
        System.out.printf("TOTAL A PAGAR: R$ %.2f%n", totalLiquido);
        System.out.println("--------------------------------");
    }
}