package bl4ckscor3.mod.polarizingbiomes;

import bl4ckscor3.mod.polarizingbiomes.biome.feature.BigLakeFeature;
import bl4ckscor3.mod.polarizingbiomes.foliageplacer.SlimTreeFoliagePlacer;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureSpread;
import net.minecraft.world.gen.feature.TwoLayerFeature;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
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
	public static final BaseTreeFeatureConfig SLIM_TREE_CONFIG = new BaseTreeFeatureConfig.Builder(
			new SimpleBlockStateProvider(Blocks.SPRUCE_LOG.getDefaultState()),
			new SimpleBlockStateProvider(Blocks.SPRUCE_LEAVES.getDefaultState()),
			new SlimTreeFoliagePlacer(FeatureSpread.create(2, 0), FeatureSpread.create(0, 0), 1),
			new StraightTrunkPlacer(12, 15, 3),
			new TwoLayerFeature(2, 0, 2)).setIgnoreVines().build();

	@SubscribeEvent
	public static void onRegisterFeature(RegistryEvent.Register<Feature<?>> event)
	{
		event.getRegistry().register(BIG_LAKE);
	}

	public static void addSlimTaigaTrees(Biome biome)
	{
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.TREE.withConfiguration(SLIM_TREE_CONFIG)
				.withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(20, 0.1F, 1))));
	}

	public static void addSpruceTrees(Biome biome, int count, float extraChance, int extraCount)
	{
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.TREE.withConfiguration(DefaultBiomeFeatures.SPRUCE_TREE_CONFIG)
				.withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(count, extraChance, extraCount))));
	}
}
