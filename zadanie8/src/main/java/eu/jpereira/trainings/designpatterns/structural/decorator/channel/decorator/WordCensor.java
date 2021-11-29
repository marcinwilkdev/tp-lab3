package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;

public class WordCensor extends SocialChannelDecorator {
    private String word;

    public WordCensor(String word) {
        this.word = word;
    }

    public WordCensor(String word, SocialChannel channel) {
        this(word);
        this.delegate = channel;
    }

    @Override
    public void deliverMessage(String message) {
        String censoredMessage = message.replaceAll(word, "###");

        if(delegate != null) {
            delegate.deliverMessage(censoredMessage);
        }
    }
}
