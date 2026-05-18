package org.pickaid.picontent.api.block;

import java.util.ArrayList;
import java.util.List;

public final class PiBlockBuilder {
    private final String name;
    private String section = "";
    private PiBlockPropertiesPlan properties = PiBlockPresets.stone();
    private final List<String> blockTags = new ArrayList<>();
    private final List<String> itemTags = new ArrayList<>();
    private PiBlockShapeDeclaration shape = PiBlockShapes.fullCube();
    private PiBlockPlacementDeclaration placement = PiBlockPlacement.none();
    private PiBlockLootDeclaration loot = PiBlockLoot.self();
    private PiBlockModelDeclaration blockstate = PiBlockModels.simple("");
    private boolean simpleItem;
    private String creativeSection = "";

    private PiBlockBuilder(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        this.name = name;
    }

    public static PiBlockBuilder named(String name) {
        return new PiBlockBuilder(name);
    }

    public PiBlockBuilder section(String section) {
        this.section = section == null ? "" : section;
        return this;
    }

    public PiBlockBuilder properties(PiBlockPropertiesPlan properties) {
        this.properties = properties;
        return this;
    }

    public PiBlockBuilder blockTag(String blockTag) {
        this.blockTags.add(blockTag);
        return this;
    }

    public PiBlockBuilder itemTag(String itemTag) {
        this.itemTags.add(itemTag);
        return this;
    }

    public PiBlockBuilder shape(PiBlockShapeDeclaration shape) {
        this.shape = shape;
        return this;
    }

    public PiBlockBuilder placement(PiBlockPlacementDeclaration placement) {
        this.placement = placement;
        return this;
    }

    public PiBlockBuilder loot(PiBlockLootDeclaration loot) {
        this.loot = loot;
        return this;
    }

    public PiBlockBuilder blockstate(PiBlockModelDeclaration blockstate) {
        this.blockstate = blockstate;
        return this;
    }

    public PiBlockBuilder simpleItem() {
        this.simpleItem = true;
        return this;
    }

    public PiBlockBuilder creative(String creativeSection) {
        this.creativeSection = creativeSection == null ? "" : creativeSection;
        return this;
    }

    public PiBlockPlan plan() {
        return new PiBlockPlan(
                name,
                section,
                properties,
                blockTags,
                itemTags,
                shape,
                placement,
                loot,
                blockstate,
                simpleItem,
                creativeSection
        );
    }
}
