package imageviewer;

import imageviewer.Controller.Command;
import imageviewer.Controller.NextImageCommand;
import imageviewer.Controller.PrevImageCommand;
import imageviewer.Model.Image;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class Main extends JFrame{
    private Map<String, Command> commands = new HashMap<>();
    private ImagePanel applicationDisplayPanel;
    private int xMousePosition;

    public static void main(String[] args) {
        new Main().setVisible(true);
    }

    public Main() throws HeadlessException {
        deployUI();
        addCommands();
    }

    private void addCommands() {
        commands.put("Next",new NextImageCommand(applicationDisplayPanel));
        commands.put("Prev",new PrevImageCommand(applicationDisplayPanel));
    }

    private void deployUI() {
        this.setTitle("Image Viewer");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setMinimumSize(new Dimension(500,500));
        this.setLocationRelativeTo(null);
        this.getContentPane().add(imagePanel());
        this.add(toolBar(),BorderLayout.SOUTH);
    }

    private ImagePanel imagePanel() {
        ImagePanel panel = new ImagePanel(image());
        panel.addMouseListener(new MouseInputAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                xMousePosition = e.getX();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if((xMousePosition - e.getX()) >= 0 ) commands.get("Next").execute();
                else commands.get("Prev").execute();
            }
        });
        applicationDisplayPanel = panel;
        return panel;
    }

    private Image image() {
        return new FileImageReader("/Users/yuncai/NetBeansProjects/ImageViewer/images").read();
    }

    private JMenuBar toolBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setLayout(new FlowLayout(FlowLayout.CENTER));
        menuBar.add(prevButton());
        menuBar.add(nextButton());
        return menuBar;
    }

    private JButton nextButton() {
        JButton nextButton = new JButton(">");
        nextButton.addActionListener(e -> commands.get("Next").execute());
        return nextButton;
    }

    private JButton prevButton() {
        JButton prevButton = new JButton("<");
        prevButton.addActionListener(e -> commands.get("Prev").execute());
        return prevButton;
    }
}