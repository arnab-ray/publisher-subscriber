package pubsub.services;

import pubsub.models.Message;

public class PublisherImpl implements Publisher {
    @Override
    public void publish(Message message, PubSubService pubSubService) {
        pubSubService.addMessageToQueue(message);
    }
}
