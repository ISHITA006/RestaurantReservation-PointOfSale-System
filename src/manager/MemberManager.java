package manager;

import java.util.ArrayList;
import java.util.Scanner;
import databasescanner.databaseRW;
import mainapplicationdriver.ApplicationClass;
import entities.Member;
/**
 * Member Manager class that manages creation, removal and modifications to Members
 */

public class MemberManager {
	/**
	 * This is a new scanner
	 */
	private static Scanner sc = new Scanner(System.in);
	/**
	 * Member Manager class that manages creation, removal and modifications to Members
	 */
	private static ArrayList<Member> memberList = entities.Restaurant.members;

	public static void printMemberDetails(Member member) {
		System.out.println();
		System.out.println("Member Name: " + member.getName());
		System.out.println("Member ID: " + member.getMemberId());
		System.out.println("Contact number: " + member.getContact());
	}
	
	public static void addNewMember(String name, int id, String contact) {
		Member newMem;
		newMem = new Member(id, name, contact);
		memberList.add((Member) newMem);
		System.out.println();
		System.out.println("Member " + newMem.getName() + " has been added to the members list");
		
		entities.Restaurant.members= memberList;
		String finalPath = ApplicationClass.finalDir + "\\members.dat";
		databaseRW.write(memberList.toArray(), finalPath);
	}
	
	public static void removeMember(String contact) {
		
		try{
			
			for(int i = 0; i<memberList.size(); i++) {
				if(memberList.get(i).getContact().equals(contact)) {
					memberList.remove(i);	
					break;
				}
			}
			
			System.out.println("Successfully removed the member from the list");
		}
		catch(IndexOutOfBoundsException e) {
			System.out.println("Removal of member failed, invalid contact number");
		}
		
		entities.Restaurant.members= memberList;
		String finalPath = ApplicationClass.finalDir + "\\members.dat";
		databaseRW.write(memberList.toArray(), finalPath);
		
	}

	public static void updateMember(String newContact, String oldContact) {
		
		for(int i=0; i<memberList.size(); i++) {
			if(memberList.get(i).getContact().equals(oldContact))
				memberList.get(i).setContact(newContact);
			System.out.println("Successfully Updated " + oldContact + " to " + newContact);
			break;
			}
				
		entities.Restaurant.members= memberList;
		String finalPath = ApplicationClass.finalDir + "\\members.dat";
		databaseRW.write(memberList.toArray(), finalPath);
	}
	
	public static boolean checkMember(String num)
	{
		for(int i=0; i<memberList.size(); i++) {
		if(memberList.get(i).getContact().equals(num))
			return true;
		}
		
		return false;
	}
}