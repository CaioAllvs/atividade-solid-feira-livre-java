package feira.solucao.pagamento;

public interface ProcessadorPagamento {
    String obterCodigo();
    void processarPagamento(double valor);
}