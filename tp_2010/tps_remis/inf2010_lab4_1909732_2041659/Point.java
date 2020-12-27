package tp4;

public class Point implements Comparable<Point> {
    // TODO vous pouvez modifier ce que vous voulez, tant que vous ne modifiez pas les tests

    private Integer x;
    private Integer y;
    private Integer dist=0;
    private Integer index=0;
    boolean placed=false,placed1=false;
    
    public Point(String xy) {
        String[] xAndY = xy.split(" +");
        this.x = Integer.parseInt(xAndY[0]);
        this.y = Integer.parseInt(xAndY[1]);
    }

    public Point(Integer x, Integer y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return String.format("{X: %d, Y: %d}", x, y);
    }

    @Override
    public boolean equals(Object obj) {
        Point point = (Point)obj;
        return point.x.equals(x) && point.y.equals(y);
    }

    @Override
    public int compareTo(Point point) {
        // TODO ceci vous sera peut etre utile
    	if(this.dist<point.dist)
    		return -1;
    	else if(this.dist>point.dist)
    		return 1;
    	else {
    		if(this.index<point.getIdx())
    			return -1;
    		else if(this.index>point.getIdx())
    			return 1;
    					
    	}
    	return 0;
    }
    
    public void setDist(Point point) {
    	this.dist=Math.abs(point.x-this.x)+Math.abs(point.y-this.y);
    }


	public void setIdx(Integer idx) {
		this.index=idx;
	}
	public int getIdx() {
		return this.index;
	}
	public void setPlace(boolean f) {
		placed=f;
	}
	public void setPlace1(boolean f) {
		placed1=f;
	}
}
