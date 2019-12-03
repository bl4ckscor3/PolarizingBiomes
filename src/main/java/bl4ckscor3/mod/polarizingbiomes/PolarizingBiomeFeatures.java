package bl4ckscor3.mod.polarizingbiomes;

import bl4ckscor3.mod.polarizingbiomes.biome.feature.SlimTaigaTreeFeature;
import bl4ckscor3.mod.polarizingbiomes.biome.feature.SnowOnGroundFeature;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
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
	public static final Feature<NoFeatureConfig> SLIM_TAIGA_TREE = (Feature<NoFeatureConfig>)new SlimTaigaTreeFeature(NoFeatureConfig::deserialize).setRegistryName("slim_taiga_tree");
	public static final Feature<NoFeatureConfig> SNOW_ON_GROUND = (Feature<NoFeatureConfig>)new SnowOnGroundFeature(NoFeatureConfig::deserialize).setRegistryName("snow_on_ground");

	@SubscribeEvent
	public static void onRegisterFeature(RegistryEvent.Register<Feature<?>> event)
	{
		event.getRegistry().register(SLIM_TAIGA_TREE);
		event.getRegistry().register(SNOW_ON_GROUND);
	}

	public static void addSnowOnGround(Biome biome)
	{
		biome.addFeature(
				GenerationStage.Decoration.TOP_LAYER_MODIFICATION,
				Biome.createDecoratedFeature(
						SNOW_ON_GROUND,
						IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.NOPE,
						IPlacementConfig.NO_PLACEMENT_CONFIG));
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
