package evercraft.NEMESIS13cz.TileEntity.Gui;

import java.util.ArrayList;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.StatCollector;

import org.lwjgl.opengl.GL11;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import evercraft.NEMESIS13cz.Evercraft;
import evercraft.NEMESIS13cz.ModInformation;
import evercraft.NEMESIS13cz.MachineRecipes.HighPressureFurnaceRecipes;
import evercraft.NEMESIS13cz.Main.Evercraft_Items;
import evercraft.NEMESIS13cz.TileEntity.Container.ContainerHighTemperatureFurnace;
import evercraft.NEMESIS13cz.TileEntity.TileEntity.TileEntityHighTemperatureFurnace;
import evercraft.NEMESIS13cz.Utils.GuiHelper;
import evercraft.NEMESIS13cz.Utils.PowerHandler;

@SideOnly(Side.CLIENT)
public class GuiHighTemperatureFurnace extends GuiContainer
{
    
	private int recipeCount;
	private ArrayList<ItemStack> recipe_inputs = new ArrayList<ItemStack>();
	private int structureMode;
    protected static RenderItem itemRenderer = new RenderItem();
    
    private TileEntityHighTemperatureFurnace furnaceInventory;
    private static final ResourceLocation background_texture = new ResourceLocation(ModInformation.GUITEXTUREPATH + "/" + ModInformation.HIGHTEMPERATUREFURNACE_GUI + ".png");
    private static final ResourceLocation peripherals = new ResourceLocation(ModInformation.GUITEXTUREPATH + "/" + ModInformation.INV_PER + ".png");
    public GuiHighTemperatureFurnace(InventoryPlayer par1InventoryPlayer, TileEntityHighTemperatureFurnace par2TileEntityFurnace)
    {
        super(new ContainerHighTemperatureFurnace(par1InventoryPlayer, par2TileEntityFurnace));
        this.furnaceInventory = par2TileEntityFurnace;
        this.addRecipes();
        this.recipeCount = -1;
        this.structureMode = 0;
    }

    /**
     * Defines the actual recipes.
     */
    
	private void addRecipes() {
		for (int i = 0; i < Evercraft.getEvercraft().highPressureFurnaceRecipes.length; i++) {
			this.registerRecipe((ItemStack) Evercraft.getEvercraft().highPressureFurnaceRecipes[i]);
		}
	}
	
	/**
	 * Adds all the recipe things into their ArrayLists.
	 * 
	 * @param input
	 */
	
	private void registerRecipe(ItemStack input) {
		recipe_inputs.add(input);
	}
	
	/**
	 * Switches between foreground and background rendering
	 * 
	 * @param mode
	 * @param mouseX
	 * @param mouseY
	 * @param posX1
	 * @param posY1
	 * @param posX2
	 * @param posY2
	 */
	
    private void getRecipe(boolean mode, int mouseX, int mouseY, int posX1, int posY1, int posX2, int posY2) {
    	if (this.recipeCount > -1) {
	    	if (mode) {
	    		this.drawRecipeForeground(this.recipe_inputs.get(this.recipeCount).getDisplayName(), HighPressureFurnaceRecipes.smelting().getSmeltingResult(this.recipe_inputs.get(this.recipeCount)).getDisplayName(), mouseX, mouseY, posX1, posY1, HighPressureFurnaceRecipes.smelting().getHighTemperatureFurnaceRecipeSpeed(this.recipe_inputs.get(this.recipeCount)), HighPressureFurnaceRecipes.smelting().isAdvanced(this.recipe_inputs.get(this.recipeCount)));
	    	}else{
	    		this.drawRecipeBackground(this.recipe_inputs.get(this.recipeCount), HighPressureFurnaceRecipes.smelting().getSmeltingResult(this.recipe_inputs.get(this.recipeCount)), HighPressureFurnaceRecipes.smelting().getSmeltingResult(this.recipe_inputs.get(this.recipeCount)).stackSize, posX2, posY2);
	    	}
    	}
    }
    
    /**
     * Draws the recipe item names in the inventory.
     * 
     * @param inputName
     * @param outputName
     * @param mouseX
     * @param mouseY
     * @param x
     * @param y
     */
    
