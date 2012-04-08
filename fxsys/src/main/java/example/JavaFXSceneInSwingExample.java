package example;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.embed.swing.JFXPanel;
import javafx.scene.SceneBuilder;
import javafx.scene.layout.VBoxBuilder;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.RectangleBuilder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class JavaFXSceneInSwingExample {
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                swingMain(args);
            }
        });
    }

    private static void swingMain(String[] args) {
        Model model = new Model();
        View view = new View(model);
        Controller controller = new Controller(model, view);
        controller.mainLoop();
    }

    private static class Model {
        private ObjectProperty<Color> fill = new SimpleObjectProperty<Color>(Color.LIGHTGRAY);
        private ObjectProperty<Color> stroke = new SimpleObjectProperty<Color>(Color.DARKGRAY);

        public final Color getFill() {
            return fill.get();
        }

        public final void setFill(Color value) {
            this.fill.set(value);
        }

        public final Color getStroke() {
            return stroke.get();
        }

        public final void setStroke(Color value) {
            this.stroke.set(value);
        }

        public final ObjectProperty<Color> fillProperty() {
            return fill;
        }

        public final ObjectProperty<Color> strokeProperty() {
            return stroke;
        }
    }

    private static class View {
        public JFrame frame;
        public JFXPanel canvas;
        public JButton changeFillButton;
        public JButton changeStrokeButton;

        private View(final Model model) {
            frame = new JFrame("JavaFX in Swing Example");
            canvas = new JFXPanel();
            canvas.setPreferredSize(new Dimension(210, 210));
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    final Rectangle rectangle = RectangleBuilder.create()
                        .width(200)
                        .height(200)
                        .strokeWidth(10)
                        .build();
                    rectangle.fillProperty().bind(model.fillProperty());
                    rectangle.strokeProperty().bind(model.strokeProperty());
                    canvas.setScene(SceneBuilder.create()
                        .root(VBoxBuilder.create()
                            .children(rectangle)
                            .build())
                        .build());
                }
            });
            FlowLayout canvasPanelLayout = new FlowLayout(FlowLayout.CENTER, 10, 10);
            JPanel canvasPanel = new JPanel(canvasPanelLayout);
            canvasPanel.add(canvas);

            changeFillButton = new JButton("Change Fill");
            changeStrokeButton = new JButton("Change Stroke");
            FlowLayout buttonPanelLayout = new FlowLayout(FlowLayout.CENTER, 10, 10);
            JPanel buttonPanel = new JPanel(buttonPanelLayout);
            buttonPanel.add(changeFillButton);
            buttonPanel.add(changeStrokeButton);

            frame.add(canvasPanel, BorderLayout.CENTER);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setLocationByPlatform(true);
            frame.pack();
        }
    }

    private static class Controller {
        private View view;

        private Controller(final Model model, final View view) {
            this.view = view;
            this.view.changeFillButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            final Paint fillPaint = model.getFill();
                            if (fillPaint.equals(Color.LIGHTGRAY)) {
                                model.setFill(Color.GRAY);
                            } else {
                                model.setFill(Color.LIGHTGRAY);
                            }
                        }
                    });
                }
            });
            this.view.changeStrokeButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            final Paint strokePaint = model.getStroke();
                            if (strokePaint.equals(Color.DARKGRAY)) {
                                model.setStroke(Color.BLACK);
                            } else {
                                model.setStroke(Color.DARKGRAY);
                            }
                        }
                    });
                }
            });
        }

        public void mainLoop() {
            view.frame.setVisible(true);
        }
    }
}
