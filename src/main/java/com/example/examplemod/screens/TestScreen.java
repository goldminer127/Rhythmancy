package com.example.examplemod.screens;

import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.client.Minecraft;
import net.neoforged.neoforge.common.NeoForgeConfig;

public class TestScreen extends Screen
{
    public TestScreen(Component Title)
    {
        super(Title);
    }

    @Override
    protected void init()
    {
        super.init();

        Minecraft client = Minecraft.getInstance();
        //assert client.player != null;
        //add valid item statement
    }
}
