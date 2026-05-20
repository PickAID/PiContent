package org.pickaid.picontent.api.forge;

import net.minecraftforge.registries.RegistryObject;

import java.util.Optional;
import java.util.function.Supplier;

public final class PiForgeRegistryEntry<T> implements Supplier<T> {
    private final String name;
    private final String id;
    private RegistryObject<? extends T> registryObject;

    PiForgeRegistryEntry(String name, String id) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("name must not be blank");
        }
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("id must not be blank");
        }
        this.name = name;
        this.id = id;
    }

    public String name() {
        return name;
    }

    public String id() {
        return id;
    }

    public Optional<RegistryObject<? extends T>> registryObject() {
        return Optional.ofNullable(registryObject);
    }

    public RegistryObject<? extends T> forgeEntry() {
        if (registryObject == null) {
            throw new IllegalStateException("Forge registry object is not bound yet: " + id);
        }
        return registryObject;
    }

    @Override
    public T get() {
        return forgeEntry().get();
    }

    void bind(RegistryObject<? extends T> registryObject) {
        if (this.registryObject != null) {
            throw new IllegalStateException("Forge registry object is already bound: " + id);
        }
        this.registryObject = registryObject;
    }
}
