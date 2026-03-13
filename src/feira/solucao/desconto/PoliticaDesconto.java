package feira.solucao.desconto;

public interface PoliticaDesconto {
    String obterCodigo();
    double calcularValor(double valorBruto);
}