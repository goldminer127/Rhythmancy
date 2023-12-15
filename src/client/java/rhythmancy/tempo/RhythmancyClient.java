package rhythmancy.tempo;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import rhythmancy.tempo.gui.ExampleGUI;
import rhythmancy.tempo.gui.ExampleScreen;
import rhythmancy.tempo.items.RhythmancyItem;

public class RhythmancyClient implements ClientModInitializer {
	public static final String MOD_ID = "rhythmancy";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);
	private static KeyBinding keyBinding;
	@Override
	public void onInitializeClient() {
		// This entrypoint is suitable for setting up client-specific logic, such as rendering.
		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.rhythmancy.testgui", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_M, // The keycode of the key
				"category.rhythmancy.test" // The translation key of the keybinding's category.
		));
		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (keyBinding.wasPressed()) {
				client.setScreen(new ExampleScreen(new ExampleGUI()));
			}
		});
		RhythmancyItem.registerModItems();
	}
}