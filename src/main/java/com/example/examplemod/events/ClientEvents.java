package com.example.examplemod.events;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.screens.TestHud;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;
import net.neoforged.neoforge.client.event.RenderGuiOverlayEvent;
import org.slf4j.Logger;

public class ClientEvents
{
    private static final Logger LOGGER = LogUtils.getLogger();
    @Mod.EventBusSubscriber(modid = ExampleMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents
    {
        @SubscribeEvent
        public static void registerOverlays(RegisterGuiOverlaysEvent event)
        {
            event.registerAboveAll(new ResourceLocation(ExampleMod.MODID), TestHud.HUD_BAR);
        }
    }
}
