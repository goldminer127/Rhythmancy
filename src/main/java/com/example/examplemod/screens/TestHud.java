package com.example.examplemod.screens;

import com.example.examplemod.ExampleMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;

import java.util.Arrays;

public class TestHud
{
    private static final ResourceLocation BAR = new ResourceLocation(ExampleMod.MODID, "textures/hud/bar.png");
    private static final Item[] ValidDisplayItems = new Item[]{ExampleMod.TRIANGLE.asItem()};

    public static final IGuiOverlay HUD_BAR = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        Minecraft client = Minecraft.getInstance();
        assert client.player != null;
        if(ValidateItem(client.player.getMainHandItem()))
        {
            int x = screenWidth / 2;
            int y = screenHeight;

            RenderSystem.setShader(GameRenderer::getPositionTexShader);
            RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
            guiGraphics.blit(BAR, x + 80, y / 2 - 120,0,0,350,200);
        }
    });
    public static boolean ValidateItem(ItemStack heldItem) {
        if(heldItem != null) {
            return Arrays.stream(ValidDisplayItems).anyMatch(item -> heldItem.getItem().getName(heldItem).getString().equals(item.getName(null).getString()));
        }
        return false;
    }
}
