package com.example.examplemod.events;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.screens.TestHud;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterGuiOverlaysEvent;

public class ClientEvents
{
    @Mod.EventBusSubscriber(modid = ExampleMod.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents
    {
        @SubscribeEvent
        public static void registerOverlays(RegisterGuiOverlaysEvent event)
        {
            event.registerAboveAll(new ResourceLocation(ExampleMod.MODID, "textures/hud/bar.png"), TestHud.HUD_BAR);
        }
    }
}
