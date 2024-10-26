package org.hiedacamellia.watersource.common.trigger;

import com.google.gson.JsonObject;
import net.minecraft.advancements.critereon.*;
import org.hiedacamellia.watersource.WaterSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class WaterLevelRestoredTrigger extends SimpleCriterionTrigger<WaterLevelRestoredTrigger.TriggerInstance> {
    public static final ResourceLocation ID = new ResourceLocation(WaterSource.MODID, "water_level_restored");
    @Override
    public ResourceLocation getId() {
        return ID;
    }

    public void trigger(ServerPlayer player){
        this.trigger(player, triggerInstance -> true);
    }

    @Override
    protected TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
        return new TriggerInstance(contextAwarePredicate);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance{
        public TriggerInstance(ContextAwarePredicate composite) {
            super(ID, composite);
        }
    }
}

