public  class Vehicle{
	final static int MINTANK = 100;
	final static int MAXTANK = 500;

	protected double petrol_amount;
	 protected double tank_volume;
	 protected boolean isOnBase;
	 
	 public Vehicle(double petrol_amount, double tank_volume) {
		 this.petrol_amount = petrol_amount;
		 this.tank_volume = tank_volume;
		 this.isOnBase = false;
		 }
//	 public abstract int getMax_People();
//	 public abstract int getPeopleCount();
//	 public abstract double getMaxLoad();
//	 public abstract double getCurrentLoad();
	 public double getTankVolume() {
		 return this.tank_volume;
	 }
     public double getPetrolAmount(){
    	 return this.petrol_amount;
     }
     public boolean setPetrolAmount(double petrol_amount){
		 if (this.isOnBase()) return false;
		 if(petrol_amount > this.tank_volume){
			 this.tank_volume = petrol_amount;
			 return true;
		 }
		 else return false;
	 }
     public void arrive(){
    	 Base.people_on_base++;
    	 Base.vehicles_on_base++;
    	 this.isOnBase = true;
     }
     public boolean leave(){
    	 if (((Base.petrol_on_base + this.petrol_amount) < this.tank_volume) && Base.people_on_base <= 0){
    		 return false;
         }
         else {
              Base.people_on_base--;
              Base.vehicles_on_base--;
              Base.petrol_on_base -= this.tank_volume - this.petrol_amount;
              this.petrol_amount = this.tank_volume;
              this.isOnBase = false;
              return true;
         }
     }	 
     public boolean isOnBase() {
    	return isOnBase;
     }
 }
