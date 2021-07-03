package bl4ckscor3.mod.polarizingbiomes;

import java.util.function.Supplier;

import bl4ckscor3.mod.snowundertrees.SnowUnderTrees;
import net.minecraft.util.RegistryKey;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.Biome.RainType;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.BiomeGenerationSettings;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage.Decoration;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod(PolarizingBiomes.MODID)
public class PolarizingBiomes
{
	public static final String MODID = "polarizingbiomes";
	public static final DeferredRegister<Biome> BIOMES = DeferredRegister.create(ForgeRegistries.BIOMES, MODID);
	public static final RegistryObject<Biome> BIG_FROZEN_LAKES = registerBiome("big_frozen_lakes", () -> PolarizingBiomes.makeAbstractLakes(() -> PolarizingBiomeFeatures.BIG_FROZEN_LAKES), Type.COLD, Type.CONIFEROUS, Type.FOREST, Type.HILLS, Type.SNOWY, Type.OVERWORLD);
	public static final RegistryObject<Biome> DENSE_TAIGA_FOREST = registerBiome("dense_taiga_forest", PolarizingBiomes::makeDenseTaigaForest, Type.COLD, Type.DENSE, Type.CONIFEROUS, Type.FOREST, Type.SNOWY, Type.OVERWORLD);
	public static final RegistryObject<Biome> FROZEN_FOREST = registerBiome("frozen_forest", PolarizingBiomes::makeFrozenForest, Type.COLD, Type.FOREST, Type.SNOWY, Type.OVERWORLD);
	public static final RegistryObject<Biome> FROZEN_LAKES = registerBiome("frozen_lakes", () -> PolarizingBiomes.makeAbstractLakes(() -> PolarizingBiomeFeatures.FROZEN_LAKES), Type.COLD, Type.CONIFEROUS, Type.FOREST, Type.HILLS, Type.SNOWY, Type.OVERWORLD);
	public static final RegistryObject<Biome> WINTRY_MOUNTAINS = registerBiome("wintry_mountains", PolarizingBiomes::makeWintryMountains, Type.COLD, Type.CONIFEROUS, Type.FOREST, Type.MOUNTAIN, Type.SNOWY, Type.OVERWORLD);

	public PolarizingBiomes()
	{
		BIOMES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}

	public static RegistryObject<Biome> registerBiome(String name, Supplier<Biome> biome, Type... types)
	{
		ResourceLocation biomeLocation = new ResourceLocation(MODID, name);
		RegistryKey<Biome> biomeKey = RegistryKey.getOrCreateKey(Registry.BIOME_KEY, biomeLocation);

		BiomeManager.addBiome(BiomeType.ICY, new BiomeEntry(biomeKey, 10));
		BiomeDictionary.addTypes(biomeKey, types);
		return BIOMES.register(name, biome);
	}

	public static Biome makeDenseTaigaForest()
	{
		MobSpawnInfo.Builder spawnInfo = new MobSpawnInfo.Builder();
		BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder()
				.withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
				.withStructure(StructureFeatures.IGLOO)
				.withStructure(StructureFeatures.RUINED_PORTAL);

		withDefaultSettings(generationSettings);
		generationSettings.withFeature(Decoration.VEGETAL_DECORATION, PolarizingBiomeFeatures.SPRUCE_TREES_DENSE);
		generationSettings.withFeature(Decoration.VEGETAL_DECORATION, PolarizingBiomeFeatures.SLIM_SPRUCE_TREES);
		DefaultBiomeFeatures.withSnowyBiomeMobs(spawnInfo);
		return new Biome.Builder()
				.precipitation(RainType.SNOW)
				.category(Category.ICY)
				.depth(0.2F)
				.scale(0.18F)
				.temperature(-0.5F)
				.downfall(0.5F)
				.setEffects(new BiomeAmbience.Builder()
						.withSkyColor(0x7FA1FF)
						.setFogColor(0xC0D8FF)
						.setWaterColor(0x3F76E4)
						.setWaterFogColor(0x050533)
						.setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
						.build())
				.withGenerationSettings(generationSettings.build())
				.withMobSpawnSettings(spawnInfo.build())
				.build();
	}

	public static Biome makeFrozenForest()
	{
		MobSpawnInfo.Builder spawnInfo = new MobSpawnInfo.Builder();
		BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder()
				.withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
				.withStructure(StructureFeatures.IGLOO)
				.withStructure(StructureFeatures.RUINED_PORTAL)
				.withStructure(StructureFeatures.VILLAGE_SNOWY)
				.withStructure(StructureFeatures.PILLAGER_OUTPOST);

		withDefaultSettings(generationSettings);
		DefaultBiomeFeatures.withMountainEdgeTrees(generationSettings);
		DefaultBiomeFeatures.withSnowyBiomeMobs(spawnInfo);
		return new Biome.Builder()
				.precipitation(RainType.SNOW)
				.category(Category.ICY)
				.depth(0.2F)
				.scale(0.06F)
				.temperature(-2.0F)
				.downfall(0.5F)
				.setEffects(new BiomeAmbience.Builder()
						.withSkyColor(0x7FA1FF)
						.setFogColor(0xC0D8FF)
						.setWaterColor(0x3F76E4)
						.setWaterFogColor(0x050533)
						.withFoliageColor(0xFEFEFE)
						.withGrassColor(0xFEFEFE)
						.setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
						.build())
				.withGenerationSettings(generationSettings.build())
				.withMobSpawnSettings(spawnInfo.build())
				.build();
	}

