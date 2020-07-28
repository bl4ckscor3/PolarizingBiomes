package bl4ckscor3.mod.polarizingbiomes.biome;

import bl4ckscor3.mod.snowundertrees.SnowUnderTrees;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class WintryMountains extends Biome
{
	public WintryMountains()
	{
		super(new Builder()
				.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
				.precipitation(RainType.SNOW)
				.category(Category.ICY)
				.depth(2.0F)
				.scale(0.6F)
				.temperature(-0.5F)
				.downfall(0.5F)
				.func_235097_a_(new BiomeAmbience.Builder() //ambience
						.setFogColor(0xC0D8FF)
						.setWaterColor(0x3F76E4)
						.setWaterFogColor(0x050533)
						.setMoodSound(MoodSoundAmbience.field_235027_b_) //CAVE (or so)
						.build())
				.parent(null));

		func_235063_a_(DefaultBiomeFeatures.IGLOO);
		func_235063_a_(DefaultBiomeFeatures.PILLAGER_OUTPOST);
		func_235063_a_(DefaultBiomeFeatures.RUINED_PORTAL);

		DefaultBiomeFeatures.func_235196_b_(this); //mineshaft and stronghold
		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addMonsterRooms(this);
		DefaultBiomeFeatures.addStoneVariants(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addSedimentDisks(this);
		DefaultBiomeFeatures.addScatteredSpruceTrees(this);
		DefaultBiomeFeatures.addTaigaConifers(this);
		DefaultBiomeFeatures.addSprings(this);
		DefaultBiomeFeatures.addExtraEmeraldOre(this);
		DefaultBiomeFeatures.addInfestedStone(this);
		DefaultBiomeFeatures.addDefaultFlowers(this);
		DefaultBiomeFeatures.addSparseGrass(this);
		DefaultBiomeFeatures.addFreezeTopLayer(this);
		SnowUnderTrees.addSnowUnderTrees(this);

		addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.RABBIT, 10, 2, 3));
		addSpawn(EntityClassification.CREATURE, new Biome.SpawnListEntry(EntityType.POLAR_BEAR, 1, 1, 2));
		addSpawn(EntityClassification.AMBIENT, new Biome.SpawnListEntry(EntityType.BAT, 10, 8, 8));
		addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SPIDER, 100, 4, 4));
		addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE, 95, 4, 4));
		addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
		addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.CREEPER, 100, 4, 4));
		addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SLIME, 100, 4, 4));
		addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.ENDERMAN, 10, 1, 4));
		addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.WITCH, 5, 1, 1));
		addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.SKELETON, 20, 4, 4));
		addSpawn(EntityClassification.MONSTER, new Biome.SpawnListEntry(EntityType.STRAY, 80, 4, 4));
	}
}
