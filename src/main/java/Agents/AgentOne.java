package Agents;

import Beheviours.InitialPing;
import jade.core.Agent;

public class AgentOne extends Agent {
    /*
        Добавление поведений агентам проивзодится внутри переопределяемого метода родительского класса setup(), для
        чего внутри метода вызывается метод addBehaviour() с экземпляром класса добавляемого поведения
     */
    @Override
    protected void setup() {
        super.setup();
        addBehaviour(new InitialPing(this));
    }
}
