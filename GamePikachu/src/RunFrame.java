import java.lang.Thread;

class RunFrame extends Thread {
	
	private Thread thr;
	   GameFrame gf;
	   
	   public RunFrame(GameFrame g){
		   gf = g;
	   }
	  
	   public void run() {
		   while(true)
		   {
				try {
					Thread.sleep(1000);
					gf.reducedTime();  // gọi hàm để thay đổi thời gian
					if(gf.time == 0){
						gf.Dispose();
						NotifyFrame fr = new NotifyFrame(gf, 1);
						fr.Visible();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
	   }
	   
	   public void start ()
	   {
	      if (thr == null)
	      {
	         thr = new Thread (this, "run");
	         thr.start ();	         
	      }
	   }

	}