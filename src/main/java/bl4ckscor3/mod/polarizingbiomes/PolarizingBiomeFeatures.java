package bl4ckscor3.mod.polarizingbiomes;

import bl4ckscor3.mod.polarizingbiomes.biome.feature.BigLakeFeature;
import bl4ckscor3.mod.polarizingbiomes.foliageplacer.SlimTreeFoliagePlacer;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.Features.Placements;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid=PolarizingBiomes.MODID, bus=Bus.MOD)
public class PolarizingBiomeFeatures
{
	@ObjectHolder(PolarizingBiomes.MODID + ":big_lake")
	public static final Feature<BlockStateFeatureConfig> BIG_LAKE = (Feature<BlockStateFeatureConfig>)new BigLakeFeature(BlockStateFeatureConfig.CODEC).setRegistryName("big_lake");
	public static final BaseTreeFeatureConfig SLIM_SPRUCE_TREE_CONFIG = new BaseTreeFeatureConfig.Builder(
			new SimpleBlockStateProvider(Blocks.SPRUCE_LOG.getDefaultState()),
			new SimpleBlockStateProvider(Blocks.SPRUCE_LEAVES.getDefaultState()),
			new SlimTreeFoliagePlacer(FeatureSpread.create(2, 0), FeatureSpread.create(0, 0), 1),
			new StraightTrunkPlacer(12, 15, 3),
			new TwoLayerFeature(2, 0, 2)).setIgnoreVines().build();
	public static final ConfiguredFeature<?,?> OAK_TREES = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":oak_trees", Features.OAK.withPlacement(Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
	public static final ConfiguredFeature<?,?> SPRUCE_TREES_LAKES = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":spruce_trees_lakes", Features.SPRUCE.withPlacement(Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.1F, 0))));
	public static final ConfiguredFeature<?,?> SPRUCE_TREES_DENSE = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":spruce_trees_dense", Features.SPRUCE.withPlacement(Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));
	public static final ConfiguredFeature<?,?> BIG_FROZEN_LAKES = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":big_frozen_lakes", BIG_LAKE.withConfiguration(new BlockStateFeatureConfig(Blocks.ICE.getDefaultState())).withPlacement(PolarizingBiomePlacements.LAKE_AT_SURFACE.configure(new ChanceConfig(1))));
	public static final ConfiguredFeature<?,?> FROZEN_LAKES = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":frozen_lakes", Feature.LAKE.withConfiguration(new BlockStateFeatureConfig(Blocks.ICE.getDefaultState())).withPlacement(PolarizingBiomePlacements.LAKE_AT_SURFACE.configure(new ChanceConfig(1))));
	public static final ConfiguredFeature<?,?> SLIM_SPRUCE_TREES = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":slim_spruce_trees", Feature.TREE.withConfiguration(SLIM_SPRUCE_TREE_CONFIG).withPlacement(Placements.HEIGHTMAP_PLACEMENT).withPlacement(Placement.COUNT_EXTRA.configure(new AtSurfaceWithExtraConfig(20, 0.1F, 1))));

	@SubscribeEvent
	public static void onRegisterFeature(RegistryEvent.Register<Feature<?>> event)
	{
		event.getRegistry().register(BIG_LAKE);
	}
}
