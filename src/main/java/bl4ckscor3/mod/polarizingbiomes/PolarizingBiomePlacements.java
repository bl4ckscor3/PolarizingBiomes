package bl4ckscor3.mod.polarizingbiomes;

import bl4ckscor3.mod.polarizingbiomes.placement.LakeAtSurface;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid=PolarizingBiomes.MODID, bus=Bus.MOD)
@ObjectHolder(PolarizingBiomes.MODID)
public class PolarizingBiomePlacements
{
	public static final Placement<LakeChanceConfig> LAKE_AT_SURFACE = (Placement<LakeChanceConfig>)new LakeAtSurface(LakeChanceConfig::deserialize).setRegistryName("lake_at_surface");

	@SubscribeEvent
	public static void onRegisterFeature(RegistryEvent.Register<Placement<?>> event)
	{
		event.getRegistry().register(LAKE_AT_SURFACE);
	}
}
