package Beheviours;

import jade.core.AID;
import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import jade.lang.acl.MessageTemplate;

public class InitialPing extends OneShotBehaviour {
    /* Данное поведение наследуется от базового поведения OneShotBehaviour, описанного в платформе JADE
       Данное поведение предполагает единоразовое исполнение данным агентом.
     */
    private Agent agent;
    /*
        Объявим приватную переменную класса Agent, в которой будет хранится агент, исполняющий данное поведение
     */

    public InitialPing(Agent agent) {
        /*
            Опишем конструктор класса, в котором инициализируем приватную переменную agent
         */
        this.agent = agent;
    }

    @Override
    public void action() {
        //Переопределим родительский метод action класса OneShotBehaviour,в котором опишем пользовательскую логику
        ACLMessage initPing = new ACLMessage(ACLMessage.REQUEST); // Создадим новое сообщение типа ACLMessage.REQUEST
        initPing.setContent("Hi AgentTwo, It's AgentOne with initial PING 1 for you!"); // Добавимс содержание сообщения
        initPing.addReceiver(new AID("AgentTwo", false)); //Зададим получателя сообщения
        agent.send(initPing); // Отправим сообщение получателю

        /*
          Так как поведение отрабатывает один раз, для реализациии дальнейшей лоигки необходимо довбавить
          новое поведение при помощи метода addBehaviour. В данном случае добавим поведение Pinger, отправляющее
          PING каждые 5 секунд
         */
        agent.addBehaviour(new Pinger(agent, 3000));

    }
}
