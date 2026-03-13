package feira.solucao.service;

import feira.solucao.domain.Pedido;
import feira.solucao.repository.PedidoRepository;
import feira.solucao.cupom.ImpressoraCupom;
import feira.solucao.notificacao.NotificadorPedido;
import feira.solucao.relatorio.ExportadorRelatorioPedido;
import feira.solucao.desconto.CalculadoraDesconto;
import feira.solucao.pagamento.ServicoPagamento;

public class FinalizadorPedidoService {
    
    // Dependências são apenas declaradas, não instanciadas aqui
    private final PedidoRepository repository;
    private final ImpressoraCupom impressora;
    private final NotificadorPedido notificador;
    private final ExportadorRelatorioPedido exportador;
    private final CalculadoraDesconto calculadoraDesconto;
    private final ServicoPagamento servicoPagamento;

    // Injeção de Dependências pelo construtor (DIP resolvido)
    public FinalizadorPedidoService(PedidoRepository repository,
                                    ImpressoraCupom impressora,
                                    NotificadorPedido notificador,
                                    ExportadorRelatorioPedido exportador,
                                    CalculadoraDesconto calculadoraDesconto,
                                    ServicoPagamento servicoPagamento) {
        this.repository = repository;
        this.impressora = impressora;
        this.notificador = notificador;
        this.exportador = exportador;
        this.calculadoraDesconto = calculadoraDesconto;
        this.servicoPagamento = servicoPagamento;
    }

    public double finalizarPedido(Pedido pedido, String tipoDesconto, String formaPagamento, String telefone) {
        double totalBruto = pedido.totalBruto();
        double totalLiquido = calculadoraDesconto.aplicarDesconto(tipoDesconto, totalBruto);

        servicoPagamento.realizarPagamento(formaPagamento, totalLiquido);

        repository.salvar(pedido);
        impressora.imprimir(pedido, totalLiquido);
        notificador.notificarFinalizacao(telefone, totalLiquido);

        String csv = exportador.exportar(pedido, totalLiquido);
        System.out.println("\nResumo CSV:\n" + csv);

        return totalLiquido;
    }
}