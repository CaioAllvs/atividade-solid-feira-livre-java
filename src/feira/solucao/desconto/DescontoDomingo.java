package feira.solucao.desconto;

public class DescontoDomingo implements PoliticaDesconto {
    @Override
    public String obterCodigo() { return "DOMINGO"; }

    @Override
    public double calcularValor(double valorBruto) {
        return valorBruto * 0.95; // 5% de desconto
    }
}