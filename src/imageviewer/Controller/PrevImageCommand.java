
package imageviewer.Controller;

import imageviewer.View.ImageDisplay;

public class PrevImageCommand implements Command{
    private ImageDisplay display;

    public PrevImageCommand(ImageDisplay display) {
        this.display = display;
    }

    @Override
    public void execute() {
        display.show(display.image().prev());
    }
}