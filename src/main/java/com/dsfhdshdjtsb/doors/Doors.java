package com.dsfhdshdjtsb.doors;

import com.dsfhdshdjtsb.doors.Registry.ModBlocks;
import net.fabricmc.api.ModInitializer;

public class Doors implements ModInitializer {

    public static final String MOD_ID = "ldoors";

    @Override
    public void onInitialize() {
        ModBlocks.registerBlocks();
    }
}
