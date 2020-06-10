package Agents;

import Beheviours.Boomer;
import jade.core.Agent;

public class AgentThree extends Agent {
    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new Boomer(this, 10000));
    }
}
