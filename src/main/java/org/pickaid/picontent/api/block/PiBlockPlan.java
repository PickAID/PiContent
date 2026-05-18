package org.pickaid.picontent.api.block;

import java.util.List;

public record PiBlockPlan(
        String name,
        String section,
        PiBlockPropertiesPlan properties,
        List<String> blockTags,
        List<String> itemTags,
        PiBlockShapeDeclaration shape,
        PiBlockPlacementDeclaration placement,
        PiBlockLootDeclaration loot,
        PiBlockModelDeclaration blockstate,
        boolean simpleItem,
        String creativeSection
) {
    public PiBlockPlan {
        blockTags = List.copyOf(blockTags);
        itemTags = List.copyOf(itemTags);
    }
}
