package auctionsniper;

import org.jivesoftware.smack.Chat;
import org.jivesoftware.smack.packet.Message;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JMock;
import org.junit.Test;
import org.junit.runner.RunWith;

import junit.framework.TestCase;

@RunWith(JMock.class)
public class AuctionMessageTranslatorTest extends TestCase {

	private final Mockery context = new Mockery();
	private final AuctionEventListener listener = context
			.mock(AuctionEventListener.class);
	private final AuctionMessageTranslator translator = new AuctionMessageTranslator(
			listener);

	public static final Chat UNUSED_CHAT = null;

	@Test
	public void notifiesAuctionClosedWhenCloseMessageReceived() {
		context.checking(new Expectations() {
			{
				oneOf(listener).auctionClosed();
			}
		});

		Message message = new Message();
		message.setBody(Main.CLOSE_EVENT_FORMAT);
		translator.processMessage(UNUSED_CHAT, message);
	}

	@Test
	public void notifiesBidDetailsWhenCurrentPriceMessageReceived() {
		context.checking(new Expectations() {
			{
				exactly(1).of(listener).currentPrice(192, 7);
			}
		});
		Message message = new Message();
		message
				.setBody("SOLVersion: 1.1; Event: PRICE; CurrentPrice: 192; Increment: 7; Bidder: Someone else;");
		translator.processMessage(UNUSED_CHAT, message);
	}
}
