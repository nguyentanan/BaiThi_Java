import java.util.LinkedList;
import java.util.Queue;

public class ClassMatrix{
	
	private int sizeMatrix = 12;
	private int[][] matrix = new int [sizeMatrix + 2][sizeMatrix + 2];
	private int numberPicture = 34;
	
	// tạo mới ma trận
	public void newMatrix(int s) {
		sizeMatrix = s;
		int size = sizeMatrix * sizeMatrix;
		int[] a = new int[size];
		Point[] b = new Point[size];
		
		// tạo mảng a
		for(int i = 0; i < size / 2; i++)
		{
			a[i] = (int)(Math.random() * numberPicture + 1);
			a[ i + size / 2] = a[i];		
		}

		// tạo mảng b
		int ib = 0;
		for(int i=1; i<=sizeMatrix; i++)
		{	
			for(int j=1; j<=sizeMatrix; j++)
			{	
				b[ib] = new Point(i,j);
				ib++;
			}
		}	
		do{				
			buildMatrix(a,b,size);	
		}while(checkRoadMatrix() == false);
	}
	
	// xây dựng ma trận
	public void buildMatrix(int[] a, Point[] b, int size) {
		boolean[] check = new boolean[size];
		int rannum, i = 0;
		while(i < size)
		{
			rannum = (int)(Math.random()*size);	// lấy vị trí ngẫu nhiên trong mảng a
			if(check[rannum] == false)
			{			
				check[rannum] = true;
				matrix[b[i].getX()][b[i].getY()] = a[rannum];	// gán a[i] vào vị trí X,Y
				i++;
			}
		}
	}
	
	public int getMatrix(int y, int x){
		return matrix[y][x];
	}
	
	// hoán đổi phần tử
	public void convertMatrix() {
		int[] a = new int[300];
		Point[] b = new Point[300];
		int size = 0;
		
		for(int i=1; i<=sizeMatrix; i++)
			for(int j=1; j<=sizeMatrix; j++)
				if(matrix[i][j] != 0) // ô còn hình
				{
					a[size] = matrix[i][j];
					b[size] = new Point(i,j);
					size++;
				}
		do{
			buildMatrix(a,b,size);
		}while(checkRoadMatrix() == false);
	}
	
	// tìm kiếm cặp
	public Point[] seekPoint(){
		Point[] p = new Point[2];
		Point[] a, b;
		int sizeab = 0;
		a = new Point[300];
		b = new Point[300];
		for(int i=1; i<=sizeMatrix; i++)
			for(int j=1; j<=sizeMatrix; j++)
				if(getMatrix(i,j) != 0){
					sizeab++;
					a[sizeab] = new Point(j,i);
					b[sizeab] = new Point(j,i);
				}
		
		for(int i=1; i<=sizeab; i++)
			for(int j=i+1; j<=sizeab; j++)
				if(getMatrix(a[i].getY(),a[i].getX()) == getMatrix(b[j].getY(),b[j].getX())) //2 điểm là 1 cặp
					if(check(a[i],b[j]) == true){
						p[0] = a[i];
						p[1] = b[j];
						return p;
					}
		return p;
	}
	
	// kiểm tra liên thông giữa 2 điểm
	public boolean check(Point p1, Point p2) {
		//2 điểm trùng nhau
		if(p1.getX() == p2.getX() && p1.getY() == p2.getY())
			return false;
		
		//2 điểm khác cặp
		if(getMatrix(p1.getY(),p1.getX()) != getMatrix(p2.getY(), p2.getX()))
			return false;
		
		// tìm đường đi
		Queue<Point> myQueue = new LinkedList<Point>();
		int[][] checkMatrix = new int[sizeMatrix + 2][sizeMatrix + 2];
		int[] dx = new int[4];
		int[] dy = new int [4];
		int x, y, xx ,yy;
		Point p;
		dx[0] = 1; dx[1] = -1; dx[2] = 0; dx[3] = 0;
		dy[0] = 0; dy[1] = 0;  dy[2] = 1; dy[3] = -1;
		
		for(int i=0; i<sizeMatrix+2; i++)
			for(int j=0; j<sizeMatrix+2; j++)
				checkMatrix[i][j] = 0;
		
		for(int i=1; i<=sizeMatrix; i++)
			for(int j=1; j<=sizeMatrix; j++)
			{	
				if(matrix[i][j] != 0)
					checkMatrix[i][j] = -1;		// 0 tức là không có vật thể
			}
		checkMatrix[p2.getY()][p2.getX()] = 0;	// gán là không co hình để dễ tìm đường đi
		checkMatrix[p1.getY()][p1.getX()] = 0;	// lần đổi hướng tại đây là 0 (tận dụng ma trận này luôn)
		myQueue.add(p1);
		
		while(!myQueue.isEmpty())
		{
			Point pp = new Point(-1,-1);
			p = myQueue.peek();
			myQueue.poll();// lấy và xóa 1 phần tử trong queue
			x = p.getX();
			y = p.getY();

			for(int i=0; i<4; i++)
			{
				xx = x + dx[i];
				yy = y + dy[i];
				//if(checkMatrix[yy][xx] > 3) checkMatrix[yy][xx] = 0;
				while(xx > -1 && xx < sizeMatrix+2 && yy > -1 && yy < sizeMatrix+2 && checkMatrix[yy][xx] == 0)
				{
					pp = new Point(xx,yy);
					myQueue.add(pp);
					checkMatrix[yy][xx] = checkMatrix[y][x] + 1;
					xx += dx[i];
					yy += dy[i];
				}
			}
		}
		if(checkMatrix[p2.getY()][p2.getX()] >= 1 && checkMatrix[p2.getY()][p2.getX()] <= 3) //kiếm tra số lần chuyển hướng 
			return true;
		return false;
	}
	
	//kiểm tra đường đi 
	public boolean checkRoadMatrix(){
		Point[] a, b;
		int sizeab = 0;
		a = new Point[300];
		b = new Point[300];
		for(int i=1; i<=sizeMatrix; i++)
			for(int j=1; j<=sizeMatrix; j++)
				if(getMatrix(i,j) != 0)
				{
					sizeab++;
					a[sizeab] = new Point(j,i);
					b[sizeab] = new Point(j,i);
				}
		
		for(int i=1; i<=sizeab; i++)
			for(int j=1; j<=sizeab; j++)
				if(getMatrix(a[i].getY(),a[i].getX()) == getMatrix(b[j].getY(),b[j].getX()))
					if(check(a[i],b[j]) == true)
						return true;
		
		return false;
	}
	
	public void updateMatrix(Point p1, Point p2){
		matrix[p1.getY()][p1.getX()] = 0;
		matrix[p2.getY()][p2.getX()] = 0;
	}
	
	public int getSizeMatrix(){
		return sizeMatrix;
	}
}
