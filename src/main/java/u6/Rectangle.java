package u6;

/**
 * @author Bahareh Shidrang, Yuxin Liu
 * @version 1.0
*/

public class Rectangle {

/* Attributes */	
	public int x;
	public int y;
	public int width;
	public int height;

/* Constructors */
	public Rectangle(int x, int y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}	
	public Rectangle(){
		this(0, 0, 100, 100);
	}

/* Methods */
	public int area() {
		return width*height;
	}
	public Rectangle clone(){
		return new Rectangle(x, y, width, height);
	}
	public boolean equal(Rectangle r){
		if((this.x == r.x) && (this.y == r.y) && (this.width == r.width) && (this.height == r.height))
			return true;
		else
			return false;
	}
	public int compareAreaTo(Rectangle r){
		if(this.area() == r.area())
			return 0;
		else if(this.area() > r.area())
			return 1;
		else
			return -1;
	}
	public int end_x(){
		return x+width;
	}
	public int end_y(){
		return y+height;
	}
	private boolean between(int small, int big, int mid){
		return (small<=mid) && (mid<=big);
	}
	private boolean between_point(int x, int y){
		return between(this.x, this.end_x(), x) && between(this.y, this.end_y(), y);
	}
	public boolean contains(Rectangle r){
		if(
		between_point(r.x, r.y) && between_point(r.end_x(), r.end_y()) 
		&& between_point(r.end_x(), r.y) && between_point(r.x, r.end_y())
		)
			return true;
		else
			return false;
	}
	public boolean overlaps(Rectangle r){
		if(
		this.contains(r) || r.contains(this)
		||between_point(r.x, r.y) || between_point(r.end_x(), r.end_y()) 
		|| between_point(r.end_x(), r.y) || between_point(r.x, r.end_y())
		/* Axis-Aligned Bounding Box */
		|| (this.x < r.end_x() && this.end_x() > r.x && this.y < r.end_y() && this.end_y() > r.y)
		)
			return true;
		else
			return false;
	}
	private static java.awt.Rectangle to_awt(Rectangle r){
		var rec = new java.awt.Rectangle(r.x, r.y, r.width, r.height);
		return rec;
	}
	private static Rectangle from_awt(java.awt.Rectangle r){
		Rectangle rec = new Rectangle(r.x, r.y, r.width, r.height);
		return rec;
	}
	public Rectangle section(Rectangle r){
		var rec1 = to_awt(this);
		var r1 = to_awt(r);

		var section1 = rec1.intersection(r1);
		
		Rectangle section = from_awt(section1);
		return section;
	}
	public static Rectangle smallestBoundingRectangle(Rectangle[] recs){
		var rec_total = to_awt(recs[0]);
		for(int i=0; i<recs.length-1; i++){
			var rec_next = to_awt(recs[i+1]);
			rec_total = rec_total.union(rec_next);
		}
		Rectangle smallestBounding = from_awt(rec_total);
		return smallestBounding;
	}

}
