package edu.example.adventure.blockentity;

import edu.example.adventure.AdventureExample;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.storage.ReadView;
import net.minecraft.storage.WriteView;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class RussianBlockEntity extends BlockEntity {

    public static final Identifier ID = Identifier.of(AdventureExample.MOD_ID,"russian_block_entity");
    public RussianBlockEntity(BlockPos pos, BlockState state) {
        super(AdventureExample.RUSSIAN_BLOCK_ENTITY_BLOCK, pos, state);
    }

    int pot = 1;

    public int getPot() {
        return pot;
    }

    public void incrementPot(){
        pot++;
        markDirty();
    }

    @Override
    protected void writeData(WriteView view) {
        view.putInt("pot",pot);
    }

    @Override
    protected void readData(ReadView view) {
        pot = view.getInt("pot",1);
    }
}
