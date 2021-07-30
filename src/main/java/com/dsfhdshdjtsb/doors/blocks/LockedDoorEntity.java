package com.dsfhdshdjtsb.doors.blocks;

import com.dsfhdshdjtsb.doors.Registry.ModBlocks;
import com.dsfhdshdjtsb.doors.gui.LockedDoorScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;

public class LockedDoorEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(9, ItemStack.EMPTY);
    private int[] players;
    public LockedDoorEntity(BlockPos pos, BlockState state) {
        super(ModBlocks.LOCKED_DOOR_ENTITY, pos, state);
        players = new int[10];

    }

    public NbtCompound writeNbt(NbtCompound tag)
    {
        super.writeNbt(tag);
        tag.putIntArray("players", players);
        Inventories.writeNbt(tag, this.inventory);
        return tag;
    }

    public void readNbt(NbtCompound tag)
    {
        super.readNbt(tag);
        players = tag.getIntArray("players");
        Inventories.readNbt(tag, this.inventory);
    }


    public boolean contains(int player)
    {
        for(Integer i : players) {
            if (i == player)
                return true;
        }
        return false;
    }

    public void add(int player)
    {
        for(int i = 0; i < players.length; i++)
        {
            if(players[i] == player)
                return;
            if(players[i] == 0)
            {
                players[i] = player;
                return;
            }
        }
    }

    public void print()
    {
        for(Integer i : players)
            System.out.println(i);
    }

    @Override
    public Text getDisplayName() {
        return null;
    }

    @Override
    public @Nullable ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        //We provide *this* to the screenHandler as our class Implements Inventory
        //Only the Server has the Inventory at the start, this will be synced to the client in the ScreenHandler
        return new LockedDoorScreenHandler(syncId, inv, this);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }




}