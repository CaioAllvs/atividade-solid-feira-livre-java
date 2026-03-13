package feira.solucao.pagamento;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServicoPagamento {
    private final Map<String, ProcessadorPagamento> processadores = new HashMap<>();

    public ServicoPagamento(List<ProcessadorPagamento> listaProcessadores) {
        for (ProcessadorPagamento p : listaProcessadores) {
            processadores.put(p.obterCodigo().toUpperCase(), p);
        }
    }

    public void realizarPagamento(String codigoPagamento, double valor) {
        if (codigoPagamento == null) {
            throw new IllegalArgumentException("É necessário informar uma forma de pagamento.");
        }
        
        ProcessadorPagamento processador = processadores.get(codigoPagamento.toUpperCase());
        if (processador == null) {
            throw new IllegalArgumentException("Forma de pagamento não reconhecida: " + codigoPagamento);
        }
        processador.processarPagamento(valor);
    }
}