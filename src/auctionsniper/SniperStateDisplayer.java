package auctionsniper;

import javax.swing.SwingUtilities;

public class SniperStateDisplayer implements SniperListener {

	@Override
	public void sniperBidding() {
		showStatus(Main.STATUS_BIDDING);

	}

	@Override
	public void sniperLost() {
		showStatus(Main.STATUS_LOST);

	}

	private void showStatus(final String status) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				Main.ui.showStatus(status);
			}
		});
	}
}
