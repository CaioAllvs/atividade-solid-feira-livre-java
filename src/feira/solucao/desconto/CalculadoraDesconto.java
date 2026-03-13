package feira.solucao.desconto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CalculadoraDesconto {
    private final Map<String, PoliticaDesconto> politicas = new HashMap<>();

    public CalculadoraDesconto(List<PoliticaDesconto> listaPoliticas) {
        for (PoliticaDesconto p : listaPoliticas) {
            politicas.put(p.obterCodigo().toUpperCase(), p);
        }
    }

    public double aplicarDesconto(String codigoDesconto, double valorBruto) {
        if (codigoDesconto == null || codigoDesconto.trim().isEmpty()) {
            return valorBruto;
        }
        
        // Se não encontrar o desconto, aplica "SemDesconto" por padrão
        PoliticaDesconto politica = politicas.getOrDefault(codigoDesconto.toUpperCase(), new SemDesconto());
        return politica.calcularValor(valorBruto);
    }
}