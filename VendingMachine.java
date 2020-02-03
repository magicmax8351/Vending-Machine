import java.util.ArrayList;


public class VendingMachine {

	private ArrayList<Item> items;
	private Money change;
	private Money profit;
	
	public VendingMachine() {
		items = new ArrayList<Item>();
		change = new Money();
		profit = new Money();
	}
	
	public VendingMachine(ArrayList<Item> i, Money c, Money p) {
		for(int y = 0; y<i.size(); y++) {
		items.set(y, new Item(i.get(y)));
		}

		change = new Money(c.getDollar(), c.getQuarter());
		profit = new Money(p.getDollar(), p.getQuarter());
		
		
	}

	public void addItem(Item x) {
		items.add(new Item(x));
	}
	
	public void buyItem(String candy, Money payment) {
		int location = -1;
		for(int i = 0; i<items.size(); i++) {
			if(items.get(i).getName().equals(candy)) {
				location = i;
			}
		}
		if(location == -1) {
			System.out.print("Item doesn't exist :( ");
			return;
		}
		else if(items.get(location).getQuantity() <= 0) {
			System.out.print("Item is out of stock");
			return;
		}
		else if(payment.getTotal() < items.get(location).getPrice()) {
			System.out.println("Insufficient funds");
			return;
		}
		else if(payment.getTotal() == items.get(location).getPrice()) {
			System.out.println("Success!");
			items.get(location).setQuantity(items.get(location).getQuantity()-1);
			profit.setDollar(profit.getDollar() + payment.getDollar());
			profit.setQuarter(profit.getQuarter() + payment.getQuarter());
			return;
		}
	
		double temp = payment.getTotal() - items.get(location).getPrice();
		boolean changeMade = false;
		int dChange = (int)(temp);
		int qChange = (int)((temp-dChange)/.25);
		while( !changeMade && dChange >= 0) {
			if(dChange <= change.getDollar() && qChange <= change.getQuarter()) {
				changeMade = true;
			}
			else {
				qChange += 4;
				dChange -= 1;
			}
		}
		if(dChange < 0) {
			System.out.println("Cannot make change");
			return;
		}
		change.setQuarter(change.getQuarter()-qChange);
		change.setDollar(change.getDollar()-dChange);
		profit.setDollar(profit.getDollar() + payment.getDollar());
		profit.setQuarter(profit.getQuarter() + payment.getQuarter());
		System.out.println("Success!");
		System.out.println("Your change is " + (payment.getTotal() - items.get(location).getPrice()));
		items.get(location).setQuantity(items.get(location).getQuantity()-1);
		return;
	}
	
	
	public void addChange(int q, int d) {
		change.setQuarter(change.getQuarter()+q);
		change.setDollar(change.getDollar()+d);
	}
	
	public void removeProfit() {
		System.out.println(profit.getTotal());
		profit.setDollar(0);
		profit.setQuarter(0);
	}
	
	public void getVendingMachineInfo() {
		System.out.println("Dollars in machine for change " + change.getDollar());
		System.out.println("Quarters in machine for change " + change.getQuarter());
		System.out.println("Dollars in machine from profit " + profit.getDollar());
		System.out.println("Quarters in machine from profit " + profit.getQuarter());
		if(items.size() == 0) {
			System.out.println("No items");
		}
		else
			for(int i = 0; i<items.size(); i++) {
				System.out.println("Name of candy " + items.get(i).getName());
				System.out.println("\tNumber of candy " + items.get(i).getQuantity());
				System.out.println("\tPrice of candy " + items.get(i).getPrice());
				
			}
	}
	
	
	
	
	
	
	
	
	
	
	
}





