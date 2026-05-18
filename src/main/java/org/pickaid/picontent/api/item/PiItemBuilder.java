package org.pickaid.picontent.api.item;

import org.pickaid.picontent.api.clientdecl.PiClientDeclaration;

import java.util.ArrayList;
import java.util.List;

public final class PiItemBuilder {
    private final String name;
    private String section = "";
    private PiItemPropertiesPlan properties = PiItemPropertiesPlan.material();
    private String lang = "";
    private PiItemModelDeclaration model = PiItemModels.generated("");
    private final List<String> itemTags = new ArrayList<>();
    private final List<PiItemPropertyDeclaration> propertyDeclarations = new ArrayList<>();
    private final List<String> clientDeclarations = new ArrayList<>();

    private PiItemBuilder(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        this.name = name;
    }

    public static PiItemBuilder named(String name) {
        return new PiItemBuilder(name);
    }

    public PiItemBuilder section(String section) {
        this.section = section == null ? "" : section;
        return this;
    }

    public PiItemBuilder properties(PiItemPropertiesPlan properties) {
        this.properties = properties;
        return this;
    }

    public PiItemBuilder itemTag(String itemTag) {
        this.itemTags.add(itemTag);
        return this;
    }

    public PiItemBuilder lang(String lang) {
        this.lang = lang == null ? "" : lang;
        return this;
    }

    public PiItemBuilder model(PiItemModelDeclaration model) {
        this.model = model;
        return this;
    }

    public PiItemBuilder property(String name, PiItemPropertyDeclaration property) {
        this.propertyDeclarations.add(property.named(name));
        return this;
    }

    public PiItemBuilder rig(PiClientDeclaration declaration) {
        this.clientDeclarations.add(declaration.key());
        return this;
    }

    public PiItemBuilder avatarUse(PiClientDeclaration declaration) {
        this.clientDeclarations.add(declaration.key());
        return this;
    }

    public PiItemPlan plan() {
        return new PiItemPlan(
                name,
                section,
                properties,
                itemTags,
                lang,
                model,
                propertyDeclarations,
                clientDeclarations
        );
    }
}
