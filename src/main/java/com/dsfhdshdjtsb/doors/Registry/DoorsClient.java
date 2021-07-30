package com.dsfhdshdjtsb.doors.Registry;

import com.dsfhdshdjtsb.doors.gui.LockedDoorScreen;
import com.dsfhdshdjtsb.doors.test.BoxScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class DoorsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ModBlocks.LOCKED_DOOR_SCREEN_HANDLER, LockedDoorScreen::new);

        ScreenRegistry.register(ModBlocks.BOX_SCREEN_HANDLER, BoxScreen::new);
    }
}