    private void drawRecipeForeground(String inputName, String outputName, int mouseX, int mouseY, int x, int y, int processSpeed, boolean adv) {
    	if (this.recipeCount > -1) {
            this.fontRendererObj.drawString(GuiHelper.TicksIntoTime(processSpeed), 214 - this.fontRendererObj.getStringWidth(GuiHelper.TicksIntoTime(processSpeed)) / 2, this.ySize - 150, 4210752);
    		String cost = processSpeed * PowerHandler.HTFurnaceConsump + " EE";
            this.fontRendererObj.drawString(cost, 214 - this.fontRendererObj.getStringWidth(cost) / 2, this.ySize - 138, 4210752);
    		String consumption = PowerHandler.HTFurnaceConsump + " EE/tick";
            this.fontRendererObj.drawString(consumption, 214 - this.fontRendererObj.getStringWidth(consumption) / 2, this.ySize - 126, 4210752);
            
            if (mouseX > x + 54 && mouseX < x + 55 + 18 && mouseY > y + 33 && mouseY < y + 34 + 18) {
				ArrayList<String> Input = new ArrayList<String>();
				Input.add(inputName);
		    	this.drawHoveringText(Input, mouseX - ((this.width - this.xSize) / 2), mouseY - ((this.height - this.ySize) / 2), fontRendererObj);
			}
			if (mouseX > x + 114 && mouseX < x + 115 + 18 && mouseY > y + 33 && mouseY < y + 34 + 18) {
				ArrayList<String> Output = new ArrayList<String>();
				Output.add(outputName);
		    	this.drawHoveringText(Output, mouseX - ((this.width - this.xSize) / 2), mouseY - ((this.height - this.ySize) / 2), fontRendererObj);
			}
			if (adv) {
	            this.fontRendererObj.drawString("Requires an upgraded", 112 - this.fontRendererObj.getStringWidth("Requires an upgraded") / 2, this.ySize - 104, 0x404040);
	            this.fontRendererObj.drawString("furnace.", 112 - this.fontRendererObj.getStringWidth("furnace.") / 2, this.ySize - 96, 0x404040);
			}
    	}
    }
    
    /**
     * Draws the recipe images in the inventory.
     * 
     * @param input
     * @param output
     * @param amountOut
     * @param x
     * @param y
     */
    
