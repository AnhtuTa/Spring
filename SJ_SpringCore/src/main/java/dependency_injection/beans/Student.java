package dependency_injection.beans;

import java.util.List;
import java.util.Set;

public class Student {
	String name;
	List<String> emailList;
	Set<Address> addrSet;
	
	public Student() {}
	
	public Student(String name, List<String> emailList, Set<Address> addrSet) {
		this.name = name;
		this.emailList = emailList;
		this.addrSet = addrSet;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getEmailList() {
		return emailList;
	}

	public void setEmailList(List<String> emailList) {
		this.emailList = emailList;
	}

	public Set<Address> getAddrSet() {
		return addrSet;
	}

	public void setAddrSet(Set<Address> addrSet) {
		this.addrSet = addrSet;
	}
	
	public void printInfo() {
		System.out.println("Student: " + this.name);
		System.out.print("\tEmails:");
		for(String em : emailList) {
			System.out.print(em + ", ");
		}
		System.out.println("\n\tAddress:");
		for(Address ad : addrSet) {
			System.out.println("\t\t" + ad);
		}
	}
}