	public static Biome makeAbstractLakes(Supplier<ConfiguredFeature<?,?>> lake)
	{
		MobSpawnInfo.Builder spawnInfo = new MobSpawnInfo.Builder();
		BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder()
				.withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
				.withStructure(StructureFeatures.IGLOO)
				.withStructure(StructureFeatures.RUINED_PORTAL)
				.withStructure(StructureFeatures.VILLAGE_SNOWY)
				.withStructure(StructureFeatures.PILLAGER_OUTPOST);

		withDefaultSettings(generationSettings);
		generationSettings.withFeature(Decoration.LOCAL_MODIFICATIONS, lake.get());
		generationSettings.withFeature(Decoration.LOCAL_MODIFICATIONS, lake.get());
		generationSettings.withFeature(Decoration.VEGETAL_DECORATION, PolarizingBiomeFeatures.OAK_TREES);
		generationSettings.withFeature(Decoration.VEGETAL_DECORATION, PolarizingBiomeFeatures.SPRUCE_TREES_LAKES);
		DefaultBiomeFeatures.withSnowyBiomeMobs(spawnInfo);
		return new Biome.Builder()
				.precipitation(RainType.SNOW)
				.category(Category.ICY)
				.depth(0.2F)
				.scale(0.15F)
				.temperature(-2.0F)
				.downfall(0.5F)
				.setEffects(new BiomeAmbience.Builder()
						.withSkyColor(0x7FA1FF)
						.setFogColor(0xC0D8FF)
						.setWaterColor(0x3F76E4)
						.setWaterFogColor(0x050533)
						.withFoliageColor(0xFEFEFE)
						.withGrassColor(0xFEFEFE)
						.setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
						.build())
				.withGenerationSettings(generationSettings.build())
				.withMobSpawnSettings(spawnInfo.build())
				.build();
	}

	public static Biome makeWintryMountains()
	{
		MobSpawnInfo.Builder spawnInfo = new MobSpawnInfo.Builder();
		BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder()
				.withSurfaceBuilder(ConfiguredSurfaceBuilders.GRASS)
				.withStructure(StructureFeatures.IGLOO)
				.withStructure(StructureFeatures.RUINED_PORTAL_MOUNTAIN);

		withDefaultSettings(generationSettings);
		DefaultBiomeFeatures.withSnowySpruces(generationSettings);
		DefaultBiomeFeatures.withTaigaVegetation(generationSettings);
		DefaultBiomeFeatures.withEmeraldOre(generationSettings);
		DefaultBiomeFeatures.withInfestedStone(generationSettings);
		DefaultBiomeFeatures.withSnowyBiomeMobs(spawnInfo);
		return new Biome.Builder()
				.precipitation(RainType.SNOW)
				.category(Category.ICY)
				.depth(2.0F)
				.scale(0.6F)
				.temperature(-0.5F)
				.downfall(0.5F)
				.setEffects(new BiomeAmbience.Builder()
						.withSkyColor(0x7FA1FF)
						.setFogColor(0xC0D8FF)
						.setWaterColor(0x3F76E4)
						.setWaterFogColor(0x050533)
						.setMoodSound(MoodSoundAmbience.DEFAULT_CAVE)
						.build())
				.withGenerationSettings(generationSettings.build())
				.withMobSpawnSettings(spawnInfo.build())
				.build();
	}

	private static final void withDefaultSettings(BiomeGenerationSettings.Builder generationSettings)
	{
		DefaultBiomeFeatures.withStrongholdAndMineshaft(generationSettings);
		DefaultBiomeFeatures.withCavesAndCanyons(generationSettings);
		DefaultBiomeFeatures.withMonsterRoom(generationSettings);
		DefaultBiomeFeatures.withCommonOverworldBlocks(generationSettings);
		DefaultBiomeFeatures.withOverworldOres(generationSettings);
		DefaultBiomeFeatures.withDisks(generationSettings);
		DefaultBiomeFeatures.withLavaAndWaterSprings(generationSettings);
		DefaultBiomeFeatures.withDefaultFlowers(generationSettings);
		DefaultBiomeFeatures.withForestGrass(generationSettings);
		DefaultBiomeFeatures.withFrozenTopLayer(generationSettings);
		generationSettings.withFeature(Decoration.TOP_LAYER_MODIFICATION, SnowUnderTrees.SNOW_UNDER_TREES);
	}
}
