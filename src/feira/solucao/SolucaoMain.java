package feira.solucao;

import feira.solucao.domain.Pedido;
import feira.solucao.domain.Produto;
import feira.solucao.repository.PedidoRepositoryMemoria;
import feira.solucao.cupom.ImpressoraTermica;
import feira.solucao.notificacao.NotificadorWhatsApp;
import feira.solucao.relatorio.ExportadorCsvPedido;
import feira.solucao.desconto.*;
import feira.solucao.pagamento.*;
import feira.solucao.service.FinalizadorPedidoService;
import feira.solucao.entrega.*;

import java.util.Arrays;

public class SolucaoMain {
    public static void main(String[] args) {
        // 1. Configurando dependências (DIP)
        CalculadoraDesconto calcDesconto = new CalculadoraDesconto(Arrays.asList(
                new SemDesconto(), new DescontoClienteFiel(), new DescontoQueimaEstoque(), new DescontoDomingo()
        ));

        ServicoPagamento servPagamento = new ServicoPagamento(Arrays.asList(
                new PagamentoPix(), new PagamentoCartao(), new PagamentoBoleto()
        ));

        FinalizadorPedidoService finalizador = new FinalizadorPedidoService(
                new PedidoRepositoryMemoria(),
                new ImpressoraTermica(),
                new NotificadorWhatsApp(),
                new ExportadorCsvPedido(),
                calcDesconto,
                servPagamento
        );

        // 2. Fluxo Principal do Pedido
        System.out.println("--- PROCESSANDO PEDIDO ---");
        Pedido pedido = new Pedido("João da Silva");
        pedido.adicionarItem(new Produto("Maçã", 5.50), 4);
        pedido.adicionarItem(new Produto("Alface", 3.00), 2);

        finalizador.finalizarPedido(pedido, "DOMINGO", "CARTAO", "85988887777");

        // 3. Demonstrando o LSP (Substituição de Liskov)
        System.out.println("\n--- TESTANDO ENTREGAS (LSP) ---");
        CalculadoraPrazoEntrega entregaComum = new EntregaNormal();
        CalculadoraPrazoEntrega entregaRapida = new EntregaExpressa();

        double distanciaTeste = 35.0; // Distância que quebrava o código antigo

        // Nenhuma das duas vai "crashar", ambas funcionam como "CalculadoraPrazoEntrega"
        System.out.printf("%s (%.1f km): %d dia(s)%n", entregaComum.getDescricao(), distanciaTeste, entregaComum.estimarPrazoEmDias(distanciaTeste));
        System.out.printf("%s (%.1f km): %d dia(s)%n", entregaRapida.getDescricao(), distanciaTeste, entregaRapida.estimarPrazoEmDias(distanciaTeste));
    }
}