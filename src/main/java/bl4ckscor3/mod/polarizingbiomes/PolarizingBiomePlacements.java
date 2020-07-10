package bl4ckscor3.mod.polarizingbiomes;

import bl4ckscor3.mod.polarizingbiomes.placement.LakeAtSurfacePlacement;
import net.minecraft.world.gen.placement.ChanceConfig;
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
	public static final Placement<ChanceConfig> LAKE_AT_SURFACE = (Placement<ChanceConfig>)new LakeAtSurfacePlacement(ChanceConfig.field_236950_a_).setRegistryName("lake_at_surface");

	@SubscribeEvent
	public static void onRegisterFeature(RegistryEvent.Register<Placement<?>> event)
	{
		event.getRegistry().register(LAKE_AT_SURFACE);
	}
}
