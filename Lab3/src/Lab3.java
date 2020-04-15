import java.util.Random;
import java.util.Scanner;
import java.io.IOException;
public class Lab3 {
	static Scanner in = new Scanner(System.in);
	Random rand = new Random(System.currentTimeMillis());
//		
	public static void main(String[] args) throws IOException {
		Vehicle Cars[] = new Vehicle[Base.MAXVEHICLENUMBER];
		char choice = 0;
		int i;
		int vehicleID = 0;
		
		for(i = 0; i < Cars.length; i++) Cars[i] = setVehicle(); 
		while(true) {
			System.out.printf("\nCurrent chosen vehicle - %d (%s)\n", vehicleID, VehicleType (Cars[vehicleID]));
			choice = ChooseMenuItem("<1> Ñhoose vehicle\n"
			+ "<2> Set vehicle data\n"
			+ "<3> Make vehicle arriving to the base\n"
			+ "<4> Make vehicle leaving the base\n"
			+ "<5> Print vehicles data\n"
			+ "<6> Print base data\n"
			+ "<0> Exit\n"
			+ "Your choice:", "0123456");
			switch(choice) {
			case '1':
				vehicleID = EnterIntValue("Choose vehicle to operate", 0, Base.MAXVEHICLENUMBER - 1);
				break;
			case '2':
				break;
			case '3':
				Cars[vehicleID].arrive();
				break;
			case '4':
				Cars[vehicleID].leave();
				break;
			case '5':
				printVehicles(Cars);
				break;
			case '6':
				Base.print();
				break;
			case '0':
				return;
			}
			Base.print();
		}
	}
	static String VehicleType(Vehicle Car) {
		if(Car instanceof Bus) return "Bus";
		if(Car instanceof Truck) return "Truck";
		return "Vehicle";
	}
	static double EnterDoubleValue(String Announcement, double LowerValue, double UpperValue) {
		double Value;
		do {
			System.out.printf("%s. Shall be lower %d and bigger %d:", Announcement, LowerValue, UpperValue);
			Value = in.nextDouble();
		} while((Value < LowerValue) || (Value > UpperValue)); 
		return Value;
	}
	static int EnterIntValue(String Announcement, int LowerValue, int UpperValue) {
		int Value;
		do {
			System.out.printf("%s. Shall be lower %d and bigger %d:", Announcement, LowerValue, UpperValue);
			Value = in.nextInt();
		} while((Value < LowerValue) || (Value > UpperValue)); 
		return Value;
	}
	static char ChooseMenuItem(String Announcement, String AllowedValues) throws IOException {
		int Value;
		char Symbol = 1;
		System.out.printf("%s", Announcement);
		do {
			Value = System.in.read();
			Symbol = (char) Value;
		} while(AllowedValues.indexOf(Symbol) == -1);
		return Symbol;
	}
	static int RandIntRange(int lowValue, int upperValue) {
		return lowValue + (int)(Math.random()*((upperValue - lowValue) + 1));
	}
	static double RandDoubleRange(double lowValue, double upperValue) {
		return lowValue + (Math.random()*((upperValue - lowValue) + 1));
	}
	static Vehicle setVehicle() {
		Vehicle Car;
		double tank = RandDoubleRange(Vehicle.MINTANK, Vehicle.MAXTANK), petrol = RandDoubleRange(0, tank);
	
		if(RandIntRange(0, 1) == 1) {
			int max_people = RandIntRange(Bus.MINPEOPLE, Bus.MAXPEOPLE); 
			int people = RandIntRange(Bus.MINPEOPLE, max_people);
			Car = new Bus(people, max_people, petrol, tank);
		}
		else {
			double capacity = RandDoubleRange(Truck.MINGOODS, Truck.MAXGOODS); 
			double goods = RandDoubleRange(Truck.MINGOODS, capacity);
			Car = new Truck(goods, capacity, petrol, tank);
		}	
		return Car;
	}
	static void printVehicles(Vehicle  Cars[]) {
		int length = Cars.length;
		for(int i = 0; i < length; i++) {
			if(Cars[i] instanceof Bus) {
				System.out.printf("Bus   %3d ", i); ((Bus)Cars[i]).display();
			} else if(Cars[i] instanceof Truck) {
				System.out.printf("Trunk %3d ", i); ((Truck)Cars[i]).display();
			}
		}		
	}
	static boolean EnterVehicleData(Vehicle Car) {
		Car.setPetrolAmount(EnterDoubleValue("Enter petrol amount", 0, Car.getTankVolume()));
		if(Car instanceof Bus) { 
			((Bus) Car).setPeopleCount(EnterIntValue("Enter number of people in the bus", Bus.MINPEOPLE, ((Bus)Car).getMaxPeople()));	
			return true;
		}	
		else { 
			if(Car instanceof Truck) {
				((Truck) Car).setCurrentLoad(EnterDoubleValue("Enter amount of goods in the truck", Truck.MINGOODS, ((Truck)Car).getMaxLoad()));	
				return true;	
			}
		}
		return false;
	}
}
