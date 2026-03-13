package feira.solucao.notificacao;

public class NotificadorWhatsApp implements NotificadorPedido {
    @Override
    public void notificarFinalizacao(String contato, double total) {
        String mensagem = String.format("Aviso [WhatsApp] para %s: Seu pedido foi concluído com sucesso. Valor final: R$ %.2f", contato, total);
        System.out.println(mensagem);
    }
}