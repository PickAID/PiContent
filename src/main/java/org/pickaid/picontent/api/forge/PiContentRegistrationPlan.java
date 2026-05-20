package org.pickaid.picontent.api.forge;

import org.pickaid.picontent.api.block.PiBlockPlan;
import org.pickaid.picontent.api.blockentity.PiBlockEntityPlan;
import org.pickaid.picontent.api.item.PiItemPlan;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class PiContentRegistrationPlan {
    private final String modId;
    private final Map<String, PiContentItemRegistration> items = new LinkedHashMap<>();
    private final Map<String, PiContentBlockRegistration> blocks = new LinkedHashMap<>();
    private final Map<String, PiContentBlockEntityRegistration> blockEntities = new LinkedHashMap<>();

    private PiContentRegistrationPlan(String modId) {
        if (modId == null || modId.isBlank()) {
            throw new IllegalArgumentException("modId must not be blank");
        }
        this.modId = modId;
    }

    public static PiContentRegistrationPlan create(String modId) {
        return new PiContentRegistrationPlan(modId);
    }

    public PiContentRegistrationPlan item(PiItemPlan plan) {
        items.put(plan.name(), new PiContentItemRegistration(plan, id(plan.name())));
        return this;
    }

    public PiContentRegistrationPlan block(PiBlockPlan plan) {
        return addBlock(plan, false);
    }

    public PiContentRegistrationPlan blockEntityBlock(PiBlockPlan plan) {
        return addBlock(plan, true);
    }

    public PiContentRegistrationPlan blockEntity(PiBlockEntityPlan plan) {
        blockEntities.put(plan.name(), new PiContentBlockEntityRegistration(plan, id(plan.name()), plan.validBlocks()));
        return this;
    }

    public String modId() {
        return modId;
    }

    public PiContentItemRegistration item(String name) {
        return items.get(name);
    }

    public PiContentBlockRegistration block(String name) {
        return blocks.get(name);
    }

    public PiContentBlockEntityRegistration blockEntity(String name) {
        return blockEntities.get(name);
    }

    public List<PiContentItemRegistration> items() {
        return List.copyOf(items.values());
    }

    public List<PiContentBlockRegistration> blocks() {
        return List.copyOf(blocks.values());
    }

    public List<PiContentBlockEntityRegistration> blockEntities() {
        return List.copyOf(blockEntities.values());
    }

    private PiContentRegistrationPlan addBlock(PiBlockPlan plan, boolean blockEntityHost) {
        blocks.put(plan.name(), new PiContentBlockRegistration(plan, id(plan.name()), blockEntityHost));
        if (plan.simpleItem() && !items.containsKey(plan.name())) {
            items.put(plan.name(), new PiContentItemRegistration(blockItemPlan(plan), id(plan.name())));
        }
        return this;
    }

    private PiItemPlan blockItemPlan(PiBlockPlan plan) {
        return new PiItemPlan(
                plan.name(),
                plan.section(),
                org.pickaid.picontent.api.item.PiItemPropertiesPlan.material(),
                plan.itemTags(),
                "",
                org.pickaid.picontent.api.item.PiItemModels.generated("block/" + plan.name()),
                List.of(),
                List.of()
        );
    }

    private String id(String path) {
        return modId + ":" + path;
    }
}
