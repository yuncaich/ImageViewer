package imageviewer.View;

import imageviewer.Model.Image;

public interface ImageDisplay {
    Image image();
    void show(Image image);
}