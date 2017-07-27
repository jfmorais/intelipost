package intelipost.login.queue;

public interface MessagePublisher {

    void publish(final String message);
}