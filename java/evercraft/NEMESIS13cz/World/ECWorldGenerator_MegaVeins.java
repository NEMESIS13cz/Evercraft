package evercraft.NEMESIS13cz.World;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;
import evercraft.NEMESIS13cz.Config.Evercraft_Config_WorldMegaVeins;
import evercraft.NEMESIS13cz.Main.Evercraft_Blocks;
import evercraft.NEMESIS13cz.misc.ECWorldGenMineableNetherMeta;
import evercraft.NEMESIS13cz.misc.materialMinables.WorldGenMinable_Emeralds;
import evercraft.NEMESIS13cz.misc.materialMinables.WorldGenMinable_inDIRT;
import evercraft.NEMESIS13cz.misc.materialMinables.WorldGenMinable_inSAND;

public class ECWorldGenerator_MegaVeins implements IWorldGenerator {
	
	
@Override
public void generate(Random random, int chunkX, int chunkZ, World world,IChunkProvider chunkGenerator, IChunkProvider chunkProvider) {
	switch (world.provider.dimensionId) {
		case 1:
			generateEnd(random,chunkX*16,chunkZ*16,world);
			break;
		case -1:
			generateNether(random,chunkX*16,chunkZ*16,world);
			break;
		case 0:
			generateSurface(random,chunkX*16,chunkZ*16,world);
			break;
		}
	}
//OVERWORLD
	private void generateSurface(Random random, int x, int z, World w) {
		if (Evercraft_Config_WorldMegaVeins.generateMegaVeins) {
			this.addOreSpawn(Blocks.coal_ore, w, random, x, z, 16, 16, 200, 1, 10, 60);
			this.addOreSpawn(Blocks.iron_ore, w, random, x, z, 16, 16, 150, 1, 10, 60);
			this.addOreSpawn(Blocks.gold_ore, w, random, x, z, 16, 16, 80, 1, 5, 30);
			this.addOreSpawn(Blocks.lapis_ore, w, random, x, z, 16, 16, 120, 1, 5, 40);
			this.addOreSpawn(Blocks.redstone_ore, w, random, x, z, 16, 16, 240, 1, 0, 20);
			this.addOreSpawn(Blocks.diamond_ore, w, random, x, z, 16, 16, 60, 1, 0, 20);
			
			if (Evercraft_Config_WorldMegaVeins.generateEmeraldInExtremeHillsOnly) {
				this.addOreSpawn_Emerald(Blocks.emerald_ore, w, random, x, z, 16, 16, 60, 1, 0, 40);
			}else{
				this.addOreSpawn(Blocks.emerald_ore, w, random, x, z, 16, 16, 60, 1, 0, 40);
			}

			this.addOreSpawn(Evercraft_Blocks.blockoverworldquartzore, w, random, x, z, 16, 16, 40, 1, 10, 40);
			this.addDirtSpawn(Evercraft_Blocks.blockclaydirt, w, random, x, z, 16, 16, 80, 1, 50, 100);
			this.addSandSpawn(Evercraft_Blocks.blockpowersand, w, random, x, z, 16, 16, 100, 1, 50, 140);
			this.addOreSpawn(Evercraft_Blocks.blockcopperore, w, random, x, z, 16, 16, 200, 1, 10, 60);
			this.addOreSpawn(Evercraft_Blocks.blocktinore, w, random, x, z, 16, 16, 170, 1, 5, 40);
			this.addOreSpawn(Evercraft_Blocks.blocksilverore, w, random, x, z, 16, 16, 120, 1, 0, 25);
			this.addOreSpawn(Evercraft_Blocks.blockbauxiteore, w, random, x, z, 16, 16, 100, 1, 5, 50);
			this.addOreSpawn(Evercraft_Blocks.blockzincore, w, random, x, z, 16, 16, 180, 1, 10, 60);
			this.addOreSpawn(Evercraft_Blocks.blockmagneticstone, w, random, x, z, 16, 16, 40, 1, 0, 70);
			this.addOreSpawn(Evercraft_Blocks.blockleadore, w, random, x, z, 16, 16, 90, 1, 10, 40);
		}
	}
//NETHER
	private void generateNether(Random random, int x, int z, World w) {
		if (Evercraft_Config_WorldMegaVeins.generateMegaVeins == true) {
			
		       int rand = random.nextInt(Evercraft_Config_WorldMegaVeins.megaVeinsRARITY);

	    	   if (rand == 1) {		
				int Xcoord = x + random.nextInt(16);
				int Ycoord = 40 + random.nextInt(200);
				int Zcoord = z + random.nextInt(16);
				
				new ECWorldGenMineableNetherMeta(Blocks.quartz_ore, 0, 100).generate(w, random, Xcoord, Ycoord, Zcoord);
			}
		}
	}
	//END
		private void generateEnd(Random random, int x, int z, World w) {
		
		}
		
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		public void addOreSpawn_Emerald(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
		{
		       int maxPossY = minY + (maxY - 1);
		       assert maxY > minY: "The maximum Y must be greater than the Minimum Y";
		       assert maxX > 0 && maxX <= 16: "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
		       assert minY > 0: "addOreSpawn: The Minimum Y must be greater than 0";
		       assert maxY < 256 && maxY > 0: "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
		       assert maxZ > 0 && maxZ <= 16: "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";

		       int rand = random.nextInt(Evercraft_Config_WorldMegaVeins.megaVeinsRARITY);
		       
		       int diffBtwnMinMaxY = maxY - minY;
		       for(int x = 0; x < chancesToSpawn; x++){

		    	   if (rand == 1) {		    		 
		             int posX = blockXPos + random.nextInt(maxX);
		             int posY = minY + random.nextInt(diffBtwnMinMaxY);
		             int posZ = blockZPos + random.nextInt(maxZ);
		             (new WorldGenMinable_Emeralds(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
		    	   }
		       }
		}
		
	public void addOreSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
	       int maxPossY = minY + (maxY - 1);
	       assert maxY > minY: "The maximum Y must be greater than the Minimum Y";
	       assert maxX > 0 && maxX <= 16: "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
	       assert minY > 0: "addOreSpawn: The Minimum Y must be greater than 0";
	       assert maxY < 256 && maxY > 0: "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
	       assert maxZ > 0 && maxZ <= 16: "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";

	       int rand = random.nextInt(Evercraft_Config_WorldMegaVeins.megaVeinsRARITY);
	       
	       int diffBtwnMinMaxY = maxY - minY;
	       for(int x = 0; x < chancesToSpawn; x++){

	    	   if (rand == 1) {		    		 
	             int posX = blockXPos + random.nextInt(maxX);
	             int posY = minY + random.nextInt(diffBtwnMinMaxY);
	             int posZ = blockZPos + random.nextInt(maxZ);
	             (new WorldGenMinable(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
	    	   }
	       }
	}
	
	
	public void addSandSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
	       int maxPossY = minY + (maxY - 1);
	       assert maxY > minY: "The maximum Y must be greater than the Minimum Y";
	       assert maxX > 0 && maxX <= 16: "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
	       assert minY > 0: "addOreSpawn: The Minimum Y must be greater than 0";
	       assert maxY < 256 && maxY > 0: "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
	       assert maxZ > 0 && maxZ <= 16: "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";

	       int rand = random.nextInt(Evercraft_Config_WorldMegaVeins.megaVeinsRARITY);
	       
	       int diffBtwnMinMaxY = maxY - minY;
	       for(int x = 0; x < chancesToSpawn; x++)
	       {
	    	   if (rand == 1) {		    		 
	             int posX = blockXPos + random.nextInt(maxX);
	             int posY = minY + random.nextInt(diffBtwnMinMaxY);
	             int posZ = blockZPos + random.nextInt(maxZ);
	             (new WorldGenMinable_inSAND(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
	    	   }
	       }
	}
	
	public void addDirtSpawn(Block block, World world, Random random, int blockXPos, int blockZPos, int maxX, int maxZ, int maxVeinSize, int chancesToSpawn, int minY, int maxY)
	{
	       int maxPossY = minY + (maxY - 1);
	       assert maxY > minY: "The maximum Y must be greater than the Minimum Y";
	       assert maxX > 0 && maxX <= 16: "addOreSpawn: The Maximum X must be greater than 0 and less than 16";
	       assert minY > 0: "addOreSpawn: The Minimum Y must be greater than 0";
	       assert maxY < 256 && maxY > 0: "addOreSpawn: The Maximum Y must be less than 256 but greater than 0";
	       assert maxZ > 0 && maxZ <= 16: "addOreSpawn: The Maximum Z must be greater than 0 and less than 16";

	       int rand = random.nextInt(Evercraft_Config_WorldMegaVeins.megaVeinsRARITY);
	       
	       int diffBtwnMinMaxY = maxY - minY;
	       for(int x = 0; x < chancesToSpawn; x++)
	       {
	    	   if (rand == 1) {		    		 
	             int posX = blockXPos + random.nextInt(maxX);
	             int posY = minY + random.nextInt(diffBtwnMinMaxY);
	             int posZ = blockZPos + random.nextInt(maxZ);
	             (new WorldGenMinable_inDIRT(block, maxVeinSize)).generate(world, random, posX, posY, posZ);
	       }
	    }
	}
}



