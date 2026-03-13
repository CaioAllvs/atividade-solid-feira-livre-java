package feira.solucao.pagamento;

public class PagamentoCartao implements ProcessadorPagamento {
    @Override
    public String obterCodigo() { return "CARTAO"; }

    @Override
    public void processarPagamento(double valor) {
        System.out.printf("Transação via Cartão aprovada. Valor: R$ %.2f%n", valor);
    }
}