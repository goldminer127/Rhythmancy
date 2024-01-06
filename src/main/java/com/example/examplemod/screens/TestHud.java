package com.example.examplemod.screens;

import com.example.examplemod.ExampleMod;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.client.gui.overlay.IGuiOverlay;

public class TestHud
{
    private static final ResourceLocation BAR = new ResourceLocation(ExampleMod.MODID, "textures/hud/bar.png");

    public static final IGuiOverlay HUD_BAR = ((gui, guiGraphics, partialTick, screenWidth, screenHeight) -> {
        int x = screenWidth / 2;
        int y = screenHeight;

        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(0F, 0F, 0F, 0F);
        guiGraphics.blit(BAR, x + 80, y / 2 - 120,0,0,350,200);
    });
}
