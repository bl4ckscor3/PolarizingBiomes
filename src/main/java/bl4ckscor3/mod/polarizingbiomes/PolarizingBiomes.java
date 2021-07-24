package bl4ckscor3.mod.polarizingbiomes;

import java.util.function.Supplier;

import bl4ckscor3.mod.snowundertrees.SnowUnderTrees;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.Registry;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biome.BiomeCategory;
import net.minecraft.world.level.biome.Biome.Precipitation;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.biome.AmbientMoodSettings;
import net.minecraft.world.level.levelgen.GenerationStep.Decoration;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.data.worldgen.SurfaceBuilders;
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
		ResourceKey<Biome> biomeKey = ResourceKey.create(Registry.BIOME_REGISTRY, biomeLocation);

		BiomeManager.addBiome(BiomeType.ICY, new BiomeEntry(biomeKey, 10));
		BiomeDictionary.addTypes(biomeKey, types);
		return BIOMES.register(name, biome);
	}

	public static Biome makeDenseTaigaForest()
	{
		MobSpawnSettings.Builder spawnInfo = new MobSpawnSettings.Builder();
		BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder()
				.surfaceBuilder(SurfaceBuilders.GRASS)
				.addStructureStart(StructureFeatures.IGLOO)
				.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);

		withDefaultSettings(generationSettings);
		generationSettings.addFeature(Decoration.VEGETAL_DECORATION, PolarizingBiomeFeatures.SPRUCE_TREES_DENSE);
		generationSettings.addFeature(Decoration.VEGETAL_DECORATION, PolarizingBiomeFeatures.SLIM_SPRUCE_TREES);
		BiomeDefaultFeatures.snowySpawns(spawnInfo);
		return new Biome.BiomeBuilder()
				.precipitation(Precipitation.SNOW)
				.biomeCategory(BiomeCategory.ICY)
				.depth(0.2F)
				.scale(0.18F)
				.temperature(-0.5F)
				.downfall(0.5F)
				.specialEffects(new BiomeSpecialEffects.Builder()
						.skyColor(0x7FA1FF)
						.fogColor(0xC0D8FF)
						.waterColor(0x3F76E4)
						.waterFogColor(0x050533)
						.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
						.build())
				.generationSettings(generationSettings.build())
				.mobSpawnSettings(spawnInfo.build())
				.build();
	}

	public static Biome makeFrozenForest()
	{
		MobSpawnSettings.Builder spawnInfo = new MobSpawnSettings.Builder();
		BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder()
				.surfaceBuilder(SurfaceBuilders.GRASS)
				.addStructureStart(StructureFeatures.IGLOO)
				.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD)
				.addStructureStart(StructureFeatures.VILLAGE_SNOWY)
				.addStructureStart(StructureFeatures.PILLAGER_OUTPOST);

		withDefaultSettings(generationSettings);
		BiomeDefaultFeatures.addMountainEdgeTrees(generationSettings);
		BiomeDefaultFeatures.snowySpawns(spawnInfo);
		return new Biome.BiomeBuilder()
				.precipitation(Precipitation.SNOW)
				.biomeCategory(BiomeCategory.ICY)
				.depth(0.2F)
				.scale(0.06F)
				.temperature(-2.0F)
				.downfall(0.5F)
				.specialEffects(new BiomeSpecialEffects.Builder()
						.skyColor(0x7FA1FF)
						.fogColor(0xC0D8FF)
						.waterColor(0x3F76E4)
						.waterFogColor(0x050533)
						.foliageColorOverride(0xFEFEFE)
						.grassColorOverride(0xFEFEFE)
						.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
						.build())
				.generationSettings(generationSettings.build())
				.mobSpawnSettings(spawnInfo.build())
				.build();
	}

	public static Biome makeAbstractLakes(Supplier<ConfiguredFeature<?,?>> lake)
	{
		MobSpawnSettings.Builder spawnInfo = new MobSpawnSettings.Builder();
		BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder()
				.surfaceBuilder(SurfaceBuilders.GRASS)
				.addStructureStart(StructureFeatures.IGLOO)
				.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD)
				.addStructureStart(StructureFeatures.VILLAGE_SNOWY)
				.addStructureStart(StructureFeatures.PILLAGER_OUTPOST);

		withDefaultSettings(generationSettings);
		generationSettings.addFeature(Decoration.LOCAL_MODIFICATIONS, lake.get());
		generationSettings.addFeature(Decoration.LOCAL_MODIFICATIONS, lake.get());
		generationSettings.addFeature(Decoration.VEGETAL_DECORATION, PolarizingBiomeFeatures.OAK_TREES);
		generationSettings.addFeature(Decoration.VEGETAL_DECORATION, PolarizingBiomeFeatures.SPRUCE_TREES_LAKES);
		BiomeDefaultFeatures.snowySpawns(spawnInfo);
		return new Biome.BiomeBuilder()
				.precipitation(Precipitation.SNOW)
				.biomeCategory(BiomeCategory.ICY)
				.depth(0.2F)
				.scale(0.15F)
				.temperature(-2.0F)
				.downfall(0.5F)
				.specialEffects(new BiomeSpecialEffects.Builder()
						.skyColor(0x7FA1FF)
						.fogColor(0xC0D8FF)
						.waterColor(0x3F76E4)
						.waterFogColor(0x050533)
						.foliageColorOverride(0xFEFEFE)
						.grassColorOverride(0xFEFEFE)
						.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
						.build())
				.generationSettings(generationSettings.build())
				.mobSpawnSettings(spawnInfo.build())
				.build();
	}

	public static Biome makeWintryMountains()
	{
		MobSpawnSettings.Builder spawnInfo = new MobSpawnSettings.Builder();
		BiomeGenerationSettings.Builder generationSettings = new BiomeGenerationSettings.Builder()
				.surfaceBuilder(SurfaceBuilders.GRASS)
				.addStructureStart(StructureFeatures.IGLOO)
				.addStructureStart(StructureFeatures.RUINED_PORTAL_MOUNTAIN);

		withDefaultSettings(generationSettings);
		BiomeDefaultFeatures.addSnowyTrees(generationSettings);
		BiomeDefaultFeatures.addTaigaTrees(generationSettings);
		BiomeDefaultFeatures.addExtraEmeralds(generationSettings);
		BiomeDefaultFeatures.addInfestedStone(generationSettings);
		BiomeDefaultFeatures.snowySpawns(spawnInfo);
		return new Biome.BiomeBuilder()
				.precipitation(Precipitation.SNOW)
				.biomeCategory(BiomeCategory.ICY)
				.depth(2.0F)
				.scale(0.6F)
				.temperature(-0.5F)
				.downfall(0.5F)
				.specialEffects(new BiomeSpecialEffects.Builder()
						.skyColor(0x7FA1FF)
						.fogColor(0xC0D8FF)
						.waterColor(0x3F76E4)
						.waterFogColor(0x050533)
						.ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
						.build())
				.generationSettings(generationSettings.build())
				.mobSpawnSettings(spawnInfo.build())
				.build();
	}

	private static final void withDefaultSettings(BiomeGenerationSettings.Builder generationSettings)
	{
		BiomeDefaultFeatures.addDefaultOverworldLandStructures(generationSettings);
		BiomeDefaultFeatures.addDefaultCarvers(generationSettings);
		BiomeDefaultFeatures.addDefaultMonsterRoom(generationSettings);
		BiomeDefaultFeatures.addDefaultUndergroundVariety(generationSettings);
		BiomeDefaultFeatures.addDefaultOres(generationSettings);
		BiomeDefaultFeatures.addDefaultSoftDisks(generationSettings);
		BiomeDefaultFeatures.addDefaultSprings(generationSettings);
		BiomeDefaultFeatures.addDefaultFlowers(generationSettings);
		BiomeDefaultFeatures.addForestGrass(generationSettings);
		BiomeDefaultFeatures.addSurfaceFreezing(generationSettings);
		generationSettings.addFeature(Decoration.TOP_LAYER_MODIFICATION, SnowUnderTrees.SNOW_UNDER_TREES);
	}
}
