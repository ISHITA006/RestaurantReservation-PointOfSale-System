package userinterface;
import java.util.*;
import entities.FoodItem;
import entities.Member;
import exceptionhandler.ExceptionScan;
import manager.MenuIManager;

/**
 * This is the interface class that allows the restaurant staff to choose whether they wan to add, remove or update members
 * @author ISHITA SARAF
 *
 */
public class MemberInterface {
	
	static Scanner sc = new Scanner(System.in);
	
	public static void memberMainOptions() {
		int choice;
		do {
			System.out.println("\nMEMBERS");
			
			System.out.println("[1] - View all members");
			System.out.println("[2] - Add new member");
			System.out.println("[3] - Update member");
			System.out.println("[4] - Remove member");
			System.out.println("[0] - Go Back");
			
			choice = ExceptionScan.nextInt(0, 5);
			
			switch(choice) {
				case 1:
					viewMembersUI();
					break;
				case 2:
					addNewMemberUI();
					break;
				case 3:
					updateMemberUI();
					break;
				case 4:
					removeMemberUI();
					break;
				case 0:
					return;					
			}
		}while (true);

}
/**
 * This method provides the interface print for the user to perform various ations related to the menu system
 */
	
	private static void viewMembersUI() {
		System.out.println();
		if(entities.Restaurant.members.size()==0) {
			System.out.println("No current members!");
			return;
		}
		System.out.println("Displaying current members:");
		System.out.println("============================");
		for(int i=0; i<entities.Restaurant.members.size(); i++) {
			manager.MemberManager.printMemberDetails(entities.Restaurant.members.get(i));
		}
		System.out.println();
		System.out.println("============================");
	}
	
	private static void addNewMemberUI() {
		System.out.println();
		
		int currentLength = entities.Restaurant.members.size();
		
		System.out.println("Enter name of the member");
		String name = sc.nextLine();
		
		System.out.println("Enter contact of member");
		String contact = sc.nextLine();
		
		boolean isMember= manager.MemberManager.checkMember(contact);
		
		if(isMember) {
			System.out.print("Member already registered with entered contact number!");
		}
		
		else {
			manager.MemberManager.addNewMember(name, currentLength+1, contact);
		}
	}
	
	private static void updateMemberUI() {
		System.out.println();
		
		System.out.println("Enter current contact number of member");
		String oldContact = sc.nextLine();
		
		boolean isMember= manager.MemberManager.checkMember(oldContact);
		if(!isMember) {
			System.out.print("No member currently registered with this contact number!");
		}
		
		else {
		System.out.println("Enter new contact number of member");
		String newContact = sc.nextLine();
		
		manager.MemberManager.updateMember(newContact, oldContact);
		
		}		

	}
	
	private static void removeMemberUI() {
		System.out.println();
		System.out.println("Enter contact of member you wish to remove: ");
		String contact = sc.nextLine();
		manager.MemberManager.removeMember(contact);
	}

}