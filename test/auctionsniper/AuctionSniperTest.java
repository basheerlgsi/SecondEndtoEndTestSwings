package auctionsniper;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.Test;

public class AuctionSniperTest {
	private final Mockery context = new Mockery();
	private final SniperListener sniperListener = context
			.mock(SniperListener.class);
	private final Auction auction = context.mock(Auction.class);
	private final AuctionSniper sniper = new AuctionSniper(auction,
			sniperListener);

	@Test
	public void reportsLostWhenAuctionCloses() {
		context.checking(new Expectations() {
			{
				one(sniperListener).sniperLost();
			}
		});
		sniper.auctionClosed();
	}

	@Test
	public void bidsHigherAndReportsBiddingWhenNewPriceArrives() {
		final int price = 1001;
		final int increment = 25;
		context.checking(new Expectations() {
			{
				one(auction).bid(price + increment);
				atLeast(1).of(sniperListener).sniperBidding();
			}
		});
		sniper.currentPrice(price, increment);
	}

}
