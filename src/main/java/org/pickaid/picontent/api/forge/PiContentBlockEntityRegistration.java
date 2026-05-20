package org.pickaid.picontent.api.forge;

import org.pickaid.picontent.api.blockentity.PiBlockEntityPlan;

import java.util.List;
import java.util.Objects;

public record PiContentBlockEntityRegistration(PiBlockEntityPlan plan, String id, List<String> validBlockIds) {
    public PiContentBlockEntityRegistration {
        plan = Objects.requireNonNull(plan, "plan");
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }
        validBlockIds = List.copyOf(validBlockIds);
    }

    public String name() {
        return plan.name();
    }
}
