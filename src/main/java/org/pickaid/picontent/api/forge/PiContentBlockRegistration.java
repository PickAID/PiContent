package org.pickaid.picontent.api.forge;

import org.pickaid.picontent.api.block.PiBlockPlan;

import java.util.Objects;

public record PiContentBlockRegistration(PiBlockPlan plan, String id, boolean blockEntityHost) {
    public PiContentBlockRegistration {
        plan = Objects.requireNonNull(plan, "plan");
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }
    }

    public String name() {
        return plan.name();
    }

    public org.pickaid.picontent.api.block.PiBlockPropertiesPlan properties() {
        return plan.properties();
    }
}
