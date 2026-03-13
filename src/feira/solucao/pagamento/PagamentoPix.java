package feira.solucao.pagamento;

public class PagamentoPix implements ProcessadorPagamento {
    @Override
    public String obterCodigo() { return "PIX"; }

    @Override
    public void processarPagamento(double valor) {
        System.out.printf("Transação via PIX confirmada. Valor: R$ %.2f%n", valor);
    }
}