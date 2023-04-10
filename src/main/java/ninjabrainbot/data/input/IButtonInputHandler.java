package ninjabrainbot.data.input;

import ninjabrainbot.data.calculator.endereye.IEnderEyeThrow;

public interface IButtonInputHandler {

	void onResetButtonPressed();

	void onUndoButtonPressed();

	void onRedoButtonPressed();

	void onRemoveFossilButtonPressed();

	void onRemoveThrowButtonPressed(IEnderEyeThrow throwToRemove);

}
