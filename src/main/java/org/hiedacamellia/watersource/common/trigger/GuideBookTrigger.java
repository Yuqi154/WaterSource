package org.hiedacamellia.watersource.common.trigger;

import com.google.gson.JsonObject;
import org.hiedacamellia.watersource.WaterSource;
import org.hiedacamellia.watersource.registry.ConfigRegistry;
import net.minecraft.advancements.critereon.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;

public class GuideBookTrigger extends SimpleCriterionTrigger<GuideBookTrigger.TriggerInstance> {
    public static final ResourceLocation ID = new ResourceLocation(WaterSource.MODID,"guide_book");

    public ResourceLocation getId() {
        return ID;
    }


    public void trigger(ServerPlayer player) {
        if (ConfigRegistry.GIVE_GUIDE_BOOK_ON_JOINING_GAME.get()){
            this.trigger(player, (p_70648_) -> {
                return true;
            });
        }
    }

    @Override
    protected TriggerInstance createInstance(JsonObject jsonObject, ContextAwarePredicate contextAwarePredicate, DeserializationContext deserializationContext) {
        return new TriggerInstance(contextAwarePredicate);
    }

    public static class TriggerInstance extends AbstractCriterionTriggerInstance {
        public TriggerInstance(ContextAwarePredicate p_70654_) {
            super(GuideBookTrigger.ID, p_70654_);
        }
    }
}
