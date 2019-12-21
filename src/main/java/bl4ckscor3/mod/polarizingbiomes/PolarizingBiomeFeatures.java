package bl4ckscor3.mod.polarizingbiomes;

import bl4ckscor3.mod.polarizingbiomes.biome.feature.BigLakeFeature;
import bl4ckscor3.mod.polarizingbiomes.biome.feature.SlimTreeFeature;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid=PolarizingBiomes.MODID, bus=Bus.MOD)
@ObjectHolder(PolarizingBiomes.MODID)
public class PolarizingBiomeFeatures
{
	public static final Feature<BlockStateFeatureConfig> BIG_LAKE = (Feature<BlockStateFeatureConfig>)new BigLakeFeature(BlockStateFeatureConfig::func_227271_a_).setRegistryName("big_lake"); //deserialize
	public static final Feature<TreeFeatureConfig> SLIM_TREE = (Feature<TreeFeatureConfig>)new SlimTreeFeature(TreeFeatureConfig::func_227338_a_).setRegistryName("slim_tree");

	@SubscribeEvent
	public static void onRegisterFeature(RegistryEvent.Register<Feature<?>> event)
	{
		event.getRegistry().register(BIG_LAKE);
		event.getRegistry().register(SLIM_TREE);
	}

	public static void addSlimTaigaTrees(Biome biome)
	{
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				SLIM_TREE.func_225566_b_(
						new TreeFeatureConfig.Builder(
								new SimpleBlockStateProvider(Blocks.SPRUCE_LOG.getDefaultState()),
								new SimpleBlockStateProvider(Blocks.SPRUCE_LEAVES.getDefaultState()),
								new BlobFoliagePlacer(0, 0))
						.setSapling((IPlantable)Blocks.SPRUCE_SAPLING)
						.func_225568_b_())
				.func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP.func_227446_a_(new AtSurfaceWithExtraConfig(20, 0.1F, 1))));
	}

	public static void addSpruceTrees(Biome biome, int count, float extraChance, int extraCount)
	{
		biome.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.NORMAL_TREE.func_225566_b_(DefaultBiomeFeatures.field_226810_e_)
				.func_227228_a_(Placement.COUNT_EXTRA_HEIGHTMAP.func_227446_a_(new AtSurfaceWithExtraConfig(count, extraChance, extraCount))));
	}
}
