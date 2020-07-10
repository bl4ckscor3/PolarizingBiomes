package bl4ckscor3.mod.polarizingbiomes.biome;

import bl4ckscor3.mod.polarizingbiomes.PolarizingBiomeFeatures;
import bl4ckscor3.mod.snowundertrees.SnowUnderTrees;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

public abstract class AbstractFrozenLakes extends Biome
{
	public AbstractFrozenLakes(Biome.Builder biomeBuilder)
	{
		super(biomeBuilder);

		func_235063_a_(DefaultBiomeFeatures.field_235185_w_); //village
		func_235063_a_(DefaultBiomeFeatures.field_235169_g_); //igloo
		func_235063_a_(DefaultBiomeFeatures.field_235134_a_); //pillager outpost
		func_235063_a_(DefaultBiomeFeatures.field_235187_y_); //ruined portal

		DefaultBiomeFeatures.func_235196_b_(this); //mineshaft and stronghold
		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addMonsterRooms(this);
		DefaultBiomeFeatures.addStoneVariants(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addSedimentDisks(this);
		addLakes();
		addLakes();
		PolarizingBiomeFeatures.addSpruceTrees(this, 1, 0.1F, 0);
		//oak trees
		addFeature(GenerationStage.Decoration.VEGETAL_DECORATION,
				Feature.field_236291_c_.withConfiguration(DefaultBiomeFeatures.OAK_TREE_CONFIG)
				.withPlacement(Placement.COUNT_EXTRA_HEIGHTMAP.configure(new AtSurfaceWithExtraConfig(0, 0.1F, 1))));
		DefaultBiomeFeatures.addSprings(this);
		DefaultBiomeFeatures.addDefaultFlowers(this);
		DefaultBiomeFeatures.addGrass(this);
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

	@Override
	public int getGrassColor(double d1, double d2)
	{
		return 0xFEFEFE;
	}

	@Override
	public int getFoliageColor()
	{
		return 0xFEFEFE;
	}

	protected abstract void addLakes();
}
