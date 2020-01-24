
package imageviewer.Controller;

import imageviewer.View.ImageDisplay;

public class NextImageCommand implements Command {
    private final ImageDisplay display;

    public NextImageCommand(ImageDisplay display) {
        this.display = display;
    }

    @Override
    public void execute() {
        display.show(display.image().next());
    }
}