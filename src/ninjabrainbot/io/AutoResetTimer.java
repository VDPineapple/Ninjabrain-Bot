package ninjabrainbot.io;

import javax.swing.Timer;

import ninjabrainbot.Main;
import ninjabrainbot.calculator.IDataState;
import ninjabrainbot.calculator.IDataStateHandler;
import ninjabrainbot.util.IDisposable;
import ninjabrainbot.util.SubscriptionHandler;

public class AutoResetTimer extends Timer implements IDisposable {

	private static final long serialVersionUID = 607205414762178442L;

	private static final int AUTO_RESET_DELAY = 15 * 60 * 1000;

	SubscriptionHandler sh = new SubscriptionHandler();

	public AutoResetTimer(IDataState dataState, IDataStateHandler dataStateHandler) {
		super(AUTO_RESET_DELAY, null);
		addActionListener(p -> {
			dataStateHandler.resetIfNotLocked();
			restart();
			stop();
		});
		sh.add(dataState.locked().subscribe(__ -> restart()));
		sh.add(dataState.blindResult().subscribe(__ -> restart()));
		sh.add(dataState.calculatorResult().subscribe(__ -> restart()));
		sh.add(Main.preferences.autoReset.whenModified().subscribe(b -> onAutoResetEnabledChanged(b)));
	}

	private void onAutoResetEnabledChanged(boolean b) {
		if (b)
			start();
		else
			stop();
	}

	@Override
	public void dispose() {
		sh.dispose();
	}

}
