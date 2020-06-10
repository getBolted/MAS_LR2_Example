package Beheviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.Behaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class WaitingForPing extends Behaviour {
    private boolean messageReceived;
    private Agent agent;

    public WaitingForPing(Agent agent) {
        this.agent = agent;
    }

    @Override
    public void action() {
        /*
         Зададим фильтр, по которому будут принматься сообщения. В нашем случае будут приниматься все сообщения.
         */
        MessageTemplate mt = MessageTemplate.MatchAll();
        ACLMessage ping = agent.receive(mt); // Открываем "почтовый ящик" с сообщениями


        if (ping != null){ // Если "Ящик" не пуст и в него попало сообщение
            System.out.println(ping.getContent()); // Выведем в консоль полученное сообщение
            if (ping.getPerformative() == ACLMessage.REQUEST){ //Если сообщение типа REQUEST, т.е самый превый PING
               ACLMessage firstPong = ping.createReply(); // Создаем ответ адресату, отправившему сообщение
               firstPong.setContent("Hi, AgentOne, here is my initial PONG for your PING!");
               firstPong.setPerformative(ACLMessage.AGREE);
               agent.send(firstPong);
            } else if (ping.getPerformative() == ACLMessage.INFORM){ //Если сообщение типа INFORM, т.е последуещие PING
                ACLMessage nextPong = ping.createReply();
                nextPong.setContent("PONG from AgentTwo!");
                nextPong.setPerformative(ACLMessage.AGREE);
                agent.send(nextPong);
            } else if (ping.getSender().equals(new AID("AgentThree", false))){
                // Если сообщение от 3 агента, а не от второго
                ACLMessage pongForThird = ping.createReply();
                pongForThird.setPerformative(ACLMessage.AGREE);
                pongForThird.setContent("PONG for AgentThree from AgentTwo for his BOOM!");
                agent.send(pongForThird);
            }
        } else {
            block(); // Иначе ждем, пока в "ящике" появятся сообщения
        }

    }
    @Override
    public boolean done() { // Обязательный метод класса Behaviour,
        return false;
    }
}
