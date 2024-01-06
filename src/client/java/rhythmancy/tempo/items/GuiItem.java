package rhythmancy.tempo.items;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import rhythmancy.tempo.RhythmancyClient;
import rhythmancy.tempo.gui.ExampleGuiDescription;
import rhythmancy.tempo.hud.TestHud;

public class GuiItem extends Item {
    public GuiItem(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient) {
            user.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
                return new ExampleGuiDescription(i, playerInventory, ScreenHandlerContext.EMPTY);
            }, Text.translatable("screen.yourmod.your_gui")));
        }
        return TypedActionResult.success(user.getStackInHand(hand));
    }
    /*
    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient && entity.isPlayer() && selected) {
            PlayerEntity user = (PlayerEntity) entity;
            user.openHandledScreen(new SimpleNamedScreenHandlerFactory((i, playerInventory, playerEntity) -> {
                return new ExampleGuiDescription(i, playerInventory, ScreenHandlerContext.EMPTY);
            }, Text.translatable("screen.yourmod.your_gui")));
        }
    }
    */
}
