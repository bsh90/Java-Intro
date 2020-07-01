package u6;

/**
 * @author: Bahareh Shidrang, Yuxin Liu
 * @version: 1.0
 */

public class App {
    public String getGreeting() {
        return "Exercise 6 solutions:";
    }
    private void mickey(double x, double y, double r){
	StdDraw.filledCircle(x, y, r);
	if(r >= 1){
		mickey(x+r, y+r, r/2);
		mickey(x+r, y-r, r/2);
		mickey(x-r, y+r, r/2);
		mickey(x-r, y-r, r/2);
	}
    }
    public void mickey_mouse(){
	StdDraw.setXscale(-50, 50);
	StdDraw.setYscale(-50, 50);
	StdDraw.setPenColor(StdDraw.GREEN);
	mickey(0, 0, 25);
    }
    public static void main(String[] args) {
        System.out.println(new App().getGreeting());

        new TestRectangle();
	new App().mickey_mouse();
    }
}
