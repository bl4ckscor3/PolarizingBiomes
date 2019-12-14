package bl4ckscor3.mod.polarizingbiomes;

import bl4ckscor3.mod.polarizingbiomes.biome.feature.BigLakeFeature;
import bl4ckscor3.mod.polarizingbiomes.biome.feature.SlimTaigaTreeFeature;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid=PolarizingBiomes.MODID, bus=Bus.MOD)
@ObjectHolder(PolarizingBiomes.MODID)
public class PolarizingBiomeFeatures
{
	public static final Feature<LakesConfig> BIG_LAKE = (Feature<LakesConfig>)new BigLakeFeature(LakesConfig::deserialize).setRegistryName("big_lake");
	public static final Feature<NoFeatureConfig> SLIM_TAIGA_TREE = (Feature<NoFeatureConfig>)new SlimTaigaTreeFeature(NoFeatureConfig::deserialize).setRegistryName("slim_taiga_tree");

	@SubscribeEvent
	public static void onRegisterFeature(RegistryEvent.Register<Feature<?>> event)
	{
		event.getRegistry().register(BIG_LAKE);
		event.getRegistry().register(SLIM_TAIGA_TREE);
	}

	public static void addSlimTaigaTrees(Biome biome)
	{
		biome.addFeature(
				GenerationStage.Decoration.VEGETAL_DECORATION,
				Biome.createDecoratedFeature(
						SLIM_TAIGA_TREE,
						IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP,
						new AtSurfaceWithExtraConfig(20, 0.1F, 1)));
	}
}
