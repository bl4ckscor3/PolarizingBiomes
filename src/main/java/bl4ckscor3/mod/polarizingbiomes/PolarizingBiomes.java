package bl4ckscor3.mod.polarizingbiomes;

import bl4ckscor3.mod.polarizingbiomes.biome.BigFrozenLakes;
import bl4ckscor3.mod.polarizingbiomes.biome.DenseTaigaForest;
import bl4ckscor3.mod.polarizingbiomes.biome.FrozenForest;
import bl4ckscor3.mod.polarizingbiomes.biome.FrozenLakes;
import bl4ckscor3.mod.polarizingbiomes.biome.WintryMountains;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.BiomeManager.BiomeEntry;
import net.minecraftforge.common.BiomeManager.BiomeType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod(PolarizingBiomes.MODID)
@EventBusSubscriber(modid=PolarizingBiomes.MODID, bus=Bus.MOD)
public class PolarizingBiomes
{
	public static final String MODID = "polarizingbiomes";

	@SubscribeEvent
	public static void onRegisterBiome(RegistryEvent.Register<Biome> event)
	{
		//10 is what the vast majority of vanilla biomes use
		registerBiome(event, new BigFrozenLakes(), "big_frozen_lakes", 10, BiomeType.ICY, Type.COLD, Type.CONIFEROUS, Type.FOREST, Type.HILLS, Type.SNOWY, Type.OVERWORLD);
		registerBiome(event, new DenseTaigaForest(), "dense_taiga_forest", 10, BiomeType.ICY, Type.COLD, Type.DENSE, Type.CONIFEROUS, Type.FOREST, Type.SNOWY, Type.OVERWORLD);
		registerBiome(event, new FrozenForest(), "frozen_forest", 10, BiomeType.ICY, Type.COLD, Type.FOREST, Type.SNOWY, Type.OVERWORLD);
		registerBiome(event, new FrozenLakes(), "frozen_lakes", 10, BiomeType.ICY, Type.COLD, Type.CONIFEROUS, Type.FOREST, Type.HILLS, Type.SNOWY, Type.OVERWORLD);
		registerBiome(event, new WintryMountains(), "wintry_mountains", 10, BiomeType.ICY, Type.COLD, Type.CONIFEROUS, Type.FOREST, Type.MOUNTAIN, Type.SNOWY, Type.OVERWORLD);
	}

	private static void registerBiome(RegistryEvent.Register<Biome> event, Biome biome, String registryName, int spawnWeight, BiomeType spawnType, Type... types)
	{
		event.getRegistry().register(biome.setRegistryName(new ResourceLocation(MODID, registryName)));
		BiomeDictionary.addTypes(biome, types);
		BiomeManager.addBiome(spawnType, new BiomeEntry(biome, spawnWeight));
	}
}
