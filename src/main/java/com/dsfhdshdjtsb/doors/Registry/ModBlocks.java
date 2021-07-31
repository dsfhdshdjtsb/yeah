package com.dsfhdshdjtsb.doors.Registry;

import com.dsfhdshdjtsb.doors.Doors;
import com.dsfhdshdjtsb.doors.blocks.LockedDoor;
import com.dsfhdshdjtsb.doors.blocks.LockedDoorEntity;
import com.dsfhdshdjtsb.doors.blocks.LockedDoorScreenHandler;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

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








    public static void registerBlocks()
    {
        Registry.register(Registry.BLOCK, new Identifier(Doors.MOD_ID, "locked_door"), LOCKED_DOOR);

        Registry.register(Registry.ITEM, new Identifier(Doors.MOD_ID, "locked_door"), new BlockItem(LOCKED_DOOR,
                new FabricItemSettings().group(ItemGroup.MISC)));

        LOCKED_DOOR_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, Doors.MOD_ID, FabricBlockEntityTypeBuilder.create(LockedDoorEntity::new, LOCKED_DOOR).build(null));
    }
}
