package it.unibo.pixArt.model.tool;

public interface ToolFactory {

    /**
     * @return
     */
    public Tool createTool(String name);

    Tool createNormalBrush();
    
}
