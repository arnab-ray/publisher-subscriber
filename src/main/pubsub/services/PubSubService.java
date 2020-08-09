package pubsub.services;

import pubsub.models.Message;

import java.util.*;

public class PubSubService {
    Map<String, Set<Subscriber>> subscribersTopicMap = new HashMap<String, Set<Subscriber>>();

    Queue<Message> messagesQueue = new LinkedList<Message>();

    public void addMessageToQueue(Message message){
        messagesQueue.add(message);
    }

    public void addSubscriber(String topic, Subscriber subscriber){

        Set<Subscriber> subscribers;
        if(subscribersTopicMap.containsKey(topic)){
            subscribers = subscribersTopicMap.get(topic);
        }else{
            subscribers = new HashSet<Subscriber>();
        }
        subscribers.add(subscriber);
        subscribersTopicMap.put(topic, subscribers);
    }

    public void removeSubscriber(String topic, Subscriber subscriber){

        if(subscribersTopicMap.containsKey(topic)){
            Set<Subscriber> subscribers = subscribersTopicMap.get(topic);
            subscribers.remove(subscriber);
            subscribersTopicMap.put(topic, subscribers);
        }
    }

    public void broadcast(){
        if(messagesQueue.isEmpty()){
            System.out.println("No messages from publishers to display");
        }else{
            while(!messagesQueue.isEmpty()){
                Message message = messagesQueue.remove();
                String topic = message.getTopic();

                Set<Subscriber> subscribersOfTopic = subscribersTopicMap.get(topic);

                for(Subscriber subscriber : subscribersOfTopic){
                    //add broadcasted message to subscribers message queue
                    List<Message> subscriberMessages = subscriber.getSubscriberMessages();
                    subscriberMessages.add(message);
                    subscriber.setSubscriberMessages(subscriberMessages);
                }
            }
        }
    }

    public void getMessagesForSubscriberOfTopic(String topic, Subscriber subscriber) {
        if(messagesQueue.isEmpty()){
            System.out.println("No messages from publishers to display");
        }else{
            while(!messagesQueue.isEmpty()){
                Message message = messagesQueue.remove();

                if(message.getTopic().equalsIgnoreCase(topic)){

                    Set<Subscriber> subscribersOfTopic = subscribersTopicMap.get(topic);

                    for(Subscriber _subscriber : subscribersOfTopic){
                        if(_subscriber.equals(subscriber)){
                            //add broadcasted message to subscriber message queue
                            List<Message> subscriberMessages = subscriber.getSubscriberMessages();
                            subscriberMessages.add(message);
                            subscriber.setSubscriberMessages(subscriberMessages);
                        }
                    }
                }
            }
        }
    }
}
