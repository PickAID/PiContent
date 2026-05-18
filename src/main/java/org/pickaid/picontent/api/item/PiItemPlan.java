package org.pickaid.picontent.api.item;

import java.util.List;

public record PiItemPlan(
        String name,
        String section,
        PiItemPropertiesPlan properties,
        List<String> itemTags,
        String lang,
        PiItemModelDeclaration model,
        List<PiItemPropertyDeclaration> propertyDeclarations,
        List<String> clientDeclarations
) {
    public PiItemPlan {
        itemTags = List.copyOf(itemTags);
        propertyDeclarations = List.copyOf(propertyDeclarations);
        clientDeclarations = List.copyOf(clientDeclarations);
    }
}
