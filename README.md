# Medieval Weapons: Unofficial NeoForge Port

Unofficial port of **[Medieval Weapons](https://github.com/Globox1997/MedievalWeapons)** by **Globox_Z** (Fabric 1.21.1, version 1.7.0) to **NeoForge 1.21.1**.

Medieval Weapons adds the weapons of the series *Songs of War* (Plasma Studios): small axe, long sword, dagger, francisca, big axe, javelin, lance, healing staff, mace, ninjato, sickle, rapier, two bows and three hero weapons, in wooden, stone, iron, golden, diamond and netherite variants.

## Status: early alpha

The port builds, the client reaches the main menu, and the dedicated-server smoke test creates a world, spawns every weapon entity of the mod and ticks them without errors. Gameplay itself (combat, blocking, arm animations, HUD attack indicator, item rendering in hand) has **not** been verified in game yet. Please report anything that behaves differently from the original (see below).

## Requirements

- Minecraft 1.21.1, NeoForge 21.1.200 or newer
- [Cloth Config](https://modrinth.com/mod/cloth-config) for NeoForge (required, the mod's config screen uses it)

## Getting textures, models and sounds

All Medieval Weapons assets (textures, models, sounds, translations) are **All Rights Reserved** by Globox_Z. They are **not included** in this port.

1. Download the original **Medieval Weapons 1.7.0** for **Fabric 1.21.1** yourself: [CurseForge](https://www.curseforge.com/minecraft/mc-mods/medievalweapons/files) or [Modrinth](https://modrinth.com/mod/medievalweapons/version/1.7.0+1.21.1).
2. Put the downloaded `.jar` into your `resourcepacks` folder. **Not** into `mods`.

On startup the port finds the jar and loads **only its client assets** (textures, models, sounds, translations, including the compat models of the mods listed below) as a built-in resource pack. Nothing is downloaded by the mod itself. Without the jar the game works, but items are purple-black, and a one-time notice on the title screen explains what to do.

## Integrations

Compatibility with other mods is kept where a NeoForge 1.21.1 build of that mod exists:

| Mod | Status |
|---|---|
| Deeper and Darker | items, entity types, recipes, data pack |
| Mythic Metals | items, entity types, recipes, data pack — uses the unofficial NeoForge port of Mythic Metals (CurseForge project 1800546) |
| Mythic Upgrades | items, entity types, recipes, data pack |
| Paradise Lost | items, entity types, recipes, data pack |
| Better Combat | weapon attributes are shipped as data; no code dependency |
| LevelZ | optional: archery bow bonus (`BonusHelper.bowBonus`) for thrown francisca/javelin, linked against the unofficial NeoForge port; without LevelZ the class is never loaded |
| Better End, Better Nether, DragonLoot, Oh The Biomes You'll Go, EarlyStage, Oblivion, Additional Additions | not available on NeoForge 1.21.1 (or, for Additional Additions, the NeoForge build has none of the materials and items this mod's compat pack uses), so their compat modules are kept in `_excluded/` and are not compiled |

## Building

Requires JDK 21. The optional LevelZ integration is compiled against the LevelZ NeoForge port, so publish that port
first (it needs LibZ from `mavenLocal` as well):

```bash
# once, if not done already
(cd ../../levelz-neoforge/libz/port && ./gradlew publishToMavenLocal)
(cd ../../levelz-neoforge/levelz/port && ./gradlew publishToMavenLocal)

./gradlew build
```

LevelZ itself is **not** bundled: it is a `compileOnly` dependency (plus `runtimeOnly` in dev runs, so the integration
can be tested in the IDE). The mod jar is in `build/libs/`.

## Reporting bugs

This is an **unofficial** port. Please **do not** report its bugs to Globox_Z or on the original issue tracker. Use this repository instead: https://github.com/sanufsoiii/medievalweapons-neoforge/issues

## License and credits

- Medieval Weapons was created by **Globox_Z**: https://github.com/Globox1997/MedievalWeapons. All gameplay design, art, sounds and names belong to them; the weapons are based on *Songs of War* by Plasma Studios.
- This port is not affiliated with or endorsed by the original author.
- Code: **MIT**, same as the original code (see `LICENSE`).
- Folder `_excluded/` keeps original Fabric-only code (data generators, ModMenu integration, integrations with mods that have no NeoForge 1.21.1 version) for reference; it is not compiled.
