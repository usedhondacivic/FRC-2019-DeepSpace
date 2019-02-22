package frc.robot.framework.IO.sensors;
import io.github.pseudoresonance.pixy2api.Pixy2CCC.Block;
import io.github.pseudoresonance.pixy2api.Pixy2;
import java.util.ArrayList;

public class PixyBlocksSensor extends Sensor<ArrayList<Block>>{
    private Pixy2 pixy;
    private int mapNumber;
    private int maxBlocks;
    
    public PixyBlocksSensor(int mapNumber, int maxBlocks){
        this.mapNumber = mapNumber;
        this.maxBlocks = maxBlocks;
        this.pixy = Pixy2.createInstance(Pixy2.LinkType.SPI);
        int init = this.pixy.init();
        System.out.println(init);
        this.pixy.setLamp((byte)1, (byte)1);
    }

    public ArrayList<Block> get(){
        int error = this.pixy.getCCC().getBlocks(false, this.mapNumber, this.maxBlocks);
        return this.pixy.getCCC().getBlocks();
    }

    public void computeDeltas(){
        
    }
}