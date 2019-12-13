//1
package bl4ckscor3.mod.polarizingbiomes.biome;

import bl4ckscor3.mod.snowundertrees.SnowUnderTrees;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeFeatures;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.feature.structure.MineshaftConfig;
import net.minecraft.world.gen.feature.structure.MineshaftStructure;
import net.minecraft.world.gen.feature.structure.PillagerOutpostConfig;
import net.minecraft.world.gen.feature.structure.VillageConfig;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class FrozenLakes extends Biome
{
	public FrozenLakes()
	{
		super(new Builder()
				.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
				.precipitation(RainType.SNOW)
				.category(Category.ICY)
				.depth(1.0F)
				.scale(0.15F)
				.temperature(-2.0F)
				.downfall(0.5F)
				.waterColor(0x3F76E4)
				.waterFogColor(0x050533)
				.parent(null));

		addStructure(Feature.VILLAGE, new VillageConfig("village/snowy/town_centers", 6));
		addStructure(Feature.IGLOO, IFeatureConfig.NO_FEATURE_CONFIG);
		addStructure(Feature.MINESHAFT, new MineshaftConfig(0.004D, MineshaftStructure.Type.NORMAL));
		addStructure(Feature.STRONGHOLD, IFeatureConfig.NO_FEATURE_CONFIG);
		addStructure(Feature.PILLAGER_OUTPOST, new PillagerOutpostConfig(0.004D));

		DefaultBiomeFeatures.addCarvers(this);
		DefaultBiomeFeatures.addStructures(this);
		DefaultBiomeFeatures.addMonsterRooms(this);
		DefaultBiomeFeatures.addStoneVariants(this);
		DefaultBiomeFeatures.addOres(this);
		DefaultBiomeFeatures.addSedimentDisks(this);
		addFeature(
				GenerationStage.Decoration.LOCAL_MODIFICATIONS,
				Biome.createDecoratedFeature(
						Feature.LAKE,
						new LakesConfig(Blocks.ICE.getDefaultState()),
						Placement.WATER_LAKE,
						new LakeChanceConfig(1)));
		addFeature(
				GenerationStage.Decoration.LOCAL_MODIFICATIONS,
				Biome.createDecoratedFeature(
						Feature.LAKE,
						new LakesConfig(Blocks.ICE.getDefaultState()),
						Placement.WATER_LAKE,
						new LakeChanceConfig(1)));
		addFeature(
				GenerationStage.Decoration.VEGETAL_DECORATION,
				Biome.createDecoratedFeature(
						Feature.SPRUCE_TREE,
						IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP,
						new AtSurfaceWithExtraConfig(1, 0.1F, 0)));
		addFeature(
				GenerationStage.Decoration.VEGETAL_DECORATION,
				Biome.createDecoratedFeature(
						Feature.NORMAL_TREE,
						IFeatureConfig.NO_FEATURE_CONFIG,
						Placement.COUNT_EXTRA_HEIGHTMAP,
						new AtSurfaceWithExtraConfig(0, 0.1F, 1)));
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
	public int getGrassColor(BlockPos pos)
	{
		return 0xFEFEFE;
	}

	@Override
	public int getFoliageColor(BlockPos pos)
	{
		return 0xFEFEFE;
	}
}
