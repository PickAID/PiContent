package org.pickaid.picontent.api.forge;

import org.pickaid.picontent.api.item.PiItemPlan;

import java.util.Objects;

public record PiContentItemRegistration(PiItemPlan plan, String id) {
    public PiContentItemRegistration {
        plan = Objects.requireNonNull(plan, "plan");
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }
    }

    public String name() {
        return plan.name();
    }

    public org.pickaid.picontent.api.item.PiItemPropertiesPlan properties() {
        return plan.properties();
    }
}
