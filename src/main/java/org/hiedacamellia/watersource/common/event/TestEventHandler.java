package org.hiedacamellia.watersource.common.event;

import org.hiedacamellia.watersource.WaterSource;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = WaterSource.MODID)
public class TestEventHandler {
    @SubscribeEvent
    public static void onPlayerRightClickEmpty(PlayerInteractEvent.RightClickEmpty event) {
    }
}
