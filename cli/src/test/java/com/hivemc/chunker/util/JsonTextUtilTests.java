package com.hivemc.chunker.util;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.hivemc.chunker.nbt.tags.collection.CompoundTag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests to ensure JsonTextUtil behaves as expected.
 */
public class JsonTextUtilTests {
    @Test
    public void testShowEntityHoverEventToNBT() {
        JsonObject hoverEvent = new JsonObject();
        hoverEvent.addProperty("action", "show_entity");

        JsonObject contents = new JsonObject();
        contents.addProperty("type", "minecraft:armor_stand");
        contents.addProperty("name", "Chunker");

        JsonArray uuid = new JsonArray(4);
        uuid.add(1);
        uuid.add(2);
        uuid.add(3);
        uuid.add(4);
        contents.add("id", uuid);

        hoverEvent.add("contents", contents);

        JsonObject input = new JsonObject();
        input.add("hoverEvent", hoverEvent);

        CompoundTag output = (CompoundTag) JsonTextUtil.toNBT(input);
        CompoundTag hoverEventTag = output.getCompound("hover_event");

        assertEquals("show_entity", hoverEventTag.getString("action"));
        assertEquals("minecraft:armor_stand", hoverEventTag.getString("id"));
        assertEquals("Chunker", hoverEventTag.getString("name"));
        assertArrayEquals(new int[]{1, 2, 3, 4}, hoverEventTag.getIntArray("uuid"));
    }
}
