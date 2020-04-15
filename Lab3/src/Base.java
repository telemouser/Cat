final public class Base{
	public static final int MAXVEHICLENUMBER = 10;
	static public int people_on_base = 200;
	static public int vehicles_on_base = MAXVEHICLENUMBER;
	static public double petrol_on_base = 20000;//Liters
	static public double goods_on_base = 5000;  //Tons
	//
	static public void print(){
		System.out.printf("People on base: %d Vehicles on base: %d Petrol on base: %f Goods on base:%f\n",
			Base.people_on_base, Base.vehicles_on_base, Base.petrol_on_base, Base.goods_on_base);
	}
}