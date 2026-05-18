package org.pickaid.picontent.api.datagen;

public record PiCreativeSampleDeclaration(String section, String itemName) {
    public PiCreativeSampleDeclaration {
        if (section == null || section.isBlank()) {
            throw new IllegalArgumentException("section must not be blank");
        }
        if (itemName == null || itemName.isBlank()) {
            throw new IllegalArgumentException("itemName must not be blank");
        }
    }
}
