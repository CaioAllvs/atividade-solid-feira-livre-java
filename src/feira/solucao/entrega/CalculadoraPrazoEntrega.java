package feira.solucao.entrega;

public interface CalculadoraPrazoEntrega {
    String getDescricao();
    int estimarPrazoEmDias(double distanciaKm);
}