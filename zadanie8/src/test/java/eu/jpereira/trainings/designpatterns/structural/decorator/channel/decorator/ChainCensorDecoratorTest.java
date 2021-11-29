package eu.jpereira.trainings.designpatterns.structural.decorator.channel.decorator;

import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannel;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelBuilder;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelProperties;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.SocialChannelPropertyKey;
import eu.jpereira.trainings.designpatterns.structural.decorator.channel.spy.TestSpySocialChannel;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ChainCensorDecoratorTest extends AbstractSocialChanneldDecoratorTest {
    @Test
    public void chainWordCensorWithTruncatorTest() {
        SocialChannelBuilder builder = createTestSpySocialChannelBuilder();

        SocialChannelProperties props = new SocialChannelProperties().putProperty(SocialChannelPropertyKey.NAME, TestSpySocialChannel.NAME);
        TestSpySocialChannel spyChannel = (TestSpySocialChannel) builder.buildChannel(props);

        SocialChannel channel = builder.with(new WordCensor("Microsoft")).with(new MessageTruncator(10))
                .getDecoratedChannel(props);

        channel.deliverMessage("Microsoft is a great company. I really like Microsoft.");

        assertEquals("### is ...", spyChannel.lastMessagePublished());
    }
}
