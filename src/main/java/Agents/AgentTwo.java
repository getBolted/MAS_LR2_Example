package Agents;

import Beheviours.WaitingForPing;
import jade.core.Agent;

public class AgentTwo extends Agent {
    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new WaitingForPing(this)); // Добавим 2-ому агенту его поведение
    }
}
