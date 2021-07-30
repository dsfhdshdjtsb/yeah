package com.dsfhdshdjtsb.doors.blocks;

import com.dsfhdshdjtsb.doors.test.BoxBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class LockedDoor extends DoorBlock implements BlockEntityProvider{
    private int getOpenSoundEventId() {
        return this.material == Material.METAL ? 1011 : 1012;
    }

    private int getCloseSoundEventId() {
        return this.material == Material.METAL ? 1005 : 1006;
    }

    public LockedDoor(Settings settings) {
        super(settings);
    }
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new LockedDoorEntity(pos, state);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (!world.isClient) {
            //This will call the createScreenHandlerFactory method from BlockWithEntity, which will return our blockEntity casted to
            //a namedScreenHandlerFactory. If your block class does not extend BlockWithEntity, it needs to implement createScreenHandlerFactory.
            NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);

            if (screenHandlerFactory != null) {
                //With this call the server will request the client to open the appropriate Screenhandler
                player.openHandledScreen(screenHandlerFactory);
            }
        }
        return ActionResult.SUCCESS;
    }


        /*
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof LockedDoorEntity) {
            if (!((LockedDoorEntity) blockEntity).contains(player.getName().asString().hashCode())) {
                if(world.isClient)
                    MinecraftClient.getInstance().setScreen(new LockedDoorScreen(new LockedDoorGui()));
                return ActionResult.PASS;
            } else {
                state = (BlockState) state.cycle(OPEN);
                world.setBlockState(pos, state, 10);
                world.syncWorldEvent(player, (Boolean) state.get(OPEN) ? this.getCloseSoundEventId() : this.getOpenSoundEventId(), pos, 0);
                return ActionResult.success(world.isClient);
            }
        }*/
        @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof BoxBlockEntity) {
                ItemScatterer.spawn(world, pos, (BoxBlockEntity)blockEntity);
                // update comparators
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
    }

}
