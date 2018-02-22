package br.com.branquinho.jms;

import javax.jms.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Scanner;

public class TesteConsumidor {

    public static void main(String args[]) throws NamingException, JMSException {

        InitialContext context = new InitialContext();

        ConnectionFactory factory = (ConnectionFactory) context.lookup("ConnectionFactory");
        Connection connection = factory.createConnection();
        connection.start();

        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        Destination fila = (Destination) context.lookup("financeiro");

        MessageConsumer consumer = session.createConsumer(fila);

        Message mensagem = consumer.receive();

        System.out.println("Recebendo mensagem: " + mensagem);

        new Scanner(System.in).nextLine();

        session.close();
        connection.close();
        context.close();

    }

}
