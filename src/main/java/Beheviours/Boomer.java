package Beheviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class Boomer extends WakerBehaviour {
    public Boomer(Agent a, long timeout) {
        super(a, timeout);
    }

    @Override
    protected void onWake() {
        ACLMessage poong = new ACLMessage(ACLMessage.CONFIRM);
        poong.setContent("BOOM from AgentThree to AgentTwo!");
        poong.addReceiver(new AID("AgentTwo", false));
        getAgent().send(poong);


    }

    @Override
    public int onEnd() {
        // Подождем 1 секунду и выведем ответ
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ACLMessage answer = getAgent().receive(MessageTemplate.MatchSender(new AID("AgentTwo", false)));
        System.out.println(answer.getContent());
        return super.onEnd();
    }
}

