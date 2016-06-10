import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
 
public class Drachenkurve extends Application {
	
	Logger logger = Logger.getLogger(Drachenkurve.class.getName());
	
    public static void main(String[] args) {
        launch(args);
    }
 
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Drachenkurve");
        Group root = new Group();
        Canvas canvas = new Canvas(1600, 1200);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        drawShapes(gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    private void drawShapes(GraphicsContext gc) {
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(1);
               
        List<String> navigation = new ArrayList<String>();
		navigation.add("R");
		
		for(int i = 0; i < 13; i++) {
			// System.out.println("***\n  Start der For-Schleife \n***");
//			logger.info("Start der For-Schleife");
			List<String> navigationAdding = new ArrayList<String>(navigation);
			// System.out.println("navigationAdding = navigation; \n navigation = " + navigation + "\n navigationAdding = " + navigationAdding + "\n");
			navigationAdding.set(navigationAdding.size()/2, "L");
			logger.info("navigationAdding.set(navigationAdding.size()/2, \"L\"); \n navigation = " + navigation + "\n navigationAdding = " + navigationAdding + "\n");
			navigation.add("R");
			// System.out.println("navigation.add(\"R\"); \n navigation = " + navigation + "\n navigationAdding = " + navigationAdding + "\n");
			navigation.addAll(navigationAdding);
			// System.out.println("navigation.addAll(navigationAdding); \n navigation = " + navigation + "\n navigationAdding = " + navigationAdding + "\n");
			// System.out.println("***\n  Ende der For-Schleife \n***");
			int o = i + 2;
			logger.info("Der berechnete Algorithmus in der " + o + ". Ordnung lautet: " + navigation + "\n");
		}
		
		double z = 5;
		
		double x1 = 600+z;
        double x2 = 600;
        double y1 = 400;
        double y2 = 400;
        
        gc.strokeLine(x1, y1, x2, y2);
        
		for(String navigationDirection : navigation){
			
			if(x1 == x2 && y1 > y2){
				y1 = y1 - z;
				
				// A Linie : von links nach rechts, wenn vorher nach oben
				if(navigationDirection == "R"){
					x2 = x2 + z;
					gc.strokeLine(x1, y1, x2, y2);
					logger.info("A Linie gezeichnet,\nKoordinaten: x1=" + x1 + " x2=" + x2 + " y1=" + y1 + " y2=" + y2 +"\n");
				}
				
				// B Linie : von rechts nach links, wenn vorher nach oben
				else if(navigationDirection == "L") {
					x2 = x2 - z;
					gc.strokeLine(x1, y1, x2, y2);
					logger.info("B Linie gezeichnet,\nKoordinaten: x1=" + x1 + " x2=" + x2 + " y1=" + y1 + " y2=" + y2 +"\n");
				} else {
					logger.warning("navigationDirection ist kein R oder L");
				}
			} else if(x1 == x2 && y1 < y2){
				y1 = y1 + z;
				
				// C Linie : von links nach rechts, wenn vorher nach unten
				if(navigationDirection == "R"){
					x2 = x2 - z;
					gc.strokeLine(x1, y1, x2, y2);
					logger.info("C Linie gezeichnet,\nKoordinaten: x1=" + x1 + " x2=" + x2 + " y1=" + y1 + " y2=" + y2 +"\n");
				}
				
				// D Linie : von rechts nach links, wenn vorher nach unten
				else if(navigationDirection == "L"){
					x2 = x2 + z;
					gc.strokeLine(x1, y1, x2, y2);
					logger.info("D Linie gezeichnet,\nKoordinaten: x1=" + x1 + " x2=" + x2 + " y1=" + y1 + " y2=" + y2 +"\n");
				} else {
					logger.warning("navigationDirection ist kein R oder L");
				}
			} else if(y1 == y2 && x1 < x2){
				x1 = x1 + z;
				
				// E Linie : von links nach rechts, wenn vorher nach rechts
				if(navigationDirection == "R"){
					y2 = y2 + z;
					gc.strokeLine(x1, y1, x2, y2);
					logger.info("E Linie gezeichnet,\nKoordinaten: x1=" + x1 + " x2=" + x2 + " y1=" + y1 + " y2=" + y2 +"\n");
				}
				
				// F Linie : von rechts nach links, wenn vorher nach rechts
				else if(navigationDirection == "L"){
					y2 = y2 - z;
					gc.strokeLine(x1, y1, x2, y2);
					logger.info("F Linie gezeichnet,\nKoordinaten: x1=" + x1 + " x2=" + x2 + " y1=" + y1 + " y2=" + y2 +"\n");
				} else {
					logger.warning("navigationDirection ist kein R oder L");
				}			
			} else if(y1 == y2 && x1 > x2){
				x1 = x1 - z;
				
				// G Linie : von links nach rechts, wenn vorher nach links
				if(navigationDirection == "R"){					
					y2 = y2 - z;
					gc.strokeLine(x1, y1, x2, y2);
					logger.info("G Linie gezeichnet,\nKoordinaten: x1=" + x1 + " x2=" + x2 + " y1=" + y1 + " y2=" + y2 +"\n");
				}
				
				// H Linie : von rechts nach links, wenn vorher nach links
				else if(navigationDirection =="L"){
					y2 = y2 + z;
					gc.strokeLine(x1, y1, x2, y2);
					logger.info("H Linie gezeichnet,\nKoordinaten: x1=" + x1 + " x2=" + x2 + " y1=" + y1 + " y2=" + y2 +"\n");
				} else {
					logger.warning("navigationDirection ist kein R oder L");
				}
			}
		}
    }
}