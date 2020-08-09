package pubsub.services;

import pubsub.models.Message;

public interface Publisher {
    void publish(Message message, PubSubService pubSubService);
}
