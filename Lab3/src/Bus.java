public class Bus extends Vehicle{
	public final static int MINPEOPLE = 20;
	public final static int MAXPEOPLE = 60;
	protected int people;
	protected int max_people; 
//	
	public Bus(int people, int max_people, double petrol_amount, double tank_volume){
		 super(petrol_amount, tank_volume);
		 this.people = people;
		 this.max_people = max_people;
	}
	public int getPeopleCount(){
		 return this.people;
	 }
	public int getMaxPeople(){
		 return this.max_people;
	 }
	public boolean setPeopleCount(int people){
		 if (this.isOnBase()) return false;
		 if(people < this.max_people)
		 {
			 this.people = people;
			 return true;
		 }
		 else return false;
	 }
	public void display(){
	        System.out.printf("People: %d Max people: %d Petrol amount: %f Tank volume: %f\n",
	                this.people, this.max_people, super.getPetrolAmount(), super.getTankVolume());
	 }
	 @Override
	 public void arrive(){
		 Base.people_on_base += this.getPeopleCount();
		 this.setPeopleCount(0);
	     super.arrive();
	 }
	 @Override
	 public boolean leave(){
		 if (super.leave()){
		    if(Base.people_on_base > this.max_people){
		    	this.people = this.max_people;
		    	Base.people_on_base -= this.max_people;
		    }
		    else {
		    	this.people = Base.people_on_base;
		    	Base.people_on_base = 0;
		    }
		    return true;
		 }
		 else return false;
	 }
}	 