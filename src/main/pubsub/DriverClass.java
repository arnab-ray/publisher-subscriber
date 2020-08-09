package pubsub;

import pubsub.models.Message;
import pubsub.services.*;

public class DriverClass {

    public static void main(String[] args) {
        Publisher javaPublisher = new PublisherImpl();
        Publisher pythonPublisher = new PublisherImpl();

        Subscriber javaSubscriber = new SubscriberImpl();
        Subscriber allLanguagesSubscriber = new SubscriberImpl();
        Subscriber pythonSubscriber = new SubscriberImpl();

        PubSubService pubSubService = new PubSubService();

        Message javaMsg1 = new Message("Java", "Core Java Concepts");
        Message javaMsg2 = new Message("Java", "Spring MVC : Dependency Injection and AOP");
        Message javaMsg3 = new Message("Java", "JPA & Hibernate");

        javaPublisher.publish(javaMsg1, pubSubService);
        javaPublisher.publish(javaMsg2, pubSubService);
        javaPublisher.publish(javaMsg3, pubSubService);

        Message pythonMsg1 = new Message("Python", "Easy and Powerful programming language");
        Message pythonMsg2 = new Message("Python", "Advanced Python message");

        pythonPublisher.publish(pythonMsg1, pubSubService);
        pythonPublisher.publish(pythonMsg2, pubSubService);


        javaSubscriber.addSubscriber("Java",pubSubService);		//Java subscriber only subscribes to Java topics
        pythonSubscriber.addSubscriber("Python",pubSubService);   //Python subscriber only subscribes to Python topics
        allLanguagesSubscriber.addSubscriber("Java", pubSubService);	//all subscriber, subscribes to both Java and Python
        allLanguagesSubscriber.addSubscriber("Python", pubSubService);

        pubSubService.broadcast();

        System.out.println("Messages of Java Subscriber are: ");
        javaSubscriber.printMessages();

        System.out.println("\nMessages of Python Subscriber are: ");
        pythonSubscriber.printMessages();

        System.out.println("\nMessages of All Languages Subscriber are: ");
        allLanguagesSubscriber.printMessages();

        //After broadcast the messagesQueue will be empty, so publishing new messages to server
        System.out.println("\nPublishing 2 more Java Messages...");
        Message javaMsg4 = new Message("Java", "JSP and Servlets");
        Message javaMsg5 = new Message("Java", "Struts framework");

        javaPublisher.publish(javaMsg4, pubSubService);
        javaPublisher.publish(javaMsg5, pubSubService);

        javaSubscriber.getMessagesForSubscriberOfTopic("Java", pubSubService);
        System.out.println("\nMessages of Java Subscriber now are: ");
        javaSubscriber.printMessages();
    }
}
