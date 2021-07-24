package bl4ckscor3.mod.polarizingbiomes;

import bl4ckscor3.mod.polarizingbiomes.placement.LakeAtSurfacePlacement;
import net.minecraft.world.level.levelgen.placement.ChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid=PolarizingBiomes.MODID, bus=Bus.MOD)
@ObjectHolder(PolarizingBiomes.MODID)
public class PolarizingBiomePlacements
{
	public static final FeatureDecorator<ChanceDecoratorConfiguration> LAKE_AT_SURFACE = (FeatureDecorator<ChanceDecoratorConfiguration>)new LakeAtSurfacePlacement(ChanceDecoratorConfiguration.CODEC).setRegistryName("lake_at_surface");

	@SubscribeEvent
	public static void onRegisterFeature(RegistryEvent.Register<FeatureDecorator<?>> event)
	{
		event.getRegistry().register(LAKE_AT_SURFACE);
	}
}
