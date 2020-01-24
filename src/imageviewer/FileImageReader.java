package imageviewer;

import imageviewer.Model.Image;
import imageviewer.View.ImageReader;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

public class FileImageReader implements ImageReader {
    private final File[] files;
    private static final  String[] ImageExtensions = {".jpg",".png",".PNG",".gif"};

    public FileImageReader(String path) {
        this(new File(path));
    }

    public FileImageReader(File folder) {
        this.files = folder.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                for(String extension :ImageExtensions) if(name.endsWith(extension)) return true;
                return false;
            }
        });
    }

    public Image read() {
        return imageAt(0);
    }

    private Image imageAt(int index) {
        return new Image() {
            @Override
            public Image next() {
                return imageAt(index < files.length-1 ? index+1 : 0);
            }

            @Override
            public Image prev() {
                return imageAt(index > 0 ? index-1 : files.length-1);
            }

            @Override
            public Object bitMap() {
                try {
                    return ImageIO.read(files[index]);
                } catch (IOException e) {
                    return null;
                }
            }
        };
    }
}