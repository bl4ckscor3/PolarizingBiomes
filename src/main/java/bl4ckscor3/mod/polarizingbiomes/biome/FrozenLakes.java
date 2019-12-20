package bl4ckscor3.mod.polarizingbiomes.biome;

import bl4ckscor3.mod.polarizingbiomes.PolarizingBiomePlacements;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.LakesConfig;
import net.minecraft.world.gen.placement.LakeChanceConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class FrozenLakes extends AbstractFrozenLakes
{
	public FrozenLakes()
	{
		super(new Builder()
				.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
				.precipitation(RainType.SNOW)
				.category(Category.ICY)
				.depth(0.2F)
				.scale(0.15F)
				.temperature(-2.0F)
				.downfall(0.5F)
				.waterColor(0x3F76E4)
				.waterFogColor(0x050533)
				.parent(null));
	}

	@Override
	protected void addLakes()
	{
		addFeature(
				GenerationStage.Decoration.LOCAL_MODIFICATIONS,
				Biome.createDecoratedFeature(
						Feature.LAKE,
						new LakesConfig(Blocks.ICE.getDefaultState()),
						PolarizingBiomePlacements.LAKE_AT_SURFACE,
						new LakeChanceConfig(1)));
	}
}
