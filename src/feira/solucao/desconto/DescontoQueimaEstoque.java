package feira.solucao.desconto;

public class DescontoQueimaEstoque implements PoliticaDesconto {
    @Override
    public String obterCodigo() { return "QUEIMA_ESTOQUE"; }

    @Override
    public double calcularValor(double valorBruto) {
        return valorBruto * 0.80; // 20% de desconto
    }
}