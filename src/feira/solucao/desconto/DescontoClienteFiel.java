package feira.solucao.desconto;

public class DescontoClienteFiel implements PoliticaDesconto {
    @Override
    public String obterCodigo() { return "CLIENTE_FIEL"; }

    @Override
    public double calcularValor(double valorBruto) {
        return valorBruto * 0.90; // 10% de desconto
    }
}