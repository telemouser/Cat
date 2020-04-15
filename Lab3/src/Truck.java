 class Truck extends Vehicle{
	 public final static double MINGOODS = 5;
	 public final static double MAXGOODS = 20;
	 protected double load;
	 protected double max_load;
//	 
	 public Truck(double load, double max_load, double petrol_amount, double tank_volume){
		super(petrol_amount, tank_volume);
		this.load = load;
		this.max_load = max_load;
         }
	 public double getCurrentLoad() {
        return this.load;
	 }
	 public double getMaxLoad() {
        return this.max_load;
	 }
	 public boolean setCurrentLoad(double load){
		if(this.isOnBase()) return false; 
    	if(this.max_load > load){
			 this.load = load;
			 return true;
		}
    	else return false;
	 }
	 public void display(){
        System.out.printf("Current load: %f Max_load: %f Petrol_amount: %f TankVolume: %f\n",
                this.load,this.max_load, super.getPetrolAmount(), super.getTankVolume());
	 }
	 @Override
	 public void arrive()
	 {
    	Base.goods_on_base += this.getCurrentLoad();
		this.setCurrentLoad(0);
	    super.arrive();
	 }
	 @Override
	 public boolean leave()
	 {
    	if (super.leave()){
		    if(Base.goods_on_base > this.max_load){
		    	this.load = this.max_load;
		    	Base.goods_on_base -= this.max_load;
		    }
		    else {
		    	this.load = Base.goods_on_base;
		    	Base.goods_on_base = 0;
		    }
		    return true;
		 }
		 else return false;
	 }
}
