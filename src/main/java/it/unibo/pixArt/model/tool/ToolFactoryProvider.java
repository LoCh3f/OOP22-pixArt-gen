package it.unibo.pixArt.model.tool;

public class ToolFactoryProvider {
    
    public static ToolFactory getFactory (boolean fill){
        
        if (fill){
            return new FillToolFactory();
        } else {
            return new DrawingToolFactory();
        }
    }
        

}
