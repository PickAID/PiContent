package org.pickaid.picontent.api.item;

import org.junit.jupiter.api.Test;
import org.pickaid.picontent.api.clientdecl.PiContentAvatarUse;
import org.pickaid.picontent.api.clientdecl.PiContentRig;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

final class PiItemBuilderTest {
    @Test
    void itemPlanCarriesLangModelTagAndClientDeclarations() {
        PiItemPlan plan = PiItemBuilder.named("fire_staff")
                .section("spells")
                .properties(PiItemPresets.staff())
                .itemTag("test:staves")
                .lang("Fire Staff")
                .model(PiItemModels.handheld("item/fire_staff"))
                .property("casting", PiItemProperties.using())
                .rig(PiContentRig.geoItem("fire_staff"))
                .avatarUse(PiContentAvatarUse.castPose("fireball"))
                .plan();

        assertEquals("fire_staff", plan.name());
        assertTrue(plan.clientDeclarations().contains("rig:fire_staff"));
    }
}
