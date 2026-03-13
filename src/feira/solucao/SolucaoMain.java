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

import java.util.Arrays;

public class SolucaoMain {
    public static void main(String[] args) {
        // 1. Configurando as estratégias e dependências (Injeção)
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

        // 2. Criando um pedido de teste próprio
        Pedido pedido = new Pedido("João da Silva");
        pedido.adicionarItem(new Produto("Maçã", 5.50), 4);
        pedido.adicionarItem(new Produto("Alface", 3.00), 2);

        // 3. Executando o fluxo
        System.out.println("Iniciando finalização do pedido...\n");
        finalizador.finalizarPedido(pedido, "DOMINGO", "CARTAO", "85988887777");
    }
}