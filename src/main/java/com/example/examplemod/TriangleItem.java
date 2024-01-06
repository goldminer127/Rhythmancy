package com.example.examplemod;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class TriangleItem extends Item{
    public TriangleItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult onItemUseFirst(ItemStack stack, UseOnContext context) {
        if(context.getLevel().isClientSide()){
            BlockPos pos = context.getClickedPos();
            Player player = context.getPlayer();

            String s = "";
            s = "Player: " + player.toString() + ", position:" + pos.toString();

            MutableComponent text = Component.literal("Player: " + player.toString() + ", position:" + pos);
            player.sendSystemMessage(text);
        }
        return super.onItemUseFirst(stack, context);
    }
}
