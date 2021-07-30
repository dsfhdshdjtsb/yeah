package com.dsfhdshdjtsb.doors;

import com.dsfhdshdjtsb.doors.Registry.ModBlocks;
import com.dsfhdshdjtsb.doors.gui.LockedDoorScreenHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class Doors implements ModInitializer {
    public static final String MOD_ID = "ldoors";



    @Override
    public void onInitialize() {
        ModBlocks.registerBlocks();
    }
}
