# PiContent

[中文](README.MD)

PiContent is the Pi stack content authoring toolkit. It owns Item, Block, BlockEntity, datagen, creative, and client presentation requirement metadata. It does not own gameplay runtime, networking, serialization internals, or renderer backends.

## Status

- Forge 1.20.1 module shell is initialized.
- P0 target: item builders, block builders, block entity builders, datagen declaration bundles, and declaration-only render hooks.

## Maven

```gradle
implementation fg.deobf("com.mihono.pickaid:picontent:0.0.1")
```

## Boundaries

- Public API does not hold the `Minecraft` client singleton or renderer backend objects.
- Public API does not expose `FriendlyByteBuf`.
- PiContent declares content and generated asset needs; it does not apply damage, movement, abilities, or world mutation.
