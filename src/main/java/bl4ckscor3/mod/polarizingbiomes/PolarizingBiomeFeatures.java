package bl4ckscor3.mod.polarizingbiomes;

import bl4ckscor3.mod.polarizingbiomes.biome.feature.BigLakeFeature;
import bl4ckscor3.mod.polarizingbiomes.foliageplacer.SlimTreeFoliagePlacer;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.Features;
import net.minecraft.data.worldgen.Features.Decorators;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockStateConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.stateproviders.SimpleStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.ChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraft.world.level.levelgen.placement.FrequencyWithExtraChanceDecoratorConfiguration;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid=PolarizingBiomes.MODID, bus=Bus.MOD)
public class PolarizingBiomeFeatures
{
	@ObjectHolder(PolarizingBiomes.MODID + ":big_lake")
	public static final Feature<BlockStateConfiguration> BIG_LAKE = (Feature<BlockStateConfiguration>)new BigLakeFeature(BlockStateConfiguration.CODEC).setRegistryName("big_lake");
	public static final TreeConfiguration SLIM_SPRUCE_TREE_CONFIG = new TreeConfiguration.TreeConfigurationBuilder(
			new SimpleStateProvider(Blocks.SPRUCE_LOG.defaultBlockState()),
			new StraightTrunkPlacer(12, 15, 3),
			new SimpleStateProvider(Blocks.SPRUCE_LEAVES.defaultBlockState()),
			new SimpleStateProvider(Blocks.SPRUCE_SAPLING.defaultBlockState()),
			new SlimTreeFoliagePlacer(UniformInt.of(2, 0), UniformInt.of(0, 0), 1),
			new TwoLayersFeatureSize(2, 0, 2)).ignoreVines().build();
	public static final ConfiguredFeature<?,?> OAK_TREES = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":oak_trees", Features.OAK.decorated(Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(0, 0.1F, 1))));
	public static final ConfiguredFeature<?,?> SPRUCE_TREES_LAKES = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":spruce_trees_lakes", Features.SPRUCE.decorated(Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0.1F, 0))));
	public static final ConfiguredFeature<?,?> SPRUCE_TREES_DENSE = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":spruce_trees_dense", Features.SPRUCE.decorated(Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(1, 0.1F, 1))));
	public static final ConfiguredFeature<?,?> BIG_FROZEN_LAKES = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":big_frozen_lakes", BIG_LAKE.configured(new BlockStateConfiguration(Blocks.ICE.defaultBlockState())).decorated(PolarizingBiomePlacements.LAKE_AT_SURFACE.configured(new ChanceDecoratorConfiguration(1))));
	public static final ConfiguredFeature<?,?> FROZEN_LAKES = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":frozen_lakes", Feature.LAKE.configured(new BlockStateConfiguration(Blocks.ICE.defaultBlockState())).decorated(PolarizingBiomePlacements.LAKE_AT_SURFACE.configured(new ChanceDecoratorConfiguration(1))));
	public static final ConfiguredFeature<?,?> SLIM_SPRUCE_TREES = Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, PolarizingBiomes.MODID + ":slim_spruce_trees", Feature.TREE.configured(SLIM_SPRUCE_TREE_CONFIG).decorated(Decorators.HEIGHTMAP_SQUARE).decorated(FeatureDecorator.COUNT_EXTRA.configured(new FrequencyWithExtraChanceDecoratorConfiguration(20, 0.1F, 1))));

	@SubscribeEvent
	public static void onRegisterFeature(RegistryEvent.Register<Feature<?>> event)
	{
		event.getRegistry().register(BIG_LAKE);
	}
}
