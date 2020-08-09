package pubsub.services;

public class SubscriberImpl extends Subscriber {
    @Override
    public void addSubscriber(String topic, PubSubService pubSubService) {
        pubSubService.addSubscriber(topic, this);
    }

    @Override
    public void unSubscribe(String topic, PubSubService pubSubService) {
        pubSubService.removeSubscriber(topic, this);
    }

    @Override
    public void getMessagesForSubscriberOfTopic(String topic, PubSubService pubSubService) {
        pubSubService.getMessagesForSubscriberOfTopic(topic, this);
    }
}
