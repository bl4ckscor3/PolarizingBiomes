package bl4ckscor3.mod.polarizingbiomes;

import bl4ckscor3.mod.polarizingbiomes.foliageplacer.SlimTreeFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.ObjectHolder;

@EventBusSubscriber(modid=PolarizingBiomes.MODID, bus=Bus.MOD)
@ObjectHolder(PolarizingBiomes.MODID)
public class PolarizingBiomeFoliagePlacerTypes
{
	public static final FoliagePlacerType<SlimTreeFoliagePlacer> SLIM_TREE = null;

	@SubscribeEvent
	public static void onRegisterFoliagePlacerType(RegistryEvent.Register<FoliagePlacerType<?>> event)
	{
		event.getRegistry().register(new FoliagePlacerType<>(SlimTreeFoliagePlacer.CODEC).setRegistryName("slim_tree"));
	}
}
