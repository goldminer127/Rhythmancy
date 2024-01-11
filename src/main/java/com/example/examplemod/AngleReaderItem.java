package com.example.examplemod;

import com.example.examplemod.enums.Direction;
import com.mojang.logging.LogUtils;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.level.NoteBlockEvent;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;

public class AngleReaderItem extends Item
{
    private static final Logger LOGGER = LogUtils.getLogger();
    private Vec3 _initialAngle;
    public AngleReaderItem(Properties properties)
    {
        super(properties);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(@NotNull Level level, @NotNull Player player, @NotNull InteractionHand hand)
    {
        player.startUsingItem(hand);
        _initialAngle = player.getLookAngle();
        return InteractionResultHolder.pass(player.getItemInHand(hand));
    }
    @Override
    public void releaseUsing(@NotNull ItemStack itemStack, @NotNull Level level, @NotNull LivingEntity entity, int duration)
    {
        if(entity instanceof Player)
        {
            var endAngle = entity.getLookAngle();
            //entity.sendSystemMessage(Component.literal(getDirection(_initialAngle, endAngle).toString()));
            entity.sendSystemMessage(Component.literal(Integer.toString(getPitchStep(endAngle))));
        }
    }
    @Override
    public int getUseDuration(@NotNull ItemStack item)
    {
        return Integer.MAX_VALUE;
    }
    private Direction getDirection(Vec3 initial, Vec3 end)
    {
        double yDist = initial.y - end.y;
        double horizontalDirection = initial.cross(end).y;

        if(Math.abs(yDist) < 0.15 && horizontalDirection > 0.0)
            return Direction.Left;
        else if(Math.abs(yDist) < 0.15 && horizontalDirection < 0.0)
            return Direction.Right;
        if(yDist < 0)
            return Direction.Up;
        else
            return Direction.Down;
    }
    //Maybe use start angle and adjust the pitch accept range after? so if start y is 0, down pitch range would be steps of 0.12 both directions, but if you start at -20 then down steps are 10 and high steps are 15
    private int getPitchStep(Vec3 end)
    {
        LOGGER.info(end.toString());
        int direction = (end.y > 0) ? 1 : -1;
        return (int)Math.floor(Math.abs(end.y / 0.15)) * direction;
    }
}
