package bl4ckscor3.mod.polarizingbiomes.biome;

import bl4ckscor3.mod.polarizingbiomes.PolarizingBiomeFeatures;
import bl4ckscor3.mod.snowundertrees.SnowUnderTrees;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class DenseTaigaForest extends Biome
{
	public DenseTaigaForest()
	{
		super(new Builder()
				.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
				.precipitation(RainType.SNOW)
				.category(Category.ICY)
				.depth(0.2F)
				.scale(0.18F)
				.temperature(-0.5F)
				.downfall(0.5F)
				.func_235097_a_(new BiomeAmbience.Builder() //ambience
						.func_235239_a_(0xC0D8FF) //fogColor
						.func_235246_b_(0x3F76E4) //waterColor
						.func_235248_c_(0x050533) //waterFogColor
						.func_235243_a_(MoodSoundAmbience.field_235027_b_) //moodSound, CAVE (or so)
						.func_235238_a_()) //build
				.parent(null));

		func_235063_a_(DefaultBiomeFeatures.field_235169_g_); //igloo
		func_235063_a_(DefaultBiomeFeatures.field_235187_y_); //ruined portal

		DefaultBiomeFeatures.func_235196_b_(this); //mineshaft and stronghold
		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addMonsterRooms(this);
		DefaultBiomeFeatures.addStoneVariants(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addSedimentDisks(this);
		DefaultBiomeFeatures.addSprings(this);
		DefaultBiomeFeatures.addDefaultFlowers(this);
		DefaultBiomeFeatures.addSparseGrass(this);
		PolarizingBiomeFeatures.addSpruceTrees(this, 1, 0.1F, 1);
		PolarizingBiomeFeatures.addSlimTaigaTrees(this);
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
