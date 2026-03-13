package feira.solucao.desconto;

public class SemDesconto implements PoliticaDesconto {
    @Override
    public String obterCodigo() { return "NENHUM"; }

    @Override
    public double calcularValor(double valorBruto) { return valorBruto; }
}