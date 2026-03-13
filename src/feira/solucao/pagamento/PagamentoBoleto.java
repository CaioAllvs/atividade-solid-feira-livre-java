package feira.solucao.pagamento;

public class PagamentoBoleto implements ProcessadorPagamento {
    @Override
    public String obterCodigo() { return "BOLETO"; }

    @Override
    public void processarPagamento(double valor) {
        System.out.printf("Boleto gerado com sucesso. Valor: R$ %.2f%n", valor);
    }
}