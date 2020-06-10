package Beheviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.TickerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;


public class Pinger extends TickerBehaviour {
    /*
        Зададим более сложный фильтр сообщений, включающий провку на соответсвие и типа сообщения, и его отправителя
     */
    private MessageTemplate mt = MessageTemplate.and(MessageTemplate.MatchPerformative(ACLMessage.AGREE),
            MessageTemplate.MatchSender(new AID("AgentTwo", false)));

    public Pinger(Agent agent, long period) {
        super(agent, period);
    }

    protected void onTick() {
        System.out.println(getAgent().receive(mt).getContent()); // Вывод в консоль ответа
        ACLMessage initPing = new ACLMessage(ACLMessage.INFORM); // Теперь сообщение типа ACLMessage.INFORM
        initPing.setContent("PING from AgentOne!");
        initPing.addReceiver(new AID("AgentTwo", false));
        getAgent().send(initPing); // Еще один вариант доступа к экземпляру агента из его поведения через метод getAgent()
    }
}
