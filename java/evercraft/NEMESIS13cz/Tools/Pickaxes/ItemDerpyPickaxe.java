package evercraft.NEMESIS13cz.Tools.Pickaxes;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import evercraft.NEMESIS13cz.ModInformation;

public class ItemDerpyPickaxe extends ItemPickaxe
{

    public ItemDerpyPickaxe(Item.ToolMaterial par2ECEnumToolMaterial)
    {
        super(par2ECEnumToolMaterial);
    }

    public void addInformation(ItemStack par1, EntityPlayer par2, List par3, boolean par4)
    {
        par3.add("Uses: " + (this.getMaxDamage() - this.getDamage(par1)));
        par3.add("Mining Speed: 12.0");
        par3.add("Mining Level: Obsidian");
    }
    @SideOnly(Side.CLIENT)
    public boolean hasEffect(ItemStack par1ItemStack)
    {
        return true;
    }
    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }



@SideOnly(Side.CLIENT)
public void registerIcons(IIconRegister par1IconRegister)
{
    this.itemIcon = par1IconRegister.registerIcon(ModInformation.TEXTUREPATH + ":" + ModInformation.DERPY_PICKAXE);
}
}
