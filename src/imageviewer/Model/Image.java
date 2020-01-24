package imageviewer.Model;

public interface Image {
    Image next();
    Image prev();
    Object bitMap();
}