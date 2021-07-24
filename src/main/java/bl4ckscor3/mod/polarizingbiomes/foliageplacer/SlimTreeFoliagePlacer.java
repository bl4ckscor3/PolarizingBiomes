package bl4ckscor3.mod.polarizingbiomes.foliageplacer;

import java.util.Random;
import java.util.function.BiConsumer;

import com.mojang.datafixers.Products.P3;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import com.mojang.serialization.codecs.RecordCodecBuilder.Instance;
import com.mojang.serialization.codecs.RecordCodecBuilder.Mu;

import bl4ckscor3.mod.polarizingbiomes.PolarizingBiomeFoliagePlacerTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class SlimTreeFoliagePlacer extends FoliagePlacer
{
	public static final Codec<SlimTreeFoliagePlacer> CODEC = RecordCodecBuilder.create(instance -> blobParts(instance).apply(instance, SlimTreeFoliagePlacer::new));
	protected final int height;

	public SlimTreeFoliagePlacer(IntProvider fs1, IntProvider fs2, int height)
	{
		super(fs1, fs2);

		this.height = height;
	}

	protected static <P extends SlimTreeFoliagePlacer> P3<Mu<P>,IntProvider,IntProvider,Integer> blobParts(Instance<P> instance)
	{
		return foliagePlacerParts(instance).and(Codec.INT.fieldOf("height").forGetter(o -> o.height));
	}

	@Override
	protected FoliagePlacerType<?> type()
	{
		return PolarizingBiomeFoliagePlacerTypes.SLIM_TREE;
	}

	@Override
	protected void createFoliage(LevelSimulatedReader world, BiConsumer<BlockPos, BlockState> placer, Random random, TreeConfiguration featureConfig, int p_161364_, FoliageAttachment foliageAttachment, int p_161366_, int p_161367_, int p_161368_)
	{
		BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos();

		pos.setWithOffset(foliageAttachment.pos(), 0, p_161367_ - 3, 0);
		pos.move(Direction.UP);
		tryPlaceLeaf(world, placer, random, featureConfig, pos);
		pos.move(Direction.DOWN).move(Direction.NORTH);
		tryPlaceLeaf(world, placer, random, featureConfig, pos);
		pos.move(Direction.DOWN);
		tryPlaceLeaf(world, placer, random, featureConfig, pos);
		pos.move(Direction.UP).move(Direction.SOUTH).move(Direction.EAST);
		tryPlaceLeaf(world, placer, random, featureConfig, pos);
		pos.move(Direction.DOWN);
		tryPlaceLeaf(world, placer, random, featureConfig, pos);
		pos.move(Direction.UP).move(Direction.WEST).move(Direction.SOUTH);
		tryPlaceLeaf(world, placer, random, featureConfig, pos);
		pos.move(Direction.DOWN);
		tryPlaceLeaf(world, placer, random, featureConfig, pos);
		pos.move(Direction.UP).move(Direction.NORTH).move(Direction.WEST);
		tryPlaceLeaf(world, placer, random, featureConfig, pos);
		pos.move(Direction.DOWN);
		tryPlaceLeaf(world, placer, random, featureConfig, pos);
	}

	@Override
	public int foliageHeight(Random random, int p_68424_, TreeConfiguration treeConfiguration)
	{
		return height;
	}

	@Override
	protected boolean shouldSkipLocation(Random p_68416_, int p_68417_, int p_68418_, int p_68419_, int p_68420_, boolean p_68421_)
	{
		return true;
	}
}
