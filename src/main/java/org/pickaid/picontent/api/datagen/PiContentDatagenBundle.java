package org.pickaid.picontent.api.datagen;

import org.pickaid.picontent.api.block.PiBlockPlan;
import org.pickaid.picontent.api.blockentity.PiBlockEntityPlan;
import org.pickaid.picontent.api.item.PiItemPlan;

import java.util.ArrayList;
import java.util.List;

public final class PiContentDatagenBundle {
    private final List<PiLangDeclaration> lang = new ArrayList<>();
    private final List<PiModelDeclaration> itemModels = new ArrayList<>();
    private final List<PiModelDeclaration> blockstates = new ArrayList<>();
    private final List<PiModelDeclaration> blockModels = new ArrayList<>();
    private final List<PiLootDeclaration> loot = new ArrayList<>();
    private final List<PiTagDeclaration> tags = new ArrayList<>();
    private final List<PiCreativeSampleDeclaration> creativeSamples = new ArrayList<>();
    private final List<String> blockEntityNames = new ArrayList<>();

    private PiContentDatagenBundle() {
    }

    public static PiContentDatagenBundle empty() {
        return new PiContentDatagenBundle();
    }

    public PiContentDatagenBundle item(PiItemPlan plan) {
        if (!plan.lang().isBlank()) {
            lang.add(new PiLangDeclaration("item", plan.name(), plan.lang()));
        }
        itemModels.add(new PiModelDeclaration("item", plan.name(), plan.model().preset(), plan.model().texture()));
        plan.itemTags().forEach(tag -> tags.add(new PiTagDeclaration("item", tag, plan.name())));
        return this;
    }

    public PiContentDatagenBundle block(PiBlockPlan plan) {
        blockstates.add(new PiModelDeclaration("blockstate", plan.name(), plan.blockstate().preset(), plan.blockstate().model()));
        blockModels.add(new PiModelDeclaration("block", plan.name(), plan.blockstate().preset(), plan.blockstate().model()));
        loot.add(new PiLootDeclaration(plan.name(), plan.loot().preset()));
        plan.blockTags().forEach(tag -> tags.add(new PiTagDeclaration("block", tag, plan.name())));
        plan.itemTags().forEach(tag -> tags.add(new PiTagDeclaration("item", tag, plan.name())));
        if (plan.simpleItem() && !plan.creativeSection().isBlank()) {
            creativeSamples.add(new PiCreativeSampleDeclaration(plan.creativeSection(), plan.name()));
        }
        return this;
    }

    public PiContentDatagenBundle blockEntity(PiBlockEntityPlan plan) {
        blockEntityNames.add(plan.name());
        return this;
    }

    public List<PiLangDeclaration> lang() {
        return List.copyOf(lang);
    }

    public List<PiModelDeclaration> itemModels() {
        return List.copyOf(itemModels);
    }

    public List<PiModelDeclaration> blockstates() {
        return List.copyOf(blockstates);
    }

    public List<PiModelDeclaration> blockModels() {
        return List.copyOf(blockModels);
    }

    public List<PiLootDeclaration> loot() {
        return List.copyOf(loot);
    }

    public List<PiTagDeclaration> tags() {
        return List.copyOf(tags);
    }

    public List<PiCreativeSampleDeclaration> creativeSamples() {
        return List.copyOf(creativeSamples);
    }

    public List<String> blockEntityNames() {
        return List.copyOf(blockEntityNames);
    }
}