    private void drawRecipeBackground(ItemStack input, ItemStack output, int amountOut, int x, int y) {
    	if (this.recipeCount > -1) {
	        this.mc.renderEngine.bindTexture(this.peripherals);
	        this.drawTexturedModalRect(x + 176, y + 5, 14, 16, 80, 56);
	        
	    	itemRenderer.zLevel = 100.0F;
	    	if (this.furnaceInventory.furnaceItemStacks[0] == null) {
	        	this.itemRenderer.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), input, x + 56, y + 35);
	    	}
	    	if (this.furnaceInventory.furnaceItemStacks[2] == null) {
	    		this.itemRenderer.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), output, x + 116, y + 35);
	    		this.itemRenderer.renderItemOverlayIntoGUI(fontRendererObj, mc.getTextureManager(), new ItemStack(output.getItem(), amountOut, output.getItemDamage()), x + 116, y + 35);
	    	}
	    	itemRenderer.zLevel = 0.0F;
    	}
    }
    
    /**
     * Draws buttons.
     * 
     * @param k
     * @param l
     */
    
    private void drawButtons(int k, int l) {
        this.mc.renderEngine.bindTexture(this.peripherals);
        
        if (this.recipeCount < this.recipe_inputs.size()-1) {
            this.drawTexturedModalRect(k + 47, l + 35, 0, 7, 7, 7);
        }else{
            this.drawTexturedModalRect(k + 47, l + 35, 0, 0, 7, 7);
        }
        if (this.recipeCount > -1) {
            this.drawTexturedModalRect(k + 47, l + 44, 7, 7, 7, 7);
        }else{
            this.drawTexturedModalRect(k + 47, l + 44, 7, 0, 7, 7);
        }
        this.drawTexturedModalRect(k + 36, l + 35, 14, 0, 9, 16);
        
        if (this.recipeCount == -1) {
            if (this.structureMode == 0) {
                this.drawTexturedModalRect(k + 120, l - 20, 23 + 46, 0, 24, 16);
            }else{
                this.drawTexturedModalRect(k + 120, l - 20, 23, 0, 24, 16);
            }
            if (this.structureMode == 1) { //TODO max structures int
                this.drawTexturedModalRect(k + 120 + 25, l - 20, 23 + 69, 0, 24, 16);
            }else{
                this.drawTexturedModalRect(k + 120 + 25, l - 20, 23 + 23, 0, 23, 16);
            }
        }
    }
    
    /**
     * Handles mouse clicking.
     */
    
	public void mouseClicked(int x, int y, int button) {
		super.mouseClicked(x, y, button);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;

        if (this.recipeCount < this.recipe_inputs.size()-1) {
        	if (x > k + 46 && x < k + 54 && y > l + 34 && y < l + 42) {
				this.recipeCount += 1;
			}
        }
        if (this.recipeCount > -1) {
        	if (x > k + 46 && x < k + 54 && y > l + 43 && y < l + 51) {
				this.recipeCount -= 1;
			}
        }
        if (x > k + 35 && x < k + 45 && y > l + 34 && y < l + 51) {
			this.recipeCount = -1;
        }
        
        if (this.recipeCount == -1) {
            if (this.structureMode > 0) {
                if (x > k + 119 && x < k + 143 && y > l - 21 && y < l - 4) {
                	this.structureMode--;
                }
            }
            if (this.structureMode < 1) { //TODO max structures int
                if (x > k + 119 + 25 && x < k + 143 + 25 && y > l - 21 && y < l - 4) {
                	this.structureMode++;
                }
            }
        }
	}
    
    /**
     * Draws the foreground layer of the inventory.
     */
    
    protected void drawGuiContainerForegroundLayer(int par1, int par2)
    {
        String s = this.furnaceInventory.hasCustomInventoryName() ? this.furnaceInventory.getInventoryName() : StatCollector.translateToLocal(this.furnaceInventory.getInventoryName());
        this.fontRendererObj.drawString(s, this.xSize / 2 - this.fontRendererObj.getStringWidth(s) / 2, 6, 4210752);
        this.fontRendererObj.drawString(StatCollector.translateToLocal("container.inventory"), 8, this.ySize - 92, 4210752);
        
        if (this.recipeCount == -1) {
	        this.fontRendererObj.drawString("Y+3", 232, this.ySize - 171, 0x000000);
	        this.fontRendererObj.drawString("Y+2", 232, this.ySize - 171 + 56, 0x000000);
	        this.fontRendererObj.drawString("Y+1", 232, this.ySize - 171 + 112, 0x000000);
	        this.fontRendererObj.drawString("Y", 232 + 12, this.ySize - 171 + 168, 0x000000);
	        
			if (this.furnaceInventory.isFormed2int == 0) {
		        this.fontRendererObj.drawString("Incomplete Structure!", this.xSize / 2 - 40, 60, 0x992000);
			}
        }
		
		if (par1 > ((this.width - this.xSize) / 2) + 5 && par1 < ((this.width - this.xSize) / 2) + 24 && par2 > ((this.height - this.ySize) / 2) + 5 && par2 < ((this.height - this.ySize) / 2) + 72) {
		ArrayList<String> lines = new ArrayList<String>();
		lines.add("EE: " + this.furnaceInventory.power + "/" + this.furnaceInventory.maxPower);
		this.drawHoveringText(lines, par1 - ((this.width - this.xSize) / 2), par2 - ((this.height - this.ySize) / 2), fontRendererObj);
		}
		
		if (this.furnaceInventory.isAdvanced) {
	        this.fontRendererObj.drawString("2600K", 140, this.ySize - 92, 4210752);
		}else{
	        this.fontRendererObj.drawString("1800K", 140, this.ySize - 92, 4210752);
		}
		
		this.getRecipe(true, par1, par2, (this.width - this.xSize) / 2, (this.height - this.ySize) / 2, 0, 0);
    }

    /**
     * Draw the background layer for the GuiContainer (everything behind the items)
     */
    protected void drawGuiContainerBackgroundLayer(float par1, int par2, int par3)
    {
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        int k = (this.width - this.xSize) / 2;
        int l = (this.height - this.ySize) / 2;
        this.mc.renderEngine.bindTexture(this.peripherals);

        if (this.recipeCount == -1) {
        	if (this.structureMode == 0) {
    	        this.drawTexturedModalRect(k + 176, l - 28, 14, 16, 80, 56);
    	        this.drawTexturedModalRect(k + 176, l - 28 + 56, 14, 16, 80, 56);
    	        this.drawTexturedModalRect(k + 176, l - 28 + 112, 14, 16, 80, 56);
    	        this.drawTexturedModalRect(k + 176, l - 28 + 168, 14, 16, 80, 56);
    	        
    	        this.drawTexturedModalRect(k + 172, l - 28, 14 + 80, 16, 4, 56);
    	        this.drawTexturedModalRect(k + 172, l - 28 + 168, 14 + 80, 16, 4, 56);
    	        
    	        this.drawTexturedModalRect(k + 178, l + 4 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 4 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 4 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 20 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 20 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 20 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 36 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 36 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 36 - 27, 0, 42, 14, 14);
    	
    	        this.drawTexturedModalRect(k + 178, l + 4 - 27 + 56, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 4 - 27 + 56, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 4 - 27 + 56, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 20 - 27 + 56, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 20 - 27 + 56, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 36 - 27 + 56, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 36 - 27 + 56, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 36 - 27 + 56, 0, 28, 14, 14);
    	
    	        this.drawTexturedModalRect(k + 178, l + 4 - 27 + 112, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 4 - 27 + 112, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 4 - 27 + 112, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 20 - 27 + 112, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 20 - 27 + 112, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 36 - 27 + 112, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 36 - 27 + 112, 0, 28, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 36 - 27 + 112, 0, 28, 14, 14);
    	
    	        this.drawTexturedModalRect(k + 178, l + 4 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 4 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 4 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 20 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 20 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 20 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 36 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 36 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 36 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 226, l + 20 - 27 + 168, 0, 14, 14, 14);
        	}else{
    	        this.drawTexturedModalRect(k + 176, l - 28, 14, 16, 80, 56);
    	        this.drawTexturedModalRect(k + 176, l - 28 + 56, 14, 16, 80, 56);
    	        this.drawTexturedModalRect(k + 176, l - 28 + 112, 14, 16, 80, 56);
    	        this.drawTexturedModalRect(k + 176, l - 28 + 168, 14, 16, 80, 56);
    	        
    	        this.drawTexturedModalRect(k + 172, l - 28, 14 + 80, 16, 4, 56);
    	        this.drawTexturedModalRect(k + 172, l - 28 + 168, 14 + 80, 16, 4, 56);
    	        
    	        this.drawTexturedModalRect(k + 178, l + 4 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 4 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 4 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 20 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 20 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 20 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 36 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 36 - 27, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 36 - 27, 0, 42, 14, 14);
    	
    	        this.drawTexturedModalRect(k + 178, l + 4 - 27 + 56, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 4 - 27 + 56, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 4 - 27 + 56, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 20 - 27 + 56, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 20 - 27 + 56, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 36 - 27 + 56, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 36 - 27 + 56, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 36 - 27 + 56, 0, 42, 14, 14);
    	
    	        this.drawTexturedModalRect(k + 178, l + 4 - 27 + 112, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 4 - 27 + 112, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 4 - 27 + 112, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 20 - 27 + 112, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 20 - 27 + 112, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 36 - 27 + 112, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 36 - 27 + 112, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 36 - 27 + 112, 0, 42, 14, 14);
    	
    	        this.drawTexturedModalRect(k + 178, l + 4 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 4 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 4 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 20 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 20 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 20 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 178, l + 36 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 194, l + 36 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 210, l + 36 - 27 + 168, 0, 42, 14, 14);
    	        this.drawTexturedModalRect(k + 226, l + 20 - 27 + 168, 0, 14, 14, 14);

        	}
        }
        
        this.mc.renderEngine.bindTexture(background_texture);
        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
        int i1;

        if (par2 > k + 5 && par2 < k + 24 && par3 > l + 5 && par3 < l + 72) {
            this.drawTexturedModalRect(k + 6, l + 5, 208, 17, 18, 68);
            this.drawTexturedModalRect(k + 7, l + 71 - 64, 176 + 16, 82 - 64, 16, 64 + 2);
        }
        
        if (this.furnaceInventory.hasPower())
        {
            i1 = this.furnaceInventory.getBurnTimeRemainingScaled(64);
            this.drawTexturedModalRect(k + 7, l + 71 - i1, 176, 82 - i1, 16, i1 + 2);
        }
        
        i1 = this.furnaceInventory.getCookProgressScaled(24);
        this.drawTexturedModalRect(k + 78, l + 33, 176, 0, i1 + 1, 18);

        this.drawButtons(k, l);
        this.getRecipe(false, 0, 0, 0, 0, k, l);
        
        if (this.recipeCount == -1 && this.structureMode > 0) {
        	this.itemRenderer.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), new ItemStack(Evercraft_Items.coil), k + 194, l + 20  - 27 + 55);
        	this.itemRenderer.renderItemAndEffectIntoGUI(fontRendererObj, mc.getTextureManager(), new ItemStack(Evercraft_Items.coil), k + 194, l + 20  - 27 + 111);
        }
    }
}
