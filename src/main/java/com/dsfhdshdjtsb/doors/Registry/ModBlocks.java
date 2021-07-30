package com.dsfhdshdjtsb.doors.Registry;

import com.dsfhdshdjtsb.doors.Doors;
import com.dsfhdshdjtsb.doors.blocks.LockedDoor;
import com.dsfhdshdjtsb.doors.blocks.LockedDoorEntity;
import com.dsfhdshdjtsb.doors.gui.LockedDoorScreenHandler;
import com.dsfhdshdjtsb.doors.test.BoxBlock;
import com.dsfhdshdjtsb.doors.test.BoxBlockEntity;
import com.dsfhdshdjtsb.doors.test.BoxScreenHandler;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.concurrent.locks.Lock;

public class ModBlocks {

    public static final Block LOCKED_DOOR = new LockedDoor( FabricBlockSettings
            .of(Material.METAL)
            .breakByTool(FabricToolTags.PICKAXES, 4)
            .requiresTool()
            .strength(3.0F, 6.0F)
            .sounds(BlockSoundGroup.METAL));

    public static BlockEntityType<LockedDoorEntity> LOCKED_DOOR_ENTITY;

    public static final ScreenHandlerType<LockedDoorScreenHandler> LOCKED_DOOR_SCREEN_HANDLER;
    static {
        LOCKED_DOOR_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(Doors.MOD_ID,"locked_door"), LockedDoorScreenHandler::new);
    }



    //test area
    public static final Block BOX_BLOCK;
    public static final BlockItem BOX_BLOCK_ITEM;
    public static final BlockEntityType<BoxBlockEntity> BOX_BLOCK_ENTITY;

    public static final String MOD_ID = "testmod";
    // a public identifier for multiple parts of our bigger chest
    public static final Identifier BOX = new Identifier(MOD_ID, "box_block");
    public static final ScreenHandlerType<BoxScreenHandler> BOX_SCREEN_HANDLER;

    static {
        BOX_BLOCK = Registry.register(Registry.BLOCK, BOX, new BoxBlock(FabricBlockSettings
                .of(Material.METAL)
                .breakByTool(FabricToolTags.PICKAXES, 4)
                .requiresTool()
                .strength(3.0F, 6.0F)
                .sounds(BlockSoundGroup.METAL)));
        BOX_BLOCK_ITEM = Registry.register(Registry.ITEM, BOX, new BlockItem(BOX_BLOCK, new Item.Settings().group(ItemGroup.MISC)));

        //The parameter of build at the very end is always null, do not worry about it
        BOX_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, BOX,  FabricBlockEntityTypeBuilder.create(BoxBlockEntity::new, BOX_BLOCK).build(null));

        BOX_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(BOX, BoxScreenHandler::new);
    }







    public static void registerBlocks()
    {
        Registry.register(Registry.BLOCK, new Identifier(Doors.MOD_ID, "locked_door"), LOCKED_DOOR);

        Registry.register(Registry.ITEM, new Identifier(Doors.MOD_ID, "locked_door"), new BlockItem(LOCKED_DOOR,
                new FabricItemSettings().group(ItemGroup.MISC)));

        LOCKED_DOOR_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Doors.MOD_ID, FabricBlockEntityTypeBuilder.create(LockedDoorEntity::new, LOCKED_DOOR).build(null));
    }
}
