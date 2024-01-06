package rhythmancy.tempo.hud;

import com.mojang.blaze3d.systems.RenderSystem;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import rhythmancy.tempo.RhythmancyClient;
import rhythmancy.tempo.items.RhythmancyItem;

import java.util.Arrays;

public class TestHud implements HudRenderCallback {
    private final Identifier MUSIC_BAR = new Identifier(RhythmancyClient.MOD_ID, "textures/instrument/bar.png");
    private final Item[] ValidDisplayItems = new Item[]{RhythmancyItem.TRIANGLE, RhythmancyItem.GUIItem};
    @Override
    public void onHudRender(DrawContext drawContext, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();
        if(client != null) {
            assert client.player != null;
            if (ValidateItem(client.player.getMainHandStack())) {
                int x = 0;
                int y = 0;

                int width = client.getWindow().getScaledWidth();
                int height = client.getWindow().getScaledHeight();

                x = width / 2;
                y = height;

                RenderSystem.setShader(GameRenderer::getPositionTexProgram);
                RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
                RenderSystem.setShaderTexture(0, MUSIC_BAR);
                drawContext.drawTexture(MUSIC_BAR, x + 80, y / 2 - 120, 0, 0, 350, 200);
            }
        }
    }
    public boolean ValidateItem(ItemStack heldItem) {
        if(heldItem != null) {
            return Arrays.stream(ValidDisplayItems).anyMatch(Item -> heldItem.getName().equals(Item.getName()));
        }
        return false;
    }
}
