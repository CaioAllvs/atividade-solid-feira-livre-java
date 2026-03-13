package feira.solucao.entrega;

public class EntregaExpressa implements CalculadoraPrazoEntrega {
    @Override
    public String getDescricao() { 
        return "Entrega Rápida (Expressa)"; 
    }

    @Override
    public int estimarPrazoEmDias(double distanciaKm) {
        if (distanciaKm < 0) {
            throw new IllegalArgumentException("A distância não pode ser negativa.");
        }
        // 1 dia para cada 20 km, mas garantindo no mínimo 1 dia de prazo
        int dias = (int) Math.ceil(distanciaKm / 20.0);
        return Math.max(1, dias);
    }
}