package feira.solucao.service;

import feira.solucao.domain.Pedido;
import feira.solucao.repository.PedidoRepository;
import feira.solucao.repository.PedidoRepositoryMemoria;
import feira.solucao.cupom.ImpressoraCupom;
import feira.solucao.cupom.ImpressoraTermica;
import feira.solucao.notificacao.NotificadorPedido;
import feira.solucao.notificacao.NotificadorWhatsApp;
import feira.solucao.relatorio.ExportadorRelatorioPedido;
import feira.solucao.relatorio.ExportadorCsvPedido;

public class FinalizadorPedidoService {
    // Instanciando direto aqui porque Injeção de Dependência (DIP) é só na Etapa 4
    private final PedidoRepository repository = new PedidoRepositoryMemoria();
    private final ImpressoraCupom impressora = new ImpressoraTermica();
    private final NotificadorPedido notificador = new NotificadorWhatsApp();
    private final ExportadorRelatorioPedido exportador = new ExportadorCsvPedido();

    public double finalizarPedido(Pedido pedido, String tipoDesconto, String formaPagamento, String telefone) {
        double total = calcularTotalComDesconto(pedido, tipoDesconto);

        // Pagamento ainda está com if/else. Vamos resolver isso no OCP (Etapa 2)!
        if ("PIX".equalsIgnoreCase(formaPagamento)) {
            System.out.println("Pagamento via PIX processado: R$ " + total);
        } else if ("CARTAO".equalsIgnoreCase(formaPagamento)) {
            System.out.println("Pagamento via Cartão processado: R$ " + total);
        } else if ("BOLETO".equalsIgnoreCase(formaPagamento)) {
            System.out.println("Boleto gerado no valor de R$ " + total);
        } else {
            throw new IllegalArgumentException("Forma de pagamento inválida");
        }

        // Delegação de responsabilidades extraídas (SRP concluído)
        repository.salvar(pedido);
        impressora.imprimir(pedido, total);
        notificador.notificarFinalizacao(telefone, total);

        String csv = exportador.exportar(pedido, total);
        System.out.println("\nRelatório CSV:\n" + csv);

        return total;
    }

    // Desconto ainda está com if/else. Vamos resolver isso no OCP (Etapa 2)!
    private double calcularTotalComDesconto(Pedido pedido, String tipoDesconto) {
        double total = pedido.totalBruto();

        if ("CLIENTE_FIEL".equalsIgnoreCase(tipoDesconto)) {
            return total * 0.90;
        } else if ("QUEIMA_ESTOQUE".equalsIgnoreCase(tipoDesconto)) {
            return total * 0.80;
        } else if ("DOMINGO".equalsIgnoreCase(tipoDesconto)) {
            return total * 0.95;
        }
        return total;
    }
}