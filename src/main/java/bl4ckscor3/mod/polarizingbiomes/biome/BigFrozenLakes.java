package bl4ckscor3.mod.polarizingbiomes.biome;

import bl4ckscor3.mod.polarizingbiomes.PolarizingBiomeFeatures;
import bl4ckscor3.mod.polarizingbiomes.PolarizingBiomePlacements;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.BiomeAmbience;
import net.minecraft.world.biome.MoodSoundAmbience;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.BlockStateFeatureConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class BigFrozenLakes extends AbstractFrozenLakes
{
	public BigFrozenLakes()
	{
		super(new Builder()
				.surfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.GRASS_DIRT_GRAVEL_CONFIG)
				.precipitation(RainType.SNOW)
				.category(Category.ICY)
				.depth(0.2F)
				.scale(0.15F)
				.temperature(-2.0F)
				.downfall(0.5F)
				.func_235097_a_(new BiomeAmbience.Builder() //ambience
						.func_235239_a_(0xC0D8FF) //fogColor
						.func_235246_b_(0x3F76E4) //waterColor
						.func_235248_c_(0x050533) //waterFogColor
						.func_235243_a_(MoodSoundAmbience.field_235027_b_) //moodSound, CAVE (or so)
						.func_235238_a_()) //build
				.parent(null));
	}

	@Override
	protected void addLakes()
	{
		addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS,
				PolarizingBiomeFeatures.BIG_LAKE.withConfiguration(new BlockStateFeatureConfig(Blocks.ICE.getDefaultState()))
				.withPlacement(PolarizingBiomePlacements.LAKE_AT_SURFACE.configure(new ChanceConfig(1))));
	}
}
