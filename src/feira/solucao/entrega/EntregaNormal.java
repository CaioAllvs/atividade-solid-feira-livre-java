package feira.solucao.entrega;

public class EntregaNormal implements CalculadoraPrazoEntrega {
    @Override
    public String getDescricao() { 
        return "Entrega Padrão"; 
    }

    @Override
    public int estimarPrazoEmDias(double distanciaKm) {
        if (distanciaKm < 0) {
            throw new IllegalArgumentException("A distância não pode ser negativa.");
        }
        // 1 dia para cada 10 km
        return (int) Math.ceil(distanciaKm / 10.0);
    }
}