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
			new SimpleBlockStateProvider(Blocks.SPRUCE_LOG.defaultBlockState()),
			new SimpleBlockStateProvider(Blocks.SPRUCE_LEAVES.defaultBlockState()),
			new SlimTreeFoliagePlacer(FeatureSpread.of(2, 0), FeatureSpread.of(0, 0), 1),
			new StraightTrunkPlacer(12, 15, 3),
			new TwoLayerFeature(2, 0, 2)).ignoreVines().build();
	public static final ConfiguredFeature<?,?> OAK_TREES = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":oak_trees", Features.OAK.decorated(Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
	public static final ConfiguredFeature<?,?> SPRUCE_TREES_LAKES = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":spruce_trees_lakes", Features.SPRUCE.decorated(Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 0))));
	public static final ConfiguredFeature<?,?> SPRUCE_TREES_DENSE = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":spruce_trees_dense", Features.SPRUCE.decorated(Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));
	public static final ConfiguredFeature<?,?> BIG_FROZEN_LAKES = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":big_frozen_lakes", BIG_LAKE.configured(new BlockStateFeatureConfig(Blocks.ICE.defaultBlockState())).decorated(PolarizingBiomePlacements.LAKE_AT_SURFACE.configured(new ChanceConfig(1))));
	public static final ConfiguredFeature<?,?> FROZEN_LAKES = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":frozen_lakes", Feature.LAKE.configured(new BlockStateFeatureConfig(Blocks.ICE.defaultBlockState())).decorated(PolarizingBiomePlacements.LAKE_AT_SURFACE.configured(new ChanceConfig(1))));
	public static final ConfiguredFeature<?,?> SLIM_SPRUCE_TREES = Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":slim_spruce_trees", Feature.TREE.configured(SLIM_SPRUCE_TREE_CONFIG).decorated(Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(20, 0.1F, 1))));

	@SubscribeEvent
	public static void onRegisterFeature(RegistryEvent.Register<Feature<?>> event)
	{
		event.getRegistry().register(BIG_LAKE);
	}
}
